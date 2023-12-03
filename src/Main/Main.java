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
    Router router = new Router();

    router.get("/user/{username}", (Request request, Response response) -> {
      Map<String, Object> params = new HashMap<>();

      params.put("username", request.getParameter("username"));

      // Send params as Object to the view new Object[]{params} params = {username:
      // "John"}

      return response.view("user", params);
    });

    router.get("/", (Request request, Response response) -> {

      return response.redirect("/user/John");
    });

    Server server = new ServerImpl();

    server.start(router);

  }
}