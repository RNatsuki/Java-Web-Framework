package Main;

import java.io.IOException;
import Server.*;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) throws IOException {

    Server server = new NativeServer(8080);
    server.start();

  }
}
