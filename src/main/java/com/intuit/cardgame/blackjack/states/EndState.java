package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.Dealer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;

import java.util.ArrayList;
import java.util.List;

public class EndState implements GameState {

    public void handle(CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        determineWinners(blackJack);
        blackJack.waitConfirm();
        context.setPlaying(false);
    }

    private void determineWinners(BlackJack blackJack){
        Dealer dealer = blackJack.getDealer();
        for (Player player: blackJack.getPlayers()) {
            BlackJackPlayer bjPlayer = (BlackJackPlayer) player;
            if(!bjPlayer.isBusted()){
                // Dealer is busted automatic win !
                if(dealer.isBusted()){
                    displayWinStatus(bjPlayer,"WINS !");
                } else if(bjPlayer.getHandValue() == dealer.getHandValue()){
                    displayWinStatus(bjPlayer,"TIE");
                } else if(bjPlayer.getHandValue()> dealer.getHandValue()){
                    displayWinStatus(bjPlayer,"WINS !");
                } else {
                    displayWinStatus(bjPlayer,"LOSES...");
                }
            } else {
                displayWinStatus(bjPlayer,"BUSTED...");
            }

        }
    }

    void displayWinStatus(BlackJackPlayer player, String status){
        System.out.println("Player "+player.getName()+"("+player.getHandValue()+"pts) : "+status);
    }
}
