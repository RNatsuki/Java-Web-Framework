package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import com.google.gson.Gson;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Http.HttpMethod;
import Http.HttpNotFound;
import Http.Request;
import Http.Response;
import Routing.Route;
import Routing.Router;

public class MyHandler implements HttpHandler {
  Router router;

  public MyHandler(Router router) {
    this.router = router;
  }

  @Override
  public void handle(HttpExchange t) throws IOException {
    try {
      Request request = createRequest(t);

      Response response = processRequest(request);
      sendResponse(t, response);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Request createRequest(HttpExchange t) {
    System.out.println("Request uri: " + t.getRequestURI().toString());
    System.out.println("Request method: " + t.getRequestMethod());

    Request request = new Request()
        .setUri(t.getRequestURI().toString())
        .setMethod(HttpMethod.valueOf(t.getRequestMethod()));

    if (request.method() == HttpMethod.POST) {
      try {
        request = setPostData(request, t);
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    return request;

  }

  private Response processRequest(Request request) {
    try {
      Route<Response> route = router.resolve(request);
      return route.action().call(request, new Response());
    } catch (HttpNotFound | Exception e) {
      if (e instanceof HttpNotFound) {
        return new Response().setStatusCode(404).setContent("Not found");
      } else {
        e.printStackTrace();
        return new Response().setStatusCode(500).setContent(e.getMessage());
      }
    }
  }

  private void sendResponse(HttpExchange t, Response response) throws IOException {
    if (response.statusCode() == 302) {
        String location = response.getHeader("Location");
        t.getResponseHeaders().set("Location", location);
        t.sendResponseHeaders(response.statusCode(), 0);
        t.close();
    } else {
        byte[] contentBytes = response.content().getBytes();
        t.sendResponseHeaders(response.statusCode(), contentBytes.length);
        OutputStream os = t.getResponseBody();
        os.write(contentBytes);
        os.close();
    }
}

  private Request setPostData(Request request, HttpExchange t) throws IOException {
    InputStream is = t.getRequestBody();

    StringBuilder sb = new StringBuilder();
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      sb.append(new String(buffer, 0, bytesRead));
    }

    String formData = sb.toString();
    HashMap<String, String> postData = new HashMap<>();

    try {
      Gson gson = new Gson();
      postData = gson.fromJson(formData, HashMap.class);
    } catch (Exception e) {
      // Si no está en formato JSON, asumir que está en formato de formulario
      String[] keyValuePairs = formData.split("&");
      for (String pair : keyValuePairs) {
        String[] keyValue = pair.split("=");
        if (keyValue.length == 2) {
          String key = keyValue[0];
          String value = keyValue[1];
          postData.put(key, value);
        }
      }
    }
    request.setPostData(postData);

    return request;
  }

}
