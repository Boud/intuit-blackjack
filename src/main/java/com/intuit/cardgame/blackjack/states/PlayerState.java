package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.input.ConsoleInputManager;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;

public class PlayerState implements GameState {

    public void handle(CardGame context) {
        int currentIndex = context.getCurrentPlayerIndex();
        Player player = context.getPlayers().get(currentIndex);
        //TODO We clear console first, in case of multiplayer we don't wanna show other player's hands
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
            ConsoleInputManager consoleInput = ConsoleInputManager.getInstance();
            int userInput = consoleInput.getUserInput();
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

    private void handleAITurn(AIPlayer aiPlayer, CardGame context){
        System.out.println(aiPlayer.getName()+" is thinking...");
        aiPlayer.playTurn(context);
    }

    //To Remove since UI info must be decoupled
    private void displayTurnInfo(HumanPlayer player, BlackJack blackJack){
        System.out.println(blackJack.getDealer().displayDealerHand(false));
        System.out.println(player.displayHand());
    }

    private void displayTurnChoices(){
        System.out.println("\n1- HIT  2- STAND");
    }


}
