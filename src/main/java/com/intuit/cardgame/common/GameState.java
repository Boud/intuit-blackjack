package com.intuit.cardgame.common;

public interface GameState {
    void handle(CardGame context);

    //Added this method here, to make sure that all states implements it and dev doesn't forget
//    void nextState(CardGame context);
}
