package Http;

import java.util.ArrayList;
import java.util.HashMap;

import Routing.Route;

/**
 * Request
 */
public class Request {

  protected String uri;
  protected HttpMethod method;

  protected ArrayList<String[]> data;
  protected ArrayList<String[]> query;

  protected Route<?> route;

  public String uri() {
    return this.uri;
  }

  public Request setUri(String uri) {
    this.uri = uri;
    return this;
  }

  public Request setRoute(Route<?> route) {
    this.route = route;
    return this;
  }

  public Route<?> route() {
    return this.route;
  }

  public HttpMethod method() {
    return this.method;
  }

  public Request setMethod(HttpMethod method) {
    this.method = method;
    return this;
  }

  public ArrayList<String[]> data() {
    return this.data;
  }

  public Request setPostData(ArrayList<String[]> data) {
    this.data = data;
    return this;
  }

  public ArrayList<String[]> query() {
    return this.query;
  }

  public Request setQueryParameters(ArrayList<String[]> query) {
    this.query = query;
    return this;
  }

  public String getQueryParameter(String key) {
    for (int i = 0; i < this.query.size(); i++) {
      if (this.query.get(i)[0].equals(key)) {
        return this.query.get(i)[1];
      }
    }
    return null;
  }

  public String toString() {
    return this.method + " " + this.uri;
  }

  
  public HashMap<String, String> getParsedParameters() {
    return this.route.parseParameters(uri);
  }
  

  public String getParameter(String key) {
    HashMap<String, String> parameters = this.route.parseParameters(uri);
    return parameters.get(key);
  }

}