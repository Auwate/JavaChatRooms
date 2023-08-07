package com.austinuwate.LocalNetwork.ChatRooms.ServerPackage.Classes;

import com.austinuwate.LocalNetwork.ChatRooms.ChatRoomPackage.Classes.ChatRoomHandler;
import com.austinuwate.LocalNetwork.ChatRooms.ClientServerHandlerPackage.Classes.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server: The Server class is designed to fulfill requests from various processes and establish
 * incoming connections.
 */
public class Server implements Runnable {

    private final ServerSocket serverSocket;
    private boolean requestServerToClose = false;

    public Server (ServerSocket serverSocket) {

        this.serverSocket = serverSocket;

    }

    /**
     * isRequestedToClose(): This method returns the current value of requestServerToClose,
     * which is used in the startServer() method.
     * @return boolean -> Current value of requestServerToClose.
     */
    public boolean isRequestedToClose () {
        return this.requestServerToClose;
    }

    /**
     * requestClose(): This method is to help test the Server class, especially with
     * the startServer() method.
     */
    public void requestClose() {
        this.requestServerToClose = true;
    }

    /**
     * startServer (): Most important method, as it is where the Server will be most of the time.
     * Specifically, it connects clients to the ServerSocket and redirects them.
     */
    public boolean startServer () {

        try {

            ChatRoomHandler room = new ChatRoomHandler("DefaultChatRoom");

            while (!serverSocket.isClosed() && !isRequestedToClose()) {

                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(room, socket);
                Thread thread = new Thread (clientHandler);
                thread.start();

            }

        }

        catch ( IOException exception ) {

            closeEverything();

        }

        return true;

    }

    /**
     * closeEverything (): If exceptions occur where the Server is operating, try to close everything.
     */
    public void closeEverything () {

        try {

            /*
            Avoid errors
             */
            if (!(serverSocket == null)) {

                serverSocket.close();

            }

        }

        catch (IOException exception) {

            exception.printStackTrace();

        }

    }

    /**
     * Main: Simply attempts to create a ServerSocket and start the server
     * @param args -> Arguments that are currently ignored
     */
    public static void main (String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(1234);
            Server server = new Server(serverSocket);

            Thread thread = new Thread (server);
            thread.start();

        }

        catch (IOException exception) {

            exception.printStackTrace();

        }

    }

    /**
     * run(): This method is used to start the Server, which allows Clients to start
     * joining.
     */
    @Override
    public void run() {

        startServer();

    }

}
