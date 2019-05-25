package com.intuit.cardgame.common;

/**
 *
 */
public interface PlayerCommand {


    /**
     * Executes a Player Command
     * @param player
     */
    void execute(Player player, CardGame context);
}
