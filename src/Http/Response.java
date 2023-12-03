package Http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;
import java.io.FileNotFoundException;
import java.io.IOException;

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

  public String getHeader(String key) {
    for (String[] header : headers) {
      if (header[0].equals(key)) {
        return header[1];
      }
    }
    return null;
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

  public Response json(String data) {
    return ((new Response()).setContentType("application/json")
        .setContent(data));
  }

  public Response html(String data) {
    return ((new Response()).setContentType("text/html").setContent(data));
  }

  public Response text(String data) {
    return ((new Response()).setContentType("text/plain").setContent(data));
  }

  public Response redirect(String uri) {
    
    return this.setHeader("Location", uri).setStatusCode(302);

  }

  public Response view(String view) throws FileNotFoundException, IOException {

    //print the current working directory

    File file = new File("src/Resources/views/" + view + ".html");

    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String html = "";
    while ((st = br.readLine()) != null) {
      html += st;
    }
    br.close();

    this.setContent(html);
    this.setContentType("text/html");

    return this;
  }

  public Response view(String view, Map<String, Object> data) throws FileNotFoundException, IOException {
    //Resources/views/index.html

    File file = new File("src/Resources/views/" + view + ".html");
    //File file = new File("public_/" + view + ".html");

    BufferedReader br = new BufferedReader(new FileReader(file));
    String st;
    String html = "";
    while ((st = br.readLine()) != null) {
      html += st;
    }
    br.close();

    for (Map.Entry<String, Object> entry : data.entrySet()) {
      html = html.replace("{{ " + entry.getKey() + " }}", entry.getValue().toString());
    }
    this.setContent(html);
    this.setContentType("text/html");

    return this;

  }

  
}
