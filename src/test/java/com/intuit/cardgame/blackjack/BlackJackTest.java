package com.intuit.cardgame.blackjack;

import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.states.DealingState;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Deck;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BlackJackTest {

    private BlackJack blackJack;

    @Before
    public void init() {
        blackJack = new BlackJack();
        blackJack.initGame();
    }

    @Test
    public void bustPlayer(){
        blackJack.setCurrentState(new DealingState());
        blackJack.getCurrentState().handle(blackJack);
        BlackJackPlayer player = (BlackJackPlayer) blackJack.getCurrentPlayer();
        assertNotNull(player);
        while(player.getHandValue()<= 21){
            blackJack.hit(player);
        }
        assertTrue(player.isBusted());
    }

    @Test
    public void stand(){
        blackJack.setCurrentState(new DealingState());
        blackJack.getCurrentState().handle(blackJack);
        BlackJackPlayer player = (BlackJackPlayer) blackJack.getCurrentPlayer();
        assertNotNull(player);
        blackJack.stand(player);
        assertTrue(player.isEndedTurn());
    }

}