import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatroomWorker implements Runnable {
  private Chatroom chatroom;
  private Connection connection;

    /**
     * Creates a ChatroomWorker given a Connection and the Chatroom
     * @param connection is the Connection of the Client the ChatroomWorker is assigned to.
     * @param chatroom is the Chatroom
     */
    public ChatroomWorker(Connection connection,Chatroom chatroom) {
        this.connection=connection;
        this.chatroom=chatroom;
    }

    /**
     * Calls the Connection class's writeMessage method in order to write a message to the ListenerClient.
     * @param m is the Message to be sent.
     * @throws IOException
     */
    public void sendMessage(Message m) throws IOException {//Sends a message to a new Listener as a response to Listen.
          this.connection.writeMessage(m);
  }

    /**
     * Receives a Message from the SpeakClient the worker is assigned to, updates the messages ArrayList, and sends new Message to all ListenerClients.
     * @throws IOException
     */
  public void handleMessage() throws IOException {
      String who = this.connection.readUTF();
      String message = this.connection.readUTF();
      Message m = new Message(who,message);
      this.chatroom.addMessage(m);//Add new Message to AL<Message> messages.
      for(Connection c : this.chatroom.getListeners()){//Sends message to all listeners.
        c.writeMessage(m);
      }
  }

    /**
     * Handles the Listen command sent by a ListenClient(LC) on connection by adding the LC's Connection to listeners, and sending all Messages in history to LC.
     * @throws IOException
     */
  public void handleListen() throws IOException {//handles new listener by keeping all messages up to date.
      this.chatroom.attachListener(this.connection);
      for(Message m : this.chatroom.getMessages()) {//Sends all Messages in history to LC
          this.sendMessage(m);
      }
  }

    /**
     * Handles the speak command sent by a SpeakClient(SC) on connection by continuously waiting for a Message to be sent by the SC, calling the handleMessage method when needed.
     * @throws IOException
     */
  public void handleSpeak() throws IOException {
    while(true){//We don't consider termination.
        int cmd = this.connection.readInt();
        if(cmd == ChatroomProtocol.MESSAGE){
            this.handleMessage();//Handles the message command
        }
    }
  }


    /**
     * Is the run method implemented from the Runnable interface. Takes the command, determines which handle methods to run.
     */
  public void run(){
      int cmd = -1;//Just default to a non-valid command value.
          try {
              cmd = this.connection.readInt();
              switch (cmd) {
                  case ChatroomProtocol.LISTEN:
                      this.handleListen();
                      break;
                  case ChatroomProtocol.SPEAK:
                      this.handleSpeak();
                      break;
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
  }
}
