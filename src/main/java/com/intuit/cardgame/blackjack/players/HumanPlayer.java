package com.intuit.cardgame.blackjack.players;

import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;

public class HumanPlayer extends BlackJackPlayer {

    public HumanPlayer(String name) {
        super(name);
    }

    public String displayHand(){
        String message = "Your Hand ("+getHandValue()+"pts) :";
        for(Card card : getHand()) {
            message+=" ["+(card.toString())+"]";
        }
        return message;
    }

}
