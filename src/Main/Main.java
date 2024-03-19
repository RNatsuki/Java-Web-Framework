package Main;

import java.io.IOException;
import Server.Server;
import Server.ServerImpl;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) throws IOException {

    Server server = new ServerImpl(8080);

    server.start();

  }
}
