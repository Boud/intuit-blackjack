package com.intuit.cardgame.blackjack;

import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.CardRank;
import com.intuit.cardgame.common.cards.CardSuit;
import com.intuit.cardgame.common.cards.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BlackJackTest {

    private BlackJack blackJack;

    @Before
    public void init() {
        blackJack = new BlackJack();
    }

    @Test
    public void calculateHandValueNoAce() {
        List hand = new ArrayList<Card>();
        hand.add(new Card(CardRank.JACK, CardSuit.HEARTS));
        hand.add(new Card(CardRank.EIGHT, CardSuit.HEARTS));
        assertEquals(18,blackJack.calculateHandValue(hand));
    }

    @Test
    public void calculateHandOneAceLess21() {
        List hand = new ArrayList<Card>();
        hand.add(new Card(CardRank.TWO, CardSuit.HEARTS));
        hand.add(new Card(CardRank.FIVE, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(18,blackJack.calculateHandValue(hand));
    }

    @Test
    public void calculateHandOneAceMore21() {
        List hand = new ArrayList<Card>();
        hand.add(new Card(CardRank.TEN, CardSuit.HEARTS));
        hand.add(new Card(CardRank.KING, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(21,blackJack.calculateHandValue(hand));
    }

    @Test
    public void calculateHandTwoAces() {
        List hand = new ArrayList<Card>();
        hand.add(new Card(CardRank.JACK, CardSuit.HEARTS));
        hand.add(new Card(CardRank.EIGHT, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        hand.add(new Card(CardRank.ACE, CardSuit.HEARTS));
        assertEquals(20,blackJack.calculateHandValue(hand));
    }

//    @Test
//    public void play(){
//        blackJack.play();
//    }
}