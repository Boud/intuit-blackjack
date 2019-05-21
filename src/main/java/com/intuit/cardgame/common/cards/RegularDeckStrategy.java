package com.intuit.cardgame.common.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates a regular Deck of 52 cards of each rank/ suit (no Jokers)
 *
 * @author mnajar
 */
public class RegularDeckStrategy implements DeckCreationStrategy {
    public List<Card> createDeck(){
        List<Card> cards = new ArrayList<Card>();
        for (CardSuit cardSuit : CardSuit.values()) {
            for (CardRank cardRank : CardRank.values()) {
                cards.add(new Card(cardRank, cardSuit));
            }
        }
        return cards;
    }
}
