package public_.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import Http.Request;
import Http.Response;

import Routing.Router;
import Server.Server;
import Server.ServerImpl;

public class Main {
  public static void main(String[] args) throws IOException {

    Router router = new Router();

    router.post("/", (Request request, Response response) -> {

      // data in JSON format
      String username = request.getData("username");
      String password = request.getData("password");

      if (username == null || password == null) {
        return response.setContent("Username or password not found");
      }

      return response.setContent("welcome " + username);
    });

    router.get("/html", (Request request, Response response) -> {
      Map<String, Object> variables = new HashMap<>();
      variables.put("title", "Página de inicio");
      variables.put("message", "¡Hola, mundo!");

      return response.view("index", variables);

    });

    Server server = new ServerImpl();
    server.start(router);

  }

}