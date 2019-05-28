package com.intuit.cardgame.blackjack.ai;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.ai.AIStrategy;

/**
 * Hard BlackJack AI Strategy
 *
 * @author mnajar
 */
public class HardBlackjackAI implements AIStrategy {

    // TODO Hard AI cheats :p
    public void playTurn(Player player, CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        BlackJackPlayer bjPlayer = (BlackJackPlayer) player;
        if (bjPlayer.getHandValue() == 21) {
            blackJack.stand(bjPlayer);
        } else {
            while (bjPlayer.getHandValue() < 17) {
                blackJack.hit(bjPlayer);
            }
            if (!bjPlayer.isBusted()) {
                blackJack.stand(bjPlayer);
            }
        }

    }
}