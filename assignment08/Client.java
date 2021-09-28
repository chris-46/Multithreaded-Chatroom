import java.io.IOException;
import java.net.Socket;

public class Client {
  /**
   * Connects a Client to the server at localhost and Port number ChatroomServer.PORT, returns the connected Socket.
   * @return the Socket of the new Client
   * @throws IOException
   */
  protected Socket connect() throws IOException {
    return new Socket("localhost", ChatroomServer.PORT);
  }
}
