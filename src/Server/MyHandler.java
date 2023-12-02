package Server;

import java.io.IOException;
import java.io.OutputStream;

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

    return new Request()
        .setUri(t.getRequestURI().toString())
        .setMethod(HttpMethod.valueOf(t.getRequestMethod()));

  }

  private Response processRequest(Request request) {
    try {
      Route<Response> route = router.resolve(request);
      return route.action().call(request, new Response());
    } catch (HttpNotFound | Exception e) {
      return new Response().setStatusCode(404).setContent("Not found");
    }
  }

  private void sendResponse(HttpExchange t, Response response) throws IOException {
    t.sendResponseHeaders(response.statusCode(), response.content().length());
    OutputStream os = t.getResponseBody();
    os.write(response.content().getBytes());
    os.close();
  }
}
