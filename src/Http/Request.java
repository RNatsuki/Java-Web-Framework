package Http;

import java.io.IOException;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;

import Routing.Route;

/**
 * Request
 */
public class Request {

  protected String uri;
  protected HttpMethod method;

  protected ArrayList<String[]> data;
  protected ArrayList<String[]> query;

  protected Route route;


  public String uri(){
    return this.uri;
  }

  public Request setUri(String uri){
    this.uri = uri;
    return this;
  }

  public Request setRoute(Route route){
    this.route = route;
    return this;
  }

  public Route route(){
    return this.route;
  }

  public HttpMethod method(){
    return this.method;
  }

  public Request setMethod(HttpMethod method){
    this.method = method;
    return this;
  }

  public ArrayList<String[]> data(){
    return this.data;
  }

  public Request setPostData(ArrayList<String[]> data){
    this.data = data;
    return this;
  }

  public ArrayList<String[]> query(){
    return this.query;
  }

  public Request setQueryParameters(ArrayList<String[]> query){
    this.query = query;
    return this;
  }

  public Object routeParameters(){
    return this.route.parseParameters(this.uri);
  }

  public String routeParameters(String key){
    for(String[] parameter : this.route.parameters()){
      if(parameter[0].equals(key)){
        return parameter[1];
      }
    }
    return null;
  }

  public static Request fromHttpExchange(HttpExchange exchange) throws IOException{
    Request request = new Request();

    request.setUri(exchange.getRequestURI().toString());
    request.setMethod(HttpMethod.fromString(exchange.getRequestMethod()));

    ArrayList<String[]> data = new ArrayList<String[]>();
    ArrayList<String[]> query = new ArrayList<String[]>();

    if(request.method() == HttpMethod.POST){
      String[] contentType = exchange.getRequestHeaders().getFirst("Content-Type").split(";");
      if(contentType[0].equals("application/x-www-form-urlencoded")){
        String[] dataString = new String(exchange.getRequestBody().readAllBytes()).split("&");
        for(String dataItem : dataString){
          String[] dataItemArray = dataItem.split("=");
          data.add(dataItemArray);
        }
      }
    }

    String[] queryString = exchange.getRequestURI().getQuery().split("&");
    for(String queryItem : queryString){
      String[] queryItemArray = queryItem.split("=");
      query.add(queryItemArray);
    }

    request.setPostData(data);
    request.setQueryParameters(query);

    return request;
  }

  


}