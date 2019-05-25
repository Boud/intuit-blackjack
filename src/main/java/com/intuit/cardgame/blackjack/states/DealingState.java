package com.intuit.cardgame.blackjack.states;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameState;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;

public class DealingState implements GameState {

    public void handle(CardGame context) {
        BlackJack blackJack = (BlackJack) context;
        System.out.println("Dealing cards ...");
        Deck deck = new Deck();
        deck.generateDeck();
        deck.shuffle();
        blackJack.setDeck(deck);

        // Give each player two cards
        for (Player player:blackJack.getPlayers()) {
            player.addCard( deck.getNextCard(),false);
            player.addCard( deck.getNextCard(),false);
        }
        // Add two cards to dealer (one face up/ one face down)
        blackJack.getDealer().getHand().add( deck.getNextCard());
        blackJack.getDealer().getHand().add( deck.getNextCard());

        //Go to next state (first player's turn)
        blackJack.setCurrentState(new PlayerState());

    }
}
