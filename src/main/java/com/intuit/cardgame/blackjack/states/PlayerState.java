package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.PlayerCommand;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerState implements GameState {

    private Queue<PlayerCommand> playerCommands = new LinkedList<PlayerCommand>();

    public void handle(CardGame context) {
        int currentIndex = context.getCurrentPlayerIndex();
        Player player = context.getPlayers().get(currentIndex);
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
        //TODO use commands
        while(!player.isBusted() && !player.isEndedTurn()){
            PlayerCommand command = playerCommands.poll();
            if(command != null){
                command.execute(player, blackJack);
            }
        }

    }

    private void handleAITurn(AIPlayer aiPlayer, CardGame context){
        //context.sendMessage(aiPlayer.getName()+" is thinking...");
        aiPlayer.playTurn(context);
    }

}
