package com.intuit.cardgame.blackjack.players;

import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.ai.AIStrategy;

public class AIPlayer extends BlackJackPlayer {

    private AIStrategy aiStrategy;

    public AIPlayer(String name,  AIStrategy aiStrategy) {
        super(name);
        this.aiStrategy = aiStrategy;
    }

    public void playTurn(CardGame context){
        aiStrategy.playTurn(this,context );
    }
}
