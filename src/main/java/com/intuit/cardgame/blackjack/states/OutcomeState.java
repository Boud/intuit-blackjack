

package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.Dealer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;

import java.util.ArrayList;
import java.util.List;

public class OutcomeState implements GameState {

    public void handle(CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        blackJack.sendMessage("****Determining Outcome ******");
        blackJack.sendMessage(blackJack.getDealer().displayDealerHand(true));
        determineWinners(blackJack);
        context.setCurrentState(new EndState());
    }

    private void determineWinners(BlackJack blackJack) {
        Dealer dealer = blackJack.getDealer();
        for (Player player : blackJack.getPlayers()) {
            BlackJackPlayer bjPlayer = (BlackJackPlayer) player;
            if (!bjPlayer.isBusted()) {
                // Dealer is busted automatic win !
                if (dealer.isBusted()) {
                    displayWinStatus(blackJack, bjPlayer, "WINS !");
                    blackJack.getGameScore().addWin(bjPlayer);
                } else if (bjPlayer.getHandValue() == dealer.getHandValue()) {
                    displayWinStatus(blackJack, bjPlayer, "TIE");
                } else if (bjPlayer.getHandValue() > dealer.getHandValue()) {
                    displayWinStatus(blackJack, bjPlayer, "WINS !");
                    blackJack.getGameScore().addWin(bjPlayer);
                } else {
                    displayWinStatus(blackJack, bjPlayer, "LOSES...");
                }
            } else {
                displayWinStatus(blackJack, bjPlayer, "BUSTED...");
            }

        }
    }

    void displayWinStatus(BlackJack blackJack, BlackJackPlayer player, String status) {
        blackJack.sendMessage("Player " + player.getName() + "(" + player.getHandValue() + "pts) : " + status);
    }
}