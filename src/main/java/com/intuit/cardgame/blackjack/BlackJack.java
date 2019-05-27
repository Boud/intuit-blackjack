package com.intuit.cardgame.blackjack;

import com.intuit.cardgame.blackjack.ai.EasyBlackjackAI;
import com.intuit.cardgame.blackjack.ai.HardBlackjackAI;
import com.intuit.cardgame.blackjack.ai.MediumBlackjackAI;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.Dealer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.blackjack.states.DealingState;
import com.intuit.cardgame.common.AILevel;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.ai.AIStrategy;
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {

    private Dealer dealer;

    public BlackJack() {
        AILevel = new EasyBlackjackAI();
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
    public void setup(boolean vsAI, AILevel aiLevel) {
        this.vsAI = vsAI;
        switch(aiLevel){
            case EASY: AILevel = new EasyBlackjackAI();break;
            default:
            case MEDIUM: AILevel = new MediumBlackjackAI();break;
            case HARD: AILevel = new HardBlackjackAI();break;
        }
    }

    public void stand(BlackJackPlayer player) {
        sendMessage("Player " + player.getName() + " stands...");
        player.setEndedTurn(true);
    }

    public void hit(BlackJackPlayer player) {
        Deck deck = getDeck();
        Card card = deck.getNextCard();
        if (card != null) {
            player.getHand().add(card);
            if (player instanceof HumanPlayer || player instanceof Dealer) {
                sendMessage("Player " + player.getName() + " drew [" + card.toString() + "]");
            } else {
                sendMessage("Player " + player.getName() + " hits...");
            }
            if (player.getHandValue() > 21) {
                sendMessage("Ouch Player " + player.getName() + " is busted !");
                player.setBusted(true);
            }


        }
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }
}
