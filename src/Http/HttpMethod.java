package Http;

public enum HttpMethod {
  
  GET,
  POST,
  PUT,
  DELETE,
  PATCH,
  HEAD,
  OPTIONS,
  TRACE,
  CONNECT,
  UNKNOWN;
  
  public static HttpMethod fromString(String method) {
    switch (method) {
      case "GET":
        return GET;
      case "POST":
        return POST;
      case "PUT":
        return PUT;
      case "DELETE":
        return DELETE;
      case "PATCH":
        return PATCH;
      case "HEAD":
        return HEAD;
      case "OPTIONS":
        return OPTIONS;
      case "TRACE":
        return TRACE;
      case "CONNECT":
        return CONNECT;
      default:
        return UNKNOWN;
    }
  }

}