package com.intuit.cardgame.common;

import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Base class for all cardGames
 *
 * @author mnajar
 */
public abstract class CardGame {

    protected PropertyChangeSupport support= new PropertyChangeSupport(this);;
    protected Deck deck;
    protected List<Player> players = new ArrayList<Player>();
//    protected List<Card> dealerHand = new ArrayList<Card>();
    protected GameState currentState;
    protected int currentPlayerIndex = 0;
    protected boolean playing;

    public void run(){
        showMenu();
    }

    public void play(){
        initGame();
        while(playing){
            if(currentState != null){
                currentState.handle(this);
            }
        }
        System.out.println("\n ******* Game Ended ********");
        reset();
    }

    // Default implementation
    protected void showMenu() {
        //greet user and give 4 options
        System.out.println("\n Would you like to play ? Type a number to begin");
        System.out.println("1. Play");
        System.out.println("2. Stats");
        System.out.println("3. Rules");
        System.out.println("4. Quit");
        System.out.println("\n");
    }


    protected abstract void initGame();

    protected abstract void reset();

    protected abstract void gameRules();

    protected void notifyUI(String message){
        support.firePropertyChange("message",null,message);
    }


    // Getters + Setters

    public List<Player> getPlayers() {
        return players;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public void setCurrentPlayerIndex(int currentPlayerIndex) {
        this.currentPlayerIndex = currentPlayerIndex;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

//    public List<Card> getDealerHand() {
//        return dealerHand;
//    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
