package public_.Main;

import java.io.IOException;

import Http.Request;
import Http.Response;

import Routing.Router;
import Server.Server;
import Server.ServerImpl;

public class Main {
  public static void main(String[] args) throws IOException {

    Router router = new Router();

    router.get("/", (Request request, Response response) -> {
      return response.json("{\"message\": \"Hello world\"}");
    });

    // una ruta con parámetros

    Server server = new ServerImpl();
    server.start(router);

  }

  /**
   * Handler
   */

  /*
   * static class MyHandler implements HttpHandler {
   * 
   * Router router;
   * 
   * MyHandler(Router router) {
   * this.router = router;
   * }
   * 
   * @Override
   * public void handle(HttpExchange t) {
   * try {
   * Request request = createRequest(t);
   * Response response = processRequest(request);
   * sendResponse(t, response);
   * } catch (IOException e) {
   * e.printStackTrace();
   * }
   * }
   * 
   * private Request createRequest(HttpExchange t) {
   * System.out.println("Request uri: " + t.getRequestURI().toString());
   * System.out.println("Request method: " + t.getRequestMethod());
   * 
   * return new Request()
   * .setUri(t.getRequestURI().toString())
   * .setMethod(HttpMethod.valueOf(t.getRequestMethod()));
   * }
   * 
   * private Response processRequest(Request request) {
   * try {
   * Route<Response> route = router.resolve(request);
   * return route.action().call(request, new Response());
   * } catch (HttpNotFound | Exception e) {
   * return new Response().setStatusCode(404).setContent("Not found");
   * }
   * }
   * 
   * private void sendResponse(HttpExchange t, Response response) throws
   * IOException {
   * t.sendResponseHeaders(response.statusCode(), response.content().length());
   * OutputStream os = t.getResponseBody();
   * os.write(response.content().getBytes());
   * os.close();
   * }
   * }
   */
}