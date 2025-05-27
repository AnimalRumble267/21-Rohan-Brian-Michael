import java.util.*;

/**
 * This class handles each round and the actual game in of itself.
 * Like its name suggests, the Dealer class handles setting the hands for each player
 * every round, responding to their requests to hit or stand, or to the trump
 * cards they play. At the end of every round (through the deal() method), it
 * returns a code depending on who won, or if it was a draw, and has the
 * capability to punish the loser of the game.
 * 
 * @author - Rohan Chaudhary
 * @version - 5/22/2025
 */
public class Dealer
{
    private Deck                 numberCardDeck;
    private Deck                 trumpCardDeck;
    private int                  bet;
    private ArrayList<Player>    players                = new ArrayList<Player>();
    private int                  goal                   = 21;
    private int                  status                 = 0;
    private int                  punishStatus           = 0;
    private boolean              playerWillBeEliminated = false;
    private ArrayList<TrumpCard> cache;
    private GameGUI              gameGUI;
    private boolean              isFirstDeal = false;

    /**
     * Assigns players, creates Decks and intantiates the types of number and trump
     * cards Number cards: 1-11 Trump cards: String type --> name, int value -->
     * value of the command within the Trump Card
     * 
     * @param playerOne - First player to deal to
     * @param playerTwo - Second player to deal to
     * @param bet - Current bet
     */
    public Dealer(Player playerOne, Player playerTwo, int bet)
    {
        players.add(playerOne);
        players.add(playerTwo);
        this.bet = bet;

        ArrayList<Card> tempNumberDeck = new ArrayList<Card>(11);
        for (int i = 1; i <= 11; i++)
        {
            tempNumberDeck.add(new NumberCard(i, true));
        }
        numberCardDeck = new Deck(tempNumberDeck);

        ArrayList<Card> tempTrumpDeck = new ArrayList<Card>(0);
        cache = new ArrayList<TrumpCard>();
        // add number trumps
        for (int j = 1; j < 8; j++)
        {
            tempTrumpDeck.add(new TrumpCard(j, "draw"));
        }

        // go for trumps
        tempTrumpDeck.add(new TrumpCard(17, "gofor"));
        tempTrumpDeck.add(new TrumpCard(24, "gofor"));
        tempTrumpDeck.add(new TrumpCard(27, "gofor"));

        // bet trumps
        tempTrumpDeck.add(new TrumpCard(1, "up"));
        tempTrumpDeck.add(new TrumpCard(2, "up"));

        // shield trump
        tempTrumpDeck.add(new TrumpCard(1, "shield"));

        trumpCardDeck = new Deck(tempTrumpDeck);

        numberCardDeck.shuffle();
        trumpCardDeck.shuffle();
        // have no implemented -- will test first
        // trump cards that have not been implemented: bet (shieldPlus, bless,
        // bloodshed, destroy, friendship, reincarnation)
        // AND the entirety of deck trumps

        gameGUI = new GameGUI(this);
    }


    /**
     * Starts the gameGUI
     */
    public void startGUI()
    {
        gameGUI.start();
    }

    /**
     * Stops the gameGUI
     */
    public void stopGUI()
    {
        gameGUI.stop();
    }

    /**
     * Forces GameGUI to update
     */
    public void updateGameWindow()
    {
        gameGUI.updateGameWindow();
    }


    /**
     * gets the X coord of the GUI's gameWindow
     * @return - int x coordinate
     */
    public int getGameWindowX()
    {
        return gameGUI.getGameWindowX();
    }


    public int getGameWindowY()
    {
        return gameGUI.getGameWindowY();
    }


