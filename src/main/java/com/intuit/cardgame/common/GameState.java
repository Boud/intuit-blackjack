package com.intuit.cardgame.common;

/**<p>
 * Represents a Card Game State
 * <br> For example for Blackjack could be Dealing State or Player State
 *</p>
 * @author mnajar
 */
public interface GameState {
    void handle(CardGame context);

    //Added this method here, to make sure that all states implements it and dev doesn't forget
//    void nextState(CardGame context);
}
