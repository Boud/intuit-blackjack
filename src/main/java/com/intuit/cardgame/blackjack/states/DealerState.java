package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;

public class DealerState implements GameState {

    public void handle(CardGame context) {
        System.out.println("Time for the dealer's turn !");
        BlackJack blackJack = (BlackJack) context;
        System.out.println(blackJack.getDealer().displayDealerHand(true));
        blackJack.getDealer().playTurn(blackJack);
        System.out.println(blackJack.getDealer().displayDealerHand(true));
        context.setCurrentState(new EndState());
    }
}
