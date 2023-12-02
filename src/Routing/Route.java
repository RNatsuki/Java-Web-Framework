package Routing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Http.HttpMethod;
import Interfaces.Action;

public class Route<T> {
  protected String uri;
  protected Action action;
  protected String regex;
  protected ArrayList<String[]> parameters = new ArrayList<>();

  public Route(String uri, HttpMethod method, Action action) {
    this.uri = uri;
    this.action = action;
    this.regex = this.uri.replaceAll("\\{[a-zA-Z0-9]+\\}", "([a-zA-Z0-9]+)");
    this.parameters = new ArrayList<>();

    // Agregar los nombres de los parámetros a la lista
    Matcher matcher = Pattern.compile("\\{([a-zA-Z0-9]+)\\}").matcher(uri);
    while (matcher.find()) {
      String parameterName = matcher.group(1);
      this.parameters.add(new String[] { parameterName });
    }
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

  public Route<T> setParameters(ArrayList<String[]> parameters) {
    this.parameters = parameters;
    return this;
  }

  public ArrayList<String[]> parameters() {
    return this.parameters;
  }

  /*
   * 
   * public function parseParameters(string $uri): array
   * {
   * preg_match("#^$this->regex$#", $uri, $arguments);
   * 
   * return array_combine($this->parameters, array_slice($arguments, 1));
   * }
   * 
   */

  public HashMap<String, String> parseParameters(String uri) {
    HashMap<String, String> parameters = new HashMap<>();
    Matcher matcher = Pattern.compile(this.regex).matcher(uri);
    if (matcher.find()) {
      for (int i = 0; i < this.parameters.size(); i++) {
        String parameterName = this.parameters.get(i)[0];
        String parameterValue = matcher.group(i + 1);
        parameters.put(parameterName, parameterValue);
      }
    }
    return parameters;

  }

}