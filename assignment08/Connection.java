import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
  private final DataInputStream in;
  private final DataOutputStream out;
  private final Socket socket;

  /**
   * Creates a Connection object given a Socket to wrap around.
   * @param socket is the socket to be wrapped inside Connection object
   * @throws IOException
   */
  public Connection(Socket socket) throws IOException {
    this.socket = socket;
    this.in = new DataInputStream(socket.getInputStream());
    this.out = new DataOutputStream(socket.getOutputStream());
  }

  /**
   * Is a wrapper method for readInt()
   * @return an integer
   * @throws IOException
   */
  public int readInt() throws IOException {
    return this.in.readInt();
  }

  /**
   * Is a wrapper method for readUTF()
   * @return a String
   * @throws IOException
   */
  public String readUTF() throws IOException {
    return this.in.readUTF();
  }

  /**
   * Is a wrapper method for writeInt(), includes flushing by default.
   * @param i is the int to write
   * @throws IOException
   */
  public void writeInt(int i) throws IOException {
    this.out.writeInt(i);
    this.out.flush();
  }

  /**
   * Is a wrapper method for writeUTF, includes flushing by default.
   * @param s is the String to write
   * @throws IOException
   */
  public void writeUTF(String s) throws IOException {
    this.out.writeUTF(s);
    this.out.flush();
  }

  /**
   * Is a custom method used to send messages for convenience
   * @param m is the Message to be sent.
   * @throws IOException
   */
  public void writeMessage(Message m) throws IOException {
    this.writeInt(ChatroomProtocol.MESSAGE);
    this.writeUTF(m.getWho());
    this.writeUTF(m.getMessage());
  }

  /**
   * Closes all connections.
   */
  public void close() {
    try {
      this.in.close();
    } catch (Exception e) {
    }

    try {
      this.out.close();
    } catch (Exception e) {
    }

    try {
      this.socket.close();
    } catch (Exception e) {
    }
  }
}
