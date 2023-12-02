package Server;

import java.io.IOException;

import Routing.Router;

public interface Server {
  public void start(Router router) throws IOException;

  public void stop();
}
