package com.intuit.cardgame.blackjack;

import com.intuit.cardgame.blackjack.ai.EasyBlackjackAI;
import com.intuit.cardgame.blackjack.ai.HardBlackjackAI;
import com.intuit.cardgame.blackjack.ai.MediumBlackjackAI;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.Dealer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.blackjack.states.DealingState;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.ai.AIStrategy;
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;
import com.intuit.cardgame.common.input.ConsoleInputManager;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {

    private boolean vsAI = true;
    private AIStrategy AILevel = new EasyBlackjackAI();


    private Dealer dealer;

    public BlackJack() {
        // This should definitively be done through Spring's IOC / DI
        inputManager = ConsoleInputManager.getInstance();
    }

    @Override
    protected void initGame() {
        //init Dealer
        dealer = new Dealer("Dealer");
        players.add(new HumanPlayer("Player 1"));
        if(vsAI){
            players.add(new AIPlayer("Asimov", new MediumBlackjackAI()));
        } else {
            players.add(new HumanPlayer("Player 2"));
        }
        // Start with initial Dealing State
        setCurrentState(new DealingState());
        playing = true;
    }

    @Override
    protected void reset() {
        players = new ArrayList<Player>();
        dealer = new Dealer("Dealer");
        currentPlayerIndex = 0;
    }

    @Override
    public void stats() {

    }

    @Override
    public void setup() {
        System.out.println("Do you want to play against a Human or an AI ?");
        System.out.println("1- HUMAN  2- AI");
        int choice = inputManager.getUserInput();
        vsAI =(choice == 1 ? false : true);
        if(vsAI){
            System.out.println("What level of AI do you want ?");
            System.out.println("1- EASY  2- MEDIUM  3-HARD");
            choice = inputManager.getUserInput();
            switch(choice){
                case 1: AILevel = new EasyBlackjackAI();break;
                default:
                case 2: AILevel = new MediumBlackjackAI();break;
                case 3: AILevel = new HardBlackjackAI();break;
            }
        }
    }

    public void stand(BlackJackPlayer player) {
        System.out.println("Player " + player.getName() + " stands...");
        player.setEndedTurn(true);
    }

    public void hit(BlackJackPlayer player) {
        Deck deck = getDeck();
        Card card = deck.getNextCard();
        if (card != null) {
            player.getHand().add(card);
            if (player instanceof HumanPlayer || player instanceof Dealer) {
                System.out.println("Player " + player.getName() + " drew [" + card.toString() + "]");
            } else {
                System.out.println("Player " + player.getName() + " hits...");
            }
            if (player.getHandValue() > 21) {
                System.out.println("Ouch Player " + player.getName() + " is busted !");
                player.setBusted(true);
            }


        }
    }

    public Dealer getDealer() {
        return dealer;
    }

}
