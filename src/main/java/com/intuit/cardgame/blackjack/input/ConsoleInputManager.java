package com.intuit.cardgame.blackjack.input;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.commands.HitCommand;
import com.intuit.cardgame.blackjack.commands.StandCommand;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameContext;
import com.intuit.cardgame.common.PlayerCommand;
import com.intuit.cardgame.common.input.InputManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputManager {

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

    public int readPlayerInput(){
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

    private PlayerCommand handleBlackJack(int userInput){
        PlayerCommand command = null;
        switch(userInput){
            case 1 : command = new HitCommand(); break;
            case 2 : command = new StandCommand(); break;
        }
        return command;
    }
}
