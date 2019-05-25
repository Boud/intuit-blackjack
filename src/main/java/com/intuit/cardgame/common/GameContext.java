package com.intuit.cardgame.common;

import java.util.List;

public class GameContext {

    private List<Player> players;
    private GameState currentState;
    private Player currentPlayer;

    public GameContext(GameState initialState){
        this.currentState = initialState;
    }



}
