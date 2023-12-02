package public_.Main;

import java.io.IOException;
import java.util.ArrayList;

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


    router.get("/users/{id}", (Request request, Response response) -> {
      
      int id = Integer.parseInt(request.getParameter("id"));

      return response.json("{\"message\": \"Hello world\", \"id\": " + id + "}");
    });

    Server server = new ServerImpl();
    server.start(router);

  }

}