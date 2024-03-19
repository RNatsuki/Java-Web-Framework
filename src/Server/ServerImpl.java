package Server;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import Http.Routes.Web;
import Routing.Router;

public class ServerImpl implements Server {
  int port = -1;
  public ServerImpl(int port){
    this.port = port;
    new Web();
  }


  @Override
  public void start() {
    try {
      HttpServer Server = HttpServer.create(new InetSocketAddress(this.port), 0);
      Server.createContext("/", new MyHandler(Router.getInstance()));
      Server.setExecutor(null);
      Server.start();
    } catch (Exception e) {
      /* System.out.println(e.getMessage()); */
    }

  }

  @Override
  public void stop() {

  }

}
