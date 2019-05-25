package com.intuit.cardgame.blackjack;

import com.intuit.cardgame.blackjack.ai.EasyBlackjackAI;
import com.intuit.cardgame.blackjack.ai.MediumBlackjackAI;
import com.intuit.cardgame.blackjack.players.AIPlayer;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.blackjack.players.Dealer;
import com.intuit.cardgame.blackjack.players.HumanPlayer;
import com.intuit.cardgame.blackjack.states.DealingState;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends CardGame {

    private Dealer dealer;

    public BlackJack(){
       // currentState =
    }

    protected void initGame() {
        //init Dealer
        dealer = new Dealer("Dealer");

        //For now add one human player and one AI
        players.add(new HumanPlayer("Boud"));
        players.add(new AIPlayer("MisterMV", new MediumBlackjackAI()));
        // Start with initial Dealing State
        setCurrentState(new DealingState());
        playing = true;
    }

    protected void reset() {
//        dealerHand = new ArrayList<Card>();
        players = new ArrayList<Player>();
        dealer = new Dealer("Dealer");
    }

    @Override
    public void gameRules() {

    }

    public void showTitle(){
        System.out.println("Blackjack by Mourad Najar");
    }

    public void init(boolean versusAI, int playerNumber){
        if(versusAI){

        }


    }

    /*public String displayDealerHand(boolean displayAll){
        String message = "Dealer's hand : ";
        if(dealerHand != null && !dealerHand.isEmpty()){
            for (int i=0;i<dealerHand.size();i++){
                message += displayAll || i == 0
                        ? "["+dealerHand.get(0).toString()+"] "
                        : "[Hidden] ";
            }
        }
        return message;
    }*/

    public int calculateHandValue(List<Card> hand){
        int numberOfAces = 0;
        int handValue = 0;
        for(Card card : hand) {
            switch (card.getRank()){
                case ACE: numberOfAces++; break;
                case TWO: handValue += 2; break;
                case THREE: handValue += 3; break;
                case FOUR: handValue += 4; break;
                case FIVE: handValue += 5; break;
                case SIX: handValue += 6; break;
                case SEVEN: handValue += 7; break;
                case EIGHT: handValue += 8; break;
                case NINE: handValue += 9; break;
                case TEN:
                case JACK:
                case QUEEN:
                case KING: handValue += 10; break;
            }
        }
        // calculate the aces's value after adding all others.
        for(int i = 0; i < numberOfAces; i++) {
            if(handValue + 11 > 21) {
                handValue += 1;
            } else {
                handValue += 11;
            }
        }
        return handValue;
    }

    public void stand(BlackJackPlayer player){
        player.setEndedTurn(true);
    }

    public void hit(BlackJackPlayer player){
        Deck deck = getDeck();
        Card card = deck.getNextCard();
        if(card != null){
            player.getHand().add(card);
            if(player instanceof HumanPlayer || player instanceof Dealer){
                System.out.println("Player "+player.getName()+" drew ["+card.toString()+"]");
            } else {
                System.out.println("\nPlayer "+player.getName()+" hits...");
            }
            if(player.getHandValue()> 21) {
                System.out.println("\nOuch Player "+player.getName()+" is busted !");
                player.setBusted(true);
            }


        }
    }

    public Dealer getDealer() {
        return dealer;
    }

}
