package com.austinuwate.LocalNetwork.ChatRooms.ClientPackage.Classes;

import com.austinuwate.LocalNetwork.ChatRooms.ClientPackage.Interfaces.ClientUserInteraction;

import java.util.Scanner;

/**
 * ClientInterface: The ClientInterface class is only interested in the frontend
 * operations of chatting with other users. Specifically, it will be dealing with
 * user needs, such as sending messages and printing onto the screen.
 * Currently, ClientInterface will only be doing console commands. Later, with JavaFX,
 * I will extend this class to include GUI interfaces.
 */
public class ClientInterface implements ClientUserInteraction {

    private final Scanner userInput = new Scanner(System.in);

    @Override
    public void printMessage( String message ) {

        System.out.println(message);

    }

    @Override
    public String writeMessage() {

        return ( userInput.nextLine() );

    }

    public String askForUsername() {
        return ( "~/"+userInput.nextLine() );
    }

    @Override
    public void close() {

        System.exit(0);

    }

    @Override
    public String preOperations () {

        System.out.println("Enter your username:");

        return askForUsername();

    }

}
