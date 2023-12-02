package Main;

import java.io.IOException;

import Http.Request;
import Http.Response;
import Routing.Router;
import Server.Server;
import Server.ServerImpl;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) throws IOException {
    Router router = new Router();

    router.get("/", (Request request, Response response) -> {
      response.setContent("Hello World");
      return response;
    });

    Server server = new ServerImpl();

    server.start(router);

  }
}