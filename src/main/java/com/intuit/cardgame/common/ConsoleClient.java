package com.intuit.cardgame.common;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleClient implements PropertyChangeListener {

    private BufferedReader console;

    public ConsoleClient(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    private int readPlayerInput(){
        int userInput = -1;
        try {
            String line = console.readLine();
            userInput = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            writeToConsole("Your input is not a valid number !");

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to determine user action from input stream");
        }
        return userInput;
    }

    private void writeToConsole(String message){
        System.out.println(message);
    }


    public void propertyChange(PropertyChangeEvent evt) {
        String message = (String) evt.getNewValue();
        writeToConsole(message);
    }
}
