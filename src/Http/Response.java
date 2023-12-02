package Http;

import java.util.ArrayList;

public class Response {
  protected int statusCode = 200;
  protected ArrayList<String[]> headers = new ArrayList<String[]>();
  protected String content = "";

  public int statusCode() {
    return this.statusCode;
  }

  public Response setStatusCode(int statusCode) {
    this.statusCode = statusCode;
    return this;
  }

  public ArrayList<String[]> headers() {
    return this.headers;
  }

  public Response setHeader(String key, String value) {
    String[] header = new String[2];
    header[0] = key;
    header[1] = value;
    this.headers.add(header);
    return this;
  }

  public Response removeHeader(String key) {
    for (int i = 0; i < this.headers.size(); i++) {
      if (this.headers.get(i)[0].equals(key)) {
        this.headers.remove(i);
      }
    }
    return this;
  }

  public Response setContentType(String contentType) {
    this.setHeader("Content-Type", contentType);
    return this;
  }

  public String content() {
    return this.content;
  }

  public Response setContent(String content) {
    this.content = content;
    return this;
  }

  public void prepare() {
    if (content == null) {
      this.removeHeader("Content-Type");
      this.removeHeader("Content-Length");
    } else {
      this.setHeader("Content-Length", String.valueOf(this.content.length()));
    }
  }

  public  Response json(String data) {
    return ((new Response()).setContentType("application/json")
        .setContent(data));
  }

  public  Response html(String data) {
    return ((new Response()).setContentType("text/html").setContent(data));
  }

  public  Response text(String data) {
    return ((new Response()).setContentType("text/plain").setContent(data));
  }

  public  Response redirect(String uri){
    return ((new Response()).setStatusCode(302)).setHeader("Location",uri);
  }


}
