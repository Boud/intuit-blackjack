package com.intuit.cardgame.common.ai;

import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;

public interface AIStrategy {

    void playTurn(Player player, CardGame context);
}
