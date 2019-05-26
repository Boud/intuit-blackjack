package com.intuit.cardgame.common.input;

import com.intuit.cardgame.blackjack.commands.HitCommand;
import com.intuit.cardgame.blackjack.commands.StandCommand;
import com.intuit.cardgame.common.PlayerCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager implements InputManager{

    private static ConsoleInputManager instance;

    private BufferedReader console;

    private ConsoleInputManager(){
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public static ConsoleInputManager getInstance()
    {
        if (instance == null)
            instance = new ConsoleInputManager();

        return instance;
    }

    public int getUserInput(){
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
}
