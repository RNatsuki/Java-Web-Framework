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

    router.post("/", (Request request, Response response) -> {

      
      System.out.println("Request data: " + request.data().toString());


      return response.setContent("Hello world");
    });

    Server server = new ServerImpl();
    server.start(router);

  }

}