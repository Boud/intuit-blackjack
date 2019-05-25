package com.intuit.cardgame.common.ai;

import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;

import java.util.List;

public interface AIStrategy {

    public void playTurn(Player player, CardGame context);
}
