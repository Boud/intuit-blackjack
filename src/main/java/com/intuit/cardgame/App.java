package com.intuit.cardgame;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.ConsoleClient;
import com.intuit.cardgame.common.CardGame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ConsoleClient consoleClient = new ConsoleClient();
        CardGame cardGame = new BlackJack();
        // consoleClient is an observer to the CardGame
        cardGame.addPropertyChangeListener(consoleClient);

        cardGame.play();
    }
}
