package Routing;

import java.util.ArrayList;
import java.util.HashMap;

import Http.HttpMethod;
import Http.HttpNotFound;
import Http.Request;
import Http.Response;
import Interfaces.Action;

public class Router {
  protected HashMap<HttpMethod, ArrayList<Route<Response>>> routes = new HashMap<HttpMethod, ArrayList<Route<Response>>>();

  private static Router instance = null;

  public Router() {
    for (HttpMethod method : HttpMethod.values()) {
      this.routes.put(method, new ArrayList<Route<Response>>());
    }
  }

  public Route<Response> resolve(Request request) {
    for (Route<Response> route : this.routes.get(request.method())) {
      if (route.matches(request.uri())) {
        request.setRoute(route);
        return route;
      }
    }
    throw new HttpNotFound();
  }

  protected void registerRoute(HttpMethod method, String uri, Action action) {
    this.routes.get(method).add(new Route<Response>(uri, method, action));
  }

  public void get(String uri, Action action) {
    this.registerRoute(HttpMethod.GET, uri, action);
  }

  public void post(String uri, Action action) {
    this.registerRoute(HttpMethod.POST, uri, action);
  }

  public void put(String uri, Action action) {
    this.registerRoute(HttpMethod.PUT, uri, action);
  }

  public void delete(String uri, Action action) {
    this.registerRoute(HttpMethod.DELETE, uri, action);
  }

  public void patch(String uri, Action action) {
    this.registerRoute(HttpMethod.PATCH, uri, action);
  }



/* Singleton and Static Methods */

  public static Router getInstance() {
    if (instance == null) {
      instance = new Router();
    }
    return instance;
  }

  public static void getStatic(String uri, Action action) {
    getInstance().get(uri, action);
  }

  public static void postStatic(String uri, Action action) {
    getInstance().post(uri, action);
  }

  public static void putStatic(String uri, Action action) {
    getInstance().put(uri, action);
  }

  public static void deleteStatic(String uri, Action action) {
    getInstance().delete(uri, action);
  }

  public static void patchStatic(String uri, Action action) {
    getInstance().patch(uri, action);
  }


}