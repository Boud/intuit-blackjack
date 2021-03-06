package com.intuit.cardgame.blackjack.ai;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.ai.AIStrategy;

/**
 * Medium BlackJack AI Strategy
 *
 * @author mnajar
 */
public class MediumBlackjackAI implements AIStrategy {

    // Medium AI Stops at 17 if possible
    public void playTurn(Player player, CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        BlackJackPlayer bjPlayer = (BlackJackPlayer) player;
        if(bjPlayer.getHandValue() == 21){
            blackJack.stand(bjPlayer);
        } else {
            while (bjPlayer.getHandValue() < 17){
                blackJack.hit(bjPlayer);
            }
            if(!bjPlayer.isBusted()){
                blackJack.stand(bjPlayer);
            }
        }

    }
}
