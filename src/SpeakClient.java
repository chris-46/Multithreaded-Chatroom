import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SpeakClient extends Client {
  private Connection connection;
  private String name;
  private Scanner in = new Scanner(System.in);

  /**
   * Prints the prompt that signifies all is ready to send the SpeakClient's Messages.
   * @param name is the name of the SpeakClient user.
   */
  private void printPrompt(String name) {
    System.out.print(name + " -> ");
  }

  /**
   * Starts the SpeakClient: Sends Speak command on connection, continuously takes Messages from stdin and sending the Messages.
   * @param name is the name of the SpeakClient user.
   * @throws IOException
   */
  public void startClient(String name) throws IOException {
    this.name=name;
    this.connection = new Connection(new Client().connect());
    System.out.println("Starting SpeakClient...");
    this.sendSpeak();//Sends speak command.
    System.out.println("Connected successfully to server.");
    System.out.println("User: "+name);

    while(true){ //Sends messages indefinitely
        this.printPrompt(this.name);//Prints prompt
        if(in.hasNextLine()){
          String s = in.nextLine();
          this.sendMessage(new Message(this.name,s));//Sends message to the Chatroom Worker.
        }
    }

  }

  /**
   * Sends a Message to the server.
   * @param m is the Message to be sent.
   * @throws IOException
   */
  public void sendMessage(Message m) throws IOException {
    this.connection.writeMessage(m);
  }

  /**
   * Sends the Speak command to identify as a SpeakClient.
   * @throws IOException
   */
  public void sendSpeak() throws IOException {
    this.connection.writeInt(ChatroomProtocol.SPEAK);
  }

  public static void main(String[] args) throws IOException {
    // Do not change this
    new SpeakClient().startClient(args[0]);//Creates a SpeakClient of name taken from args[0], and starts it.
  }
}
