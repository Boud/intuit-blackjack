package com.intuit.cardgame.common.cards;

import java.util.List;

public interface NextCardStrategy {
    public Card getNextCard(List<Card> cards);
}
