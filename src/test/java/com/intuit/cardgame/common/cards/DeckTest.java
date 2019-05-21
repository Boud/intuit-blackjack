package com.intuit.cardgame.common.cards;

import static org.junit.Assert.*;
import org.junit.Test;

public class DeckTest {

    @Test
    public void generateDeck() {
        Deck deck = new Deck();
        deck.generateDeck();
        assertEquals(52,deck.size());
    }

    @Test
    public void getNextCard() {
    }
}