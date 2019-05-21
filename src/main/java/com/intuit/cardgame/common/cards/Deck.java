package com.intuit.cardgame.common.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a deck of cards
 *
 * @author mnajar
 */
public class Deck {

    //Using an array list instead of a stack in case we have different card picking strategies
    // other than get the one on top
    private List<Card> cards = new ArrayList<Card>();


    private DeckCreationStrategy deckCreationStrategy;

    public Deck(){
        //By default we create a regular deck
        deckCreationStrategy = new RegularDeckStrategy();
    }

    public Deck(DeckCreationStrategy deckCreationStrategy){
        this.deckCreationStrategy = deckCreationStrategy;
    }

    public void generateDeck(){
        this.cards = this.deckCreationStrategy.createDeck();
    }

    public Card getNextCard(){
        Card nextCard = null;
        if(!cards.isEmpty()){
            nextCard = cards.get(cards.size()-1);
        }
        return nextCard;
    }

    public int size(){
        return cards.size();
    }

}


