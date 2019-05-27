package com.intuit.cardgame.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.cardgame.common.ai.AIStrategy;
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;

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
    protected boolean vsAI = true;
    protected AIStrategy AILevel;
    protected GameScore gameScore = new GameScore();
    protected ObjectMapper objectMapper = new ObjectMapper();

    public void play(){
        initGame();
        while(playing){
            if(currentState != null){
                notifyStateChange();
                currentState.handle(this);
            }
        }
        saveStats();
        reset();
    }

    public void notifyStateChange(){
        support.firePropertyChange("currentState",null,currentState);
    }

    public void sendMessage(String message){
        support.firePropertyChange("message",null,message);
    }

    // Abstract methods
    public abstract void setup(boolean vsAI, AILevel aiLevel);

    protected abstract void initGame();

    protected abstract void reset();

    protected abstract void stats();

    protected abstract void saveStats();


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

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
