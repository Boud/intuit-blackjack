package com.intuit.cardgame.common.ai;

import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;

/**
 * Interface for card game AI Strategies
 *
 * @author mnajar
 */
public interface AIStrategy {

    void playTurn(Player player, CardGame context);
}
