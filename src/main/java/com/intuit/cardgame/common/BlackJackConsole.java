package com.intuit.cardgame.common;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.ai.EasyBlackjackAI;
import com.intuit.cardgame.blackjack.ai.MediumBlackjackAI;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.blackjack.states.DealerState;
import com.intuit.cardgame.blackjack.states.DealingState;
import com.intuit.cardgame.blackjack.states.EndState;
import com.intuit.cardgame.blackjack.states.PlayerState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class BlackJackConsole implements PropertyChangeListener {

    private BufferedReader console;
    private BlackJack blackJack;
    protected int quitMenuChoice = 4;

    public BlackJackConsole(BlackJack blackJack){
        console = new BufferedReader(new InputStreamReader(System.in));
        this.blackJack = blackJack;
    }

    public void run(){
        int userChoice = 0;
        while(userChoice != quitMenuChoice){
            showMenu();
        }
    }

    /**
     * Show the card game menu. Should be overriden if needed
     * @see CardGame for default implementation.
     */
    protected void showMenu() {
        //greet user and give 4 options
        System.out.println("\nWould you like to play ? Type a number to begin");
        System.out.println("1. Play");
        System.out.println("2. Setup");
        System.out.println("3. Stats");
        System.out.println("4. Quit");

        int playerChoice = getPlayerInput();
        switch (playerChoice) {
            case 1: {
                play();
                break;
            }
            case 2: {
                setup();
                break;
            }
            case 3: {
                stats();
                break;
            }
            case 4: {
                System.exit(1);
                break;
            }

        }
    }

    private void play(){
        blackJack.play();
    }

    private void setup(){
        System.out.println("Do you want to play against a Human or an AI ?");
        System.out.println("1- HUMAN  2- AI");
        int choice = getPlayerInput();
        boolean vsAI =(choice == 1 ? false : true);
        AILevel aiLevel = AILevel.MEDIUM;
        if(vsAI){
            System.out.println("What level of AI do you want ?");
            System.out.println("1- EASY  2- MEDIUM  3-HARD");
            choice = getPlayerInput();
            switch(choice){
                case 1: aiLevel = AILevel.EASY;break;
                default:
                case 2: aiLevel = AILevel.MEDIUM;break;
                case 3: aiLevel = AILevel.HARD;break;
            }
        }
        blackJack.setup(vsAI,aiLevel);

    }

    private void stats(){

    }

    private int getPlayerInput(){
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

    private void waitConfirm() {
        try {
            String line = console.readLine();
        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to determine user action from input stream");
        }
    }

    protected void wait(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("currentState")) {
            GameState state = (GameState) evt.getNewValue();
            handleStates(state);
        }
        if (evt.getPropertyName().equals("message")) {
            String message = (String) evt.getNewValue();
            writeToConsole(message);
        }
    }

    private void handleStates(GameState state){
        System.out.println("\n");
        wait(1000);
        if(state instanceof DealingState){
            DealingState();
        }
        if(state instanceof PlayerState){
            PlayerState();
        }
        if(state instanceof DealerState){
            DealerState();
        }
        if(state instanceof EndState){
           EndState();
        }
    }

    private void DealingState(){
        System.out.println("Dealing cards ...");
    }

    private void PlayerState(){
        int currentIndex = blackJack.getCurrentPlayerIndex();
        Player player = blackJack.getPlayers().get(currentIndex);
        //TODO We clear console first, in case of multiplayer we don't wanna show other player's hands
        System.out.println("It's "+player.getName()+"'s turn !");
        if( player instanceof HumanPlayer){
            handlePlayerTurn((HumanPlayer)player, blackJack);
        } else if ( player instanceof AIPlayer){
            handleAITurn((AIPlayer) player,blackJack);
        }
    }

    private void DealerState(){
        System.out.println("Time for the dealer's turn !");
    }

    void EndState(){
        System.out.println("\n******* Game Ended ********\n");
        // Just a read line to keep score tab
        waitConfirm();
    }

    private void handlePlayerTurn(HumanPlayer player, BlackJack blackJack){
        while(!player.isBusted() && !player.isEndedTurn()){
            //If player has blackJack automatically end turn (no need to ask for input)
            if(player.hasBlackJack()){
                System.out.println("You have a BLACKJACK !");
                player.setEndedTurn(true);
                return;
            }
            //Display turn info to player so he can make (hopefully) a good choice
            displayTurnInfo(player, blackJack);
            displayTurnChoices();
            int userInput = getPlayerInput();
            switch(userInput){
                case 1 : {
                    blackJack.hit(player);
                    break;
                }
                case 2 : {
                    blackJack.stand(player);
                    break;
                }
            }
        }
    }

    private void handleAITurn(AIPlayer aiPlayer, BlackJack context){
        System.out.println(aiPlayer.getName()+" is thinking...");
    }

    private void displayTurnInfo(HumanPlayer player, BlackJack blackJack){
        System.out.println(blackJack.getDealer().displayDealerHand(false));
        System.out.println(player.displayHand());
    }

    private void displayTurnChoices(){
        System.out.println("\n1- HIT  2- STAND");
    }


}
