package Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    Router.get("/", (Request request, Response response) -> {
      return response.setStatusCode(302).redirect("/test");
    });

    Router.get("/test", (Request request, Response response) -> {
      return response.setStatusCode(200).setContent("Hello World");
    });

    Server server = new ServerImpl();

    server.start(Router.getInstance());

  }
}