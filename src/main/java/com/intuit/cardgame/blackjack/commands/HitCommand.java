package com.intuit.cardgame.blackjack.commands;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.players.BlackJackPlayer;
import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.PlayerCommand;
import com.intuit.cardgame.common.Player;
import com.intuit.cardgame.common.cards.Deck;

public class HitCommand implements PlayerCommand {

    public void execute(Player player, CardGame context) {
        BlackJackPlayer blackJackPlayer = (BlackJackPlayer) player;
        BlackJack blackJack = (BlackJack) context;
        Deck deck = blackJack.getDeck();
        player.getHand().add(deck.getNextCard());
        if(blackJackPlayer.getHandValue()> 21) {
            blackJackPlayer.setBusted(true);
        }
    }
}
