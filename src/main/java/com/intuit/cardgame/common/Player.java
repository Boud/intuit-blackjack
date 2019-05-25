package com.intuit.cardgame.common;

import com.intuit.cardgame.common.cards.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected String name;
    protected List<Card> hand;
    protected int money;


    public Player(String name){
        this.name = name;
        hand = new ArrayList<Card>();
    }

    public void addCard(Card card, boolean shown){
        card.setShown(shown);
        hand.add(card);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
