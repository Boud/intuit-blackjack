package com.intuit.cardgame.common.cards;
/**
 * Class representing a game card
 *
 * @author mnajar
 */
public class Card {

    private final CardRank rank;
    private final CardSuit suit;

    public Card(CardRank rank, CardSuit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }
}
