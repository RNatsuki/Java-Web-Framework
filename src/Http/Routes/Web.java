package Http.Routes;

import Routing.Router;

public class Web {

  // creamos rutas para el servidor

  public Web() {
    Router.get("/", (request, response) -> {
      return response.view("user");
    });

    Router.get("/hello", (req, res) -> {
      return res.text("World");
    });

  }

}
