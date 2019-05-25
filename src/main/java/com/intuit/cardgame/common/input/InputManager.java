package com.intuit.cardgame.common.input;

import com.intuit.cardgame.common.CardGame;
import com.intuit.cardgame.common.GameContext;
import com.intuit.cardgame.common.PlayerCommand;

public interface InputManager {

    PlayerCommand handleUserInput(CardGame context);

}
