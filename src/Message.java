public class Message {
  private final String who;
  private final String message;

  /**
   * Creates a Message object given the sender and content of the message.
   * @param who is the sender of the Message
   * @param message is the content of the Message
   */
  public Message(String who, String message) {
    this.who = who;
    this.message = message;
  }

  public String getWho() {
    return who;
  }

  public String getMessage() {
    return message;
  }

  /**
   * Turns a Message into a formatted String ready for the printing required in this assignment.
   * @return the formatted String
   */
  public String toString() {
    return this.who + " -> " + this.message;
  }
}
