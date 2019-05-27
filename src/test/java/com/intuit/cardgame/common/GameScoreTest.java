package com.intuit.cardgame.common;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameScoreTest {

    private GameScore gameScore;

    @Before
    public void init() {
        gameScore = new GameScore();
    }

    @Test
    public void addWin(){
        HumanPlayer player1 = new HumanPlayer("Boud");
        gameScore.addWin(player1);
        assertEquals(Integer.valueOf(1),gameScore.getWins(player1));
        gameScore.addWin(player1);
        assertEquals(Integer.valueOf(2),gameScore.getWins(player1));
        HumanPlayer player2 = new HumanPlayer("Tom Waits");
        assertEquals(Integer.valueOf(0),gameScore.getWins(player2));
    }

}