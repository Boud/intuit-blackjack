package com.intuit.cardgame.common.cards;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a deck of cards
 *
 * @author mnajar
 */
public class ComplicatedDeck {

    //Using an array list instead of a stack in case we have different card picking strategies
    // other than get the one on top
    private List<Card> cards = new ArrayList<Card>();

    private NextCardStrategy nextCardStrategy;

    public ComplicatedDeck(){
        //By default we use get card on top strategy
        nextCardStrategy = new TopCardStrategy();
    }

    public Card getNextCard(){
        return nextCardStrategy.getNextCard(cards);
    }

}


