package com.intuit.cardgame;

import com.intuit.cardgame.blackjack.BlackJack;
import com.intuit.cardgame.blackjack.BlackJackConsole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class
 */
@SpringBootApplication
public class App implements CommandLineRunner {

        private static Logger LOG = LoggerFactory
                .getLogger(App.class);

        @Autowired
        private BlackJack blackJack;

        @Autowired
        private BlackJackConsole blackJackConsole;

        public static void main(String[] args) {
            SpringApplication.run(App.class, args);
        }

        @Override
        public void run(String... args) {
            // blackJackConsole is an observer to the CardGame
            blackJack.addPropertyChangeListener(blackJackConsole);
            blackJackConsole.run();
        }
}