    /**
     * Handles a round of 21.
     * Resets Deck, shuffles, deals cards to both players and randomly 
     * decides who goes first. Gets input for each player, and handles
     * each action accordingly using the handleAction method. Continues
     * until both stands, then decides the winner, returning an int based
     * on who wins the round.
     * 
     * @return int signifying result (0 = draw, 1  = p1 wins, 2 = p2 wins)
     */
    public int deal()
    {
        gameGUI.playMusic(0);
        for (TrumpCard i : cache) {
            if (i.getType().equals("up")) {
                bet -= i.getValue();
            }
        }
        cache.clear();
        players.get(0).resetHand();
        players.get(1).resetHand();
        gameGUI.updateGameWindow();
        status = 1;
        numberCardDeck.shuffle();
        trumpCardDeck.shuffle();
        goal = 21;

        GameGUI.wait(1.0);
        
        // Give starting hand (2 Cards, 1 TC)
        NumberCard muteCardOne = (NumberCard)numberCardDeck.draw();
        muteCardOne.setIsHidden(true);
        players.get(0).giveNumberCard(muteCardOne);
        players.get(0).playCardFlipSound();

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard muteCardTwo = (NumberCard)numberCardDeck.draw();
        muteCardTwo.setIsHidden(true);
        players.get(1).giveNumberCard(muteCardTwo);
        players.get(1).playCardFlipSound();

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard cardOne = (NumberCard)numberCardDeck.draw();
        cardOne.setIsHidden(false);
        players.get(0).giveNumberCard(cardOne);
        players.get(0).playCardFlipSound();

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard cardTwo = (NumberCard)numberCardDeck.draw();
        cardTwo.setIsHidden(false);
        players.get(1).giveNumberCard(cardTwo);
        players.get(1).playCardFlipSound();

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        TrumpCard trumpCardOne = (TrumpCard)trumpCardDeck.draw();
        players.get(0).giveTrumpCard(trumpCardOne);

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        TrumpCard trumpCardTwo = (TrumpCard)trumpCardDeck.draw();
        players.get(1).giveTrumpCard(trumpCardTwo);

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        trumpCardOne = (TrumpCard)trumpCardDeck.draw();
        players.get(0).giveTrumpCard(trumpCardOne);

        trumpCardTwo = (TrumpCard)trumpCardDeck.draw();
        players.get(1).giveTrumpCard(trumpCardTwo);

        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);

        // Starting turn
        if (Math.random() < 0.5)
        {
            playerOne.setTurn(true);
        }
        else
        {
            playerTwo.setTurn(true);
        }

        isFirstDeal = !isFirstDeal;

        gameGUI.updateGameWindow();
        GameGUI.wait(5.0);
        
        isFirstDeal = !isFirstDeal;

        playerOne.updateHand();
        playerTwo.updateHand();

        gameGUI.updateGameWindow();

        // Wait until both players stand
        boolean bothStand = false;
        int oneCode;
        int twoCode;
        while (bothStand == false)
        {
            if (playerOne.isTurn())
            {

                oneCode = playerOne.getInput();
                if (oneCode / 10 == 3 || oneCode == 0)
                {
                    while (oneCode / 10 == 3 || oneCode == 0)
                    {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                        
                    }
                    handleAction(playerOne, oneCode);
                }
                else
                {
                    handleAction(playerOne, oneCode);
                }
                
                playerOne.clearTrumpCardDescription();
                playerOne.setTurn(false);
                playerOne.updateHand();
                gameGUI.updateGameWindow();
                gameGUI.writeTurnMessage();
                GameGUI.wait(5.0);
                gameGUI.clearTurnMessage();
                playerTwo.setTurn(true);
                playerTwo.updateHand();

                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3 || twoCode == 0)
                {
                    while (twoCode / 10 == 3 || twoCode == 0)
                    {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                        
                    }
                }
                else
                {
                    handleAction(playerTwo, oneCode);
                }

                playerTwo.clearTrumpCardDescription();
                playerTwo.setTurn(false);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
                gameGUI.writeTurnMessage();
                GameGUI.wait(5.0);
                gameGUI.clearTurnMessage();
                playerOne.setTurn(true);
                playerOne.updateHand();
            }
            else
            {
                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3 || twoCode == 0)
                {
                    while (twoCode / 10 == 3 || twoCode == 0)
                    {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                        
                    }
                    handleAction(playerTwo, twoCode);
                }
                else
                {
                    handleAction(playerTwo, twoCode);
                }

