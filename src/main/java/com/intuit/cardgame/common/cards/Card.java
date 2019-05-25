package com.intuit.cardgame.common.cards;
/**
 * Class representing a game card
 *
 * @author mnajar
 */
public class Card {

    private final CardRank rank;
    private final CardSuit suit;
    // Is card shown (facing down or up ?)
    private boolean shown;

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

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    @Override
    public String toString() {
        return rank.toString() + " OF " + suit.toString();
    }
}
