package com.intuit.cardgame.common;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GameScore implements Serializable {

    private Map<String, Integer> scores = new HashMap<String,Integer>();
    private Date lastPlayed;

    public void addWin(Player player){
        String name = player.getName();
        if(scores.containsKey(name)){
            scores.put(player.getName(), scores.get(name) + 1);
        } else {
            scores.put(player.getName(), 1);
        }
    }

    public Integer getWins(Player player){
        Integer wins =0;
        if(scores.containsKey(player.getName())){
            wins = scores.get(player.getName());
        }
        return wins;
    }

    // Getter + setters needed by Jackson

    public Map<String, Integer> getScores() {
        return scores;
    }

    public void setScores(Map<String, Integer> scores) {
        this.scores = scores;
    }

    public Date getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(Date lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
