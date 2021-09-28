import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Chatroom {
  private ArrayList<Message> messages;//Is the messages sent up to this point.
  private ArrayList<Connection> listeners;//All the ListenClients
  private ReentrantLock lLock;
  private ReentrantLock mLock;
  /**
   * Creates a Chatroom object by instantiating ArrayList of Listeners and Messages, and creates the ReentrantLock
   */
  public Chatroom() {
    this.messages = new ArrayList<>();
    this.listeners = new ArrayList<>();
    this.lLock = new ReentrantLock();
    this.mLock = new ReentrantLock();
  }


  /**
   * Adds a Message to the messages list. (Is a critical section)
   * @param message is the message to be added
   */
  public void addMessage(Message message) {
    this.mLock.lock();
    this.messages.add(message);
    this.mLock.unlock();
  }

  /**
   * Creates a copy ArrayList of Messages, returns the reference to it. (Contains a critical section)
   * @return the copied ArrayList of Messages
   */
  public ArrayList<Message> getMessages() {
    this.mLock.lock();
    ArrayList<Message> copy = new ArrayList<>(this.messages);
    this.mLock.unlock();
    return copy;
  }

  /**
   * Adds a new ListenClient to the listeners ArrayList. (Is a critical section)
   * @param s Is the connection of the ListenerClient
   */
  public void attachListener(Connection s) {
    this.lLock.lock();
    this.listeners.add(s);
    this.lLock.unlock();
  }

  /**
   * Creates a copy ArrayList of Listeners, returns the reference to it. (Contains a critical section)
   * @return the copied ArrayList of Listeners
   */
  public ArrayList<Connection> getListeners() {
    this.lLock.lock();
    ArrayList<Connection> copy = new ArrayList<>(this.listeners);
    this.lLock.unlock();
    return copy;
  }
}
