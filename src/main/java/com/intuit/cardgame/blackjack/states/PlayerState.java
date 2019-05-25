package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.commands.HitCommand;
import com.intuit.cardgame.blackjack.commands.StandCommand;
import com.intuit.cardgame.blackjack.input.ConsoleInputManager;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PlayerState implements GameState {

    public void handle(CardGame context) {
        int currentIndex = context.getCurrentPlayerIndex();
        Player player = context.getPlayers().get(currentIndex);
        //We clear console first, in case of multiplayer we don't wanna show other player's hands
//        System.out.flush();
        System.out.println("It's "+player.getName()+"'s turn !");
        if( player instanceof HumanPlayer){
            handlePlayerTurn((HumanPlayer)player, (BlackJack)context);
        } else if ( player instanceof AIPlayer){
            handleAITurn((AIPlayer) player,context);
        }
        // Determine next state depending on remaining players
        if(currentIndex < context.getPlayers().size() -1) {
            currentIndex++;
            context.setCurrentPlayerIndex(currentIndex);
            context.setCurrentState(new PlayerState());
        } else {
            //No more players, go to dealer's state
            context.setCurrentState(new DealerState());
        }


    }

    private void handlePlayerTurn(HumanPlayer player, BlackJack blackJack){

        if(player.isBusted() || player.isEndedTurn()){
            return;
        }
        //Display turn info to player so he can make (hopefully) a good choice
        displayTurnInfo(player, blackJack);
        displayTurnChoices();
        ConsoleInputManager consoleInput = ConsoleInputManager.getInstance();
        int userInput = consoleInput.readPlayerInput();
        switch(userInput){
            case 1 : {
                blackJack.hit(player);
                handlePlayerTurn(player,blackJack);
                break;
            }
            case 2 : {
                blackJack.stand(player);
                break;
            }
        }

    }

    private void handleAITurn(AIPlayer aiPlayer, CardGame context){
        System.out.println(aiPlayer.getName()+" is thinking...");
        aiPlayer.playTurn(context);
    }

    //To Remove since UI info must be decoupled
    private void displayTurnInfo(HumanPlayer player, BlackJack blackJack){
        System.out.println(blackJack.getDealer().displayDealerHand(false));
        System.out.println(player.displayHand());
        System.out.println("Total Hand Value : "+player.getHandValue());
    }

    private void displayTurnChoices(){
        System.out.println("\n1- HIT  2- STAND");
    }


}
