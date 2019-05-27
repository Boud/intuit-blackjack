

package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;

public class EndState implements GameState {

    public void handle(CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        context.setPlaying(false);
    }

}