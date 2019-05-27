package com.intuit.cardgame;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.BlackJackConsole;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BlackJack blackJack = new BlackJack();
        BlackJackConsole blackJackConsole = new BlackJackConsole(blackJack);
        // blackJackConsole is an observer to the CardGame
        blackJack.addPropertyChangeListener(blackJackConsole);
        blackJackConsole.run();
    }
}
