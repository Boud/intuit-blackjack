package com.intuit.cardgame.common.cards;

import java.util.List;

public class TopCardStrategy implements NextCardStrategy{

    public Card getNextCard(List<Card> cards) {
        Card nextCard = null;
        if(!cards.isEmpty()){
            nextCard = cards.get(cards.size()-1);
        }
        return nextCard;
    }
}
