package Server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;

import Routing.Router;

public class ServerImpl implements Server {

  @Override
  public void start(Router router) throws IOException {
    HttpServer Server = HttpServer.create(new InetSocketAddress(8080), 0);
    Server.createContext("/", new MyHandler(router));
    Server.setExecutor(null);
    Server.start();
  }

  @Override
  public void stop() {
    // TODO Auto-generated method stub
  }

}
