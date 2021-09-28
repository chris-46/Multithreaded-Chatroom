import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatroomServer {
  public final static int PORT = 46464;
  private ServerSocket server;
  private Chatroom chatroom;

  /**
   * Creates a ChatroomServer object given a Chatroom
   * @param chatroom Is the chatroom object
   */
  public ChatroomServer(Chatroom chatroom) {
    this.chatroom = chatroom;
  }

  /**
   * Starts a new Server that assigns a thread to each new Connection.
   * @throws IOException
   */
  public void startServer() throws IOException {
    this.server = new ServerSocket(ChatroomServer.PORT);
    while(true){
      Connection connection = new Connection(server.accept());//Accepts connection
      Runnable r = new ChatroomWorker(connection,this.chatroom);//Passes connection to ChatroomWorker
      Thread t = new Thread(r);//Put into a thread
      t.start();//Run thread.
    }
  }

  public static void main(String[] args) throws IOException {
    // Do not change this
    new ChatroomServer(new Chatroom()).startServer();//Creates the ChatroomServer, the chatroom, and starts server.
  }
}
