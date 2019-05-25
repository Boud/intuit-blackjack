package com.intuit.cardgame.common.cards;

/**
 * An enum representing the type of card suits (hearts, diamonds...)
 *
 * @author mnajar
 */
public enum CardSuit {
    HEARTS('\u2764'),
    SPADES('\u2660'),
    DIAMONDS('\u2666'),
    CLUBS('\u2663');

    private char symbol;

    CardSuit(char symbol) {
        this.symbol = symbol;
    }

    /*public String toString(){
        return String.valueOf(this.symbol);
    }*/

    /*@Override
    public String toString(){
        return this.name().toLowerCase();
    }*/
}