                playerTwo.clearTrumpCardDescription();
                playerTwo.setTurn(false);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
                gameGUI.writeTurnMessage();
                GameGUI.wait(5.0);
                gameGUI.clearTurnMessage();
                playerOne.setTurn(true);
                playerOne.updateHand();

                oneCode = playerOne.getInput();
                if (oneCode / 10 == 3 || oneCode == 0)
                {
                    while (oneCode / 10 == 3 || oneCode == 0)
                    {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                        
                    }
                    handleAction(playerOne, oneCode);
                }
                else
                {
                    handleAction(playerOne, oneCode);
                }

                playerOne.clearTrumpCardDescription();
                playerOne.setTurn(false);
                playerOne.updateHand();
                gameGUI.updateGameWindow();
                gameGUI.writeTurnMessage();
                GameGUI.wait(5.0);
                gameGUI.clearTurnMessage();
                playerTwo.setTurn(true);
                playerTwo.updateHand();
            }
            if (twoCode == 2 && oneCode == 2)
            {
                bothStand = true;
                playerOne.setTurn(false);
                playerTwo.setTurn(false);
                playerOne.updateHand();
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
                GameGUI.wait(3.5);
                playerOne.getNumberCardHand().get(0).setIsHidden(false);
                playerTwo.getNumberCardHand().get(0).setIsHidden(false);
            }
        }
        int valOne = playerOne.calculate();
        int valTwo = playerTwo.calculate();
        int distanceFromGoal1 = goal - valOne;
        int distanceFromGoal2 = goal - valTwo;
        gameGUI.stopMusic(0);
        if (valOne > goal && valTwo > goal)
        {
            if (distanceFromGoal1 == distanceFromGoal2)
            {
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2)
            {
                playerTwo.setWon(true);
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 2;
            }
            else if (distanceFromGoal2 < distanceFromGoal1)
            {
                playerOne.setWon(true);
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 1;
            }
        }
        else if (valOne > goal)
        {
            playerTwo.setWon(true);
            gameGUI.updateGameWindow();
            playerOne.playCardFlipSound();
            GameGUI.wait(4.0);
            playerOne.setWon(false);
            playerTwo.setWon(false);
            return 2;
        }
        else if (valTwo > goal)
        {
            playerOne.setWon(true);
            gameGUI.updateGameWindow();
            playerOne.playCardFlipSound();
            GameGUI.wait(4.0);
            playerOne.setWon(false);
            playerTwo.setWon(false);
            return 1;
        }
        else
        {
            if (distanceFromGoal1 == distanceFromGoal2)
            {
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2)
            {
                playerOne.setWon(true);
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 1;
            }
            else if (distanceFromGoal2 < distanceFromGoal1)
            {
                playerTwo.setWon(true);
                gameGUI.updateGameWindow();
                playerOne.playCardFlipSound();
                GameGUI.wait(4.0);
                playerOne.setWon(false);
                playerTwo.setWon(false);
                return 2;
            }
        }
        return 3; // ERROR

    }


    /**
     * takes in the parameter of a player and a code (symbolizing action of the
     * player to either hit, stand or play trump) executes result of selecting
     * the hit, stand or playing a trump card, which can increase bet, draw
     * a certain card, reduce bet, or change the goal
     * 
     * @param activePlayer - Player who's action is being handled
     * @param code - int symbolizing action which needs to be taken
     */
    public void handleAction(Player activePlayer, int code)
    {

        System.out.println(code);

        if (code == 1)
        {
            NumberCard newNumberCard = (NumberCard)numberCardDeck.draw();
            newNumberCard.setIsHidden(false);
            activePlayer.giveNumberCard(newNumberCard);
            activePlayer.playCardFlipSound();
            activePlayer.updateHand();
            gameGUI.updateGameWindow();
        }
        else if (code / 10 == 3)
        {
            int numTrump = code % 10;
            if (numTrump >= activePlayer.getTrumpCardHand().size()) {
                return;
            }
            TrumpCard trump = activePlayer.getTrumpCardHand().get(numTrump);
            cache.add(trump);
            String trumpType = trump.getType();
            System.out.println(trumpType);
            if (trumpType.equals("gofor"))
            {
                goal = trump.getValue();
            }
            else if (trumpType.equals("draw"))
            {
                NumberCard tempCard = (NumberCard)numberCardDeck.getCard(trump.getValue());
                if (tempCard != null)
                {
                    tempCard.setIsHidden(false);
                    activePlayer.giveNumberCard(tempCard);
                } 
            }
            else if (trumpType.equals("up"))
            {
                bet += trump.getValue();
            }
            else if (trumpType.equals("shield")) {
                if (bet > 0) bet--;
            }
            else if (trumpType.equals("shield"))
            {
                if (bet > 0)
                {
                    bet -= trump.getValue();
                }
            }
            activePlayer.removeTrumpCard(numTrump);
            activePlayer.updateHand();
            gameGUI.updateGameWindow();
            activePlayer.playCardFlipSound();
        }
    }


    public ArrayList<Player> getPlayers()
    {
        return players;
    }


    public ArrayList<TrumpCard> getTrumpCards()
    {
        return cache;
    }


    public void incrementBet()
    {
        bet++;
    }

    public int getGoal()
    {
        return goal;
    }


    public int getBet()
    {
        return bet;
    }

    public void resetBet()
    {
        bet = 1;
    }    


    /**
     * Runs a punishment on given player and the game bet then sets the attribute of the player saying whether
     * or not they are alive following the punishment. The value of bet determines how many shots player undergoes,
     * the chance of player dying on each shot is given by 1 / n, where n = bet - shot number + 1
     * 
     * @param player - Player to be punished
     * @param bet - int signifying how many times punishment must be executed.
     */
    public void punish(Player player, int bet)
    {
        gameGUI.playMusic(1);
        status = 2;
        punishStatus = 0;
        playerWillBeEliminated = false;

        gameGUI.updateGameWindow();
        GameGUI.wait(2.0);

        boolean result = false;
        boolean needToBreak = false;

        int increment = 6;
        double probability = 1.0 / increment;

        for (int i = 0; i < bet; i++)
        {
            probability = 1.0 / increment;
            punishStatus = 1;
            gameGUI.updateGameWindow();

            GameGUI.wait(2.0);

            if (Math.random() < probability)
            {
                playerWillBeEliminated = true;
                result = true;
                needToBreak = true;
            }

            punishStatus = 2;
            gameGUI.updateGameWindow();

            GameGUI.wait(1.5);

            punishStatus = 3;
            gameGUI.updateGameWindow();

            GameGUI.wait(2.5);

            if (needToBreak)
            {
                break;
            }

            increment--;
        }

        if (result)
        {
            status = 3;
            player.setAlive(false);
        }
        gameGUI.stopMusic(1);
    }


    /**
     * Returns whether or not the player being punished is/will be eliminated
     * @return true if the player is/will be eliminated, false otherwise
     */
    public boolean playerWillBeEliminated()
    {
        return playerWillBeEliminated;
    }


    /**
     * Returns the current status of the dealer. 0 = starting game 1 = playing
     * the game normally 2 = punishing a player 3 = ending game
     * 
     * @return the current status
     */
    public int getStatus()
    {
        return status;
    }


    /**
     * Returns the current status of the punishment. 0 = starting punishment 1 =
     * waiting for punishment 2 = hearing punishment 3 = outcome of punishment
     * 
     * @return the current punishment status
     */
    public int getPunishStatus()
    {
        return punishStatus;
    }

    /**
     * Returns whether or not the dealer is dealing for the first time in the round.
     * Indicates if the GUI should say which player is going first.
     * 
     * @return true if this is the dealer's first time dealing the cards, false otherwise
     */
    public boolean isFirstDeal()
    {
        return isFirstDeal;
    }

}
