package com.intuit.cardgame.common.cards;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {

    private Deck deck;

    @Before
    public void init() {
        deck = new Deck();
        deck.generateDeck();
    }

    @Test
    public void generateDeck() {
        deck = new Deck();
        deck.generateDeck();
        assertEquals(52,deck.size());
    }

    @Test
    public void getCard() {
        int expectedSize = deck.size() - 1;
        Card card = deck.getNextCard();
        assertNotNull(card);
        assertEquals(expectedSize,deck.size());
        System.out.println(card.toString());
    }

    @Test
    public void getAllCards() {
        while(deck.size()!= 0){
            Card card = deck.getNextCard();
            assertNotNull(card);
        }
        assertEquals(0,deck.size());
        assertNull(deck.getNextCard());
    }

    /*@Test
    public void shuffle() {
        Card cardBeforeShuffle = deck.peekNextCard();
        deck.shuffle();
        Card cardAfterShuffle = deck.peekNextCard();
        assertNotEquals(cardBeforeShuffle,cardAfterShuffle);
    }*/
}