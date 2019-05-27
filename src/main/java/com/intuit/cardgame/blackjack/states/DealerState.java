package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;

public class DealerState implements GameState {

    public void handle(CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        blackJack.sendMessage(blackJack.getDealer().displayDealerHand(true));
        blackJack.getDealer().playTurn(blackJack);
        context.setCurrentState(new OutcomeState());
    }
}
