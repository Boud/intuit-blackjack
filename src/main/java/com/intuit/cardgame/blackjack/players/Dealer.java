package com.intuit.cardgame.blackjack.players;

import com.intuit.cardgame.blackjack.BlackJack;

public class Dealer extends BlackJackPlayer{

    public Dealer(String name) {
        super(name);
    }

    public String displayDealerHand(boolean displayAll){
        String message = "Dealer's hand : ";
        if(hand != null && !hand.isEmpty()){
            for (int i=0;i<hand.size();i++){
                message += displayAll || i == 0
                        ? "["+hand.get(i).toString()+"] "
                        : "[Hidden] ";
            }
        }
        return message;
    }

    public void playTurn(BlackJack context){
        if(getHandValue() == 21){
            context.stand(this);
        } else {
            while (getHandValue() < 17){
                context.hit(this);
            }
        }
    }


}
