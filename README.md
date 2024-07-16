## Java Multithreaded Chatroom

### Components

1. **Chatroom.java**
   - Manages the list of messages and client connections.
   - Uses `ReentrantLock` for thread safety.
   - Provides methods to add messages (`addMessage`), retrieve messages (`getMessages`), attach listeners (`attachListener`), and get listeners (`getListeners`).

2. **ChatroomServer.java**
   - Starts a server socket (`ServerSocket`) at a specified port.
   - Accepts incoming client connections and assigns each to a new thread (`ChatroomWorker`) to handle communication.
   - Main method (`main`) initializes the chatroom server and starts listening for client connections.

3. **ChatroomWorker.java**
   - Implements `Runnable` to handle communication with individual clients.
   - Receives commands from clients (`LISTEN` or `SPEAK`).
   - Methods include `sendMessage`, `handleMessage`, `handleListen`, and `handleSpeak` to manage message sending and receiving among clients.

4. **ListenClient.java**
   - Extends `Client` and represents a client that listens to messages from the chatroom.
   - Connects to the server, sends a `LISTEN` command, and continuously receives and prints messages to the console.

5. **SpeakClient.java**
   - Extends `Client` and represents a client that sends messages to the chatroom.
   - Connects to the server, sends a `SPEAK` command, and continuously prompts the user for messages to send.

6. **Client.java**
   - Provides a method to connect a client to the chatroom server at a specified host and port (`ChatroomServer.PORT`).

7. **Connection.java**
   - Wraps a `Socket` and provides methods (`readInt`, `readUTF`, `writeInt`, `writeUTF`, `writeMessage`, `close`) for reading from and writing to the socket.
   - Ensures messages are sent and received with flushing for synchronization.

8. **Message.java**
   - Represents a message with a sender (`who`) and content (`message`).
   - Provides methods to get the sender (`getWho`) and message content (`getMessage`).
   - Overrides `toString` to format the message for printing.

9. **ChatroomProtocol.java**
   - Defines constants for chatroom protocol commands (`MESSAGE`, `LISTEN`, `SPEAK`).

### How to Use

1. **Compile**
   - Compile all Java files (`Chatroom.java`, `ChatroomServer.java`, `ChatroomWorker.java`, `ListenClient.java`, `SpeakClient.java`, `Client.java`, `Connection.java`, `Message.java`, `ChatroomProtocol.java`).

2. **Run Server**
   - Execute `ChatroomServer` to start the chatroom server.

3. **Connect Clients**
   - Clients can connect to the server using `ListenClient` or `SpeakClient`.
   - Use commands (`LISTEN` or `SPEAK`) to interact with the chatroom.

### Example Usage

1. Start the server:
   ```bash
   java ChatroomServer
