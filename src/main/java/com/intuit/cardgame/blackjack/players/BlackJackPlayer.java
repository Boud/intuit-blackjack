package com.intuit.cardgame.blackjack.players;

import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Card;

public abstract class BlackJackPlayer extends Player {

    protected boolean busted;
    protected boolean endedTurn;

    public BlackJackPlayer(String name) {
        super(name);
    }

    public int getHandValue(){
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


    public boolean hasBlackJack(){
        return (getHandValue()== 21);
    }

    public boolean isBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public boolean isEndedTurn() {
        return endedTurn;
    }

    public void setEndedTurn(boolean endedTurn) {
        this.endedTurn = endedTurn;
    }
}
