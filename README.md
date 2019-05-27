Black Jack by Mourad NAJAR
===========================

**Rules**

Simple Blackjack with no bets.

You can play with human or AI opponents against the Dealer/ bank.

No handling of split, only possible choices are hit or stand.

At the end of the game, each player's hand is only compared to the dealer and not against each others (multiple winners possible)

**Instructions**

Clone the repository to the directory of your choice.

Load in IntelliJ then run the App class.

Outside of IDE run the following commands in the command line :
```
mvn clean package

java -jar target\blackjack-1.0-SNAPSHOT.jar
```


**Completion**

- ~~Implement a card game~~
- ~~Maintain a scoring system that persists over multiple game sessions~~
- ~~Ability to play against the computer, or against other players~~
- ~~Be extensible so that other card games could be implemented in the future,
potentially dozens of different games~~
- Track each player/computer action within the system
- ~~Capable of supporting interaction patterns other than a command-line interface~~
- ~~When playing against the computer a difficulty setting of easy/medium/hard can
be selected~~