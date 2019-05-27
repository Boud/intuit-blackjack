package com.intuit.cardgame.blackjack.players;

import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.CardRank;
import com.intuit.cardgame.common.cards.CardSuit;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    private HumanPlayer player;

    @Before
    public void init() {
        player = new HumanPlayer("Boud");
    }

    @Test
    public void calculateHandValueNoAce() {
        List<Card> hand = player.getHand();
        hand.add(new Card(CardRank.JACK, CardSuit.HEARTS));
        hand.add(new Card(CardRank.EIGHT, CardSuit.HEARTS));
        assertEquals(18,player.getHandValue());
    }

    @Test
    public void calculateHandOneAceLess21() {
        List<Card> hand = player.getHand();
        hand.add(new Card(CardRank.TWO, CardSuit.HEARTS));
        hand.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(18,player.getHandValue());
    }

    @Test
    public void calculateHandOneAceMore21() {
        List<Card> hand = player.getHand();
        hand.add(new Card(CardRank.TEN, CardSuit.HEARTS));
        hand.add(new Card(CardRank.KING, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(21,player.getHandValue());
    }

    @Test
    public void calculateHandTwoAces() {
        List<Card> hand = player.getHand();
        hand.add(new Card(CardRank.JACK, CardSuit.HEARTS));
        hand.add(new Card(CardRank.EIGHT, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(20,player.getHandValue());
    }

}