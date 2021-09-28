import java.io.IOException;

public class ListenClient extends Client {
  private Connection connection;

  /**
   * Starts a ListenClient: Sends Listen command, and continuously handles Messages.
   * @throws IOException
   */
  public void startClient() throws IOException {
    System.out.println("Starting listen client...");
    this.connection=new Connection(new Client().connect());//Creates the connection channel
    System.out.println("Connected successfully to server.");
    this.sendListen();//Just identifies it's a ListenClient, and sits there waiting.
      while(true){//Indefinitely listens to messages.
        int cmd = this.connection.readInt();
        if(cmd == ChatroomProtocol.MESSAGE) {
          this.handleMessage();
        }
      }
  }

  /**
   * Handles messages by taking the messages in, and printing it to console in the required format.
   * @throws IOException
   */
  public void handleMessage() throws IOException {
    String sender = this.connection.readUTF();
    String message = this.connection.readUTF();
    System.out.println(new Message(sender,message).toString());//Sends message in appropriate format to console.
  }

  /**
   * Sends the Listen command on connection to identify as a ListenClient.
   * @throws IOException
   */
  public void sendListen() throws IOException {
    this.connection.writeInt(ChatroomProtocol.LISTEN);
  }

  public static void main(String[] args) throws IOException {
    // Do not change this
    new ListenClient().startClient();//Creates a ListenClient, starts it.
  }
}
