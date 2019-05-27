package com.intuit.cardgame.blackjack;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import com.intuit.cardgame.common.cards.Card;
import com.intuit.cardgame.common.cards.Deck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Component
public class BlackJack extends CardGame {

    private static Logger LOG = LoggerFactory
            .getLogger(BlackJack.class);

    private Dealer dealer;
    private static String SCORE_FILE_PATH ="blackjack-scores.json";

    public BlackJack() {
        AILevel = new EasyBlackjackAI();
    }


    @PostConstruct
    public void init(){
        // Load Game stats from json file if present
        File scoresFile = new File(SCORE_FILE_PATH);
        if(scoresFile.exists()){
            try {
                Map<String, Integer> scores = objectMapper.readValue(scoresFile,
                        new TypeReference<Map<String, Integer>>() {});
                gameScore.setScores(scores);

            } catch (JsonParseException e) {
                LOG.error("Error while parsing JSON file", e);
            } catch (JsonMappingException e) {
                LOG.error("Error while mapping JSON file", e);
            } catch (IOException e) {
                LOG.error("I/O Error with JSON file", e);
            }
        }

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
        if(gameScore != null && gameScore.getScores()!=null){
            String message ="Current Scores : ";
            for(Map.Entry<String, Integer> entry : gameScore.getScores().entrySet()) {
                message+= "\n"+entry.getKey()+" : "+ entry.getValue();
            }
            sendMessage(message);
        }
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

    @Override
    public void saveStats(){
        try {
            objectMapper.writeValue(new File(SCORE_FILE_PATH), gameScore.getScores());
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString( gameScore.getScores());
        } catch (IOException e) {
            LOG.error("I/O Error with JSON file", e);
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
