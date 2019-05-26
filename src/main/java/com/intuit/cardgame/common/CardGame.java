package com.intuit.cardgame.common;

import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;
import com.intuit.cardgame.common.input.InputManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

/**
 * Base class for all cardGames
 *
 * @author mnajar
 */
public abstract class CardGame {

    protected PropertyChangeSupport support= new PropertyChangeSupport(this);;
    protected Deck deck;
    protected List<Player> players = new ArrayList<Player>();
    protected GameState currentState;
    protected int currentPlayerIndex = 0;
    protected boolean playing;
    protected int quitMenuChoice = 4;


    // This should definitively be done through Spring's IOC / DI
    protected InputManager inputManager;

    public void run(){
        int userChoice = 0;
        while(userChoice != quitMenuChoice){
            showMenu();
        }
    }

    public void play(){
        System.out.println("\n\n\n");
        initGame();
        while(playing){
            if(currentState != null){
                System.out.println("\n");
                wait(1000);
                currentState.handle(this);
            }
        }
        System.out.println("\n******* Game Ended ********\n");
        reset();
    }

    /**
     * Show the card game menu. Should be overriden if needed
     * @see CardGame for default implementation.
     */
    protected void showMenu() {
        //greet user and give 4 options
        System.out.println("\nWould you like to play ? Type a number to begin");
        System.out.println("1. Play");
        System.out.println("2. Setup");
        System.out.println("3. Stats");
        System.out.println("4. Quit");

        int playerChoice = inputManager.getUserInput();
        switch (playerChoice) {
            case 1: {
                play();
                break;
            }
            case 2: {
                setup();
                break;
            }
            case 3: {
                stats();
                break;
            }
            case 4: {
                System.exit(1);
                break;
            }

        }
    }

    protected abstract void initGame();

    protected abstract void reset();

    protected abstract void setup();

    protected abstract void stats();

    protected void wait(int milliseconds){
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
