package Http.Routes;

import Routing.Router;
public class Web {

    //creamos rutas para el servidor

    public Web() {
        Router.get("/", (request, response) -> {
            return response.setStatusCode(302).redirect("/test");
        });

        Router.get("/test", (request, response) -> {
            return response.setStatusCode(200).setContent("Hello World");
        });
    }

}
