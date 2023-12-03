package Main;

import java.io.IOException;
import Http.Routes.*;
import Server.Server;
import Server.ServerImpl;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) throws IOException {

    new Web();

    Server server = new ServerImpl();

    server.start();

  }
}