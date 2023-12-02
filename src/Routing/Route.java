package Routing;

import java.util.ArrayList;

import Http.HttpMethod;
import Interfaces.Action;

public class Route<T> {
  protected String uri;
  protected Action action;
  protected String regex;
  protected ArrayList<String[]> parameters;

  public Route(String uri, HttpMethod method, Action action) {
    this.uri = uri;
    this.action = action;
    this.regex = this.uri.replaceAll("\\{[a-zA-Z0-9]+\\}", "([a-zA-Z0-9]+)");
    this.parameters = new ArrayList<>();
  }

  public String uri() {
    return this.uri;
  }

  public Action action() {
    return this.action;
  }

  public boolean matches(String uri) {
    return uri.matches(this.regex);
  }

  public boolean hasParameters() {
    return this.parameters.size() > 0;
  }

  public Object parseParameters(String uri) {
    String[] uriParts = uri.split("/");
    String[] routeParts = this.uri.split("/");
    for (int i = 0; i < routeParts.length; i++) {
      if (routeParts[i].matches("\\{[a-zA-Z0-9]+\\}")) {
        String[] parameter = new String[2];
        parameter[0] = routeParts[i].replaceAll("\\{", "").replaceAll("\\}", "");
        parameter[1] = uriParts[i];
        this.parameters.add(parameter);
      }
    }
    return this.parameters;
  }

  public ArrayList<String[]> parameters() {
    return this.parameters;
  }
}