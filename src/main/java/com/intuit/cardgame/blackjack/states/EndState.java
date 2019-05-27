

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
        context.setPlaying(false);
    }

}