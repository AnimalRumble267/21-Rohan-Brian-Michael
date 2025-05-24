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
     * Assigns players, creates Decks intantiates the types of number and trump
     * cards Number cards: 1-11 Trump cards: String type --> name, int value -->
     * value of the command within the Trump Card
     * 
     * @param playerOne
     * @param playerTwo
     * @param bet
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
        tempTrumpDeck.add(new TrumpCard(27, "trumpgofor"));

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


    public void startGUI()
    {
        gameGUI.start();
    }


    public void updateGameWindow()
    {
        gameGUI.updateGameWindow();
    }


    public int getGameWindowX()
    {
        return gameGUI.getGameWindowX();
    }


    public int getGameWindowY()
    {
        return gameGUI.getGameWindowY();
    }


    /**
     * Deals cards to Players and continues the round until a Player
     */
    public int deal()
    {
        players.get(0).resetHand();
        players.get(1).resetHand();
        gameGUI.updateGameWindow();
        status = 1;
        numberCardDeck.shuffle();
        trumpCardDeck.shuffle();

        GameGUI.wait(1.0);
        
        // Give starting hand (2 Cards, 1 TC)
        NumberCard muteCardOne = (NumberCard)numberCardDeck.draw();
        muteCardOne.setIsHidden(true);
        players.get(0).giveNumberCard(muteCardOne);

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard muteCardTwo = (NumberCard)numberCardDeck.draw();
        muteCardTwo.setIsHidden(true);
        players.get(1).giveNumberCard(muteCardTwo);

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard cardOne = (NumberCard)numberCardDeck.draw();
        cardOne.setIsHidden(false);
        players.get(0).giveNumberCard(cardOne);

        gameGUI.updateGameWindow();
        GameGUI.wait(0.5);

        NumberCard cardTwo = (NumberCard)numberCardDeck.draw();
        cardTwo.setIsHidden(false);
        players.get(1).giveNumberCard(cardTwo);

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
                if (oneCode / 10 == 3)
                {
                    while (oneCode / 10 == 3)
                    {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                    }
                }
                else
                {
                    handleAction(playerOne, oneCode);
                }

                playerOne.setTurn(false);
                playerOne.updateHand();
                playerTwo.setTurn(true);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();

                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3)
                {
                    while (twoCode / 10 == 3)
                    {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                    }
                }
                else
                {
                    handleAction(playerTwo, oneCode);
                }

                playerOne.setTurn(true);
                playerOne.updateHand();
                playerTwo.setTurn(false);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
            }
            else
            {
                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3)
                {
                    while (twoCode / 10 == 3)
                    {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                    }
                }
                else
                {
                    handleAction(playerTwo, twoCode);
                }

                playerOne.setTurn(true);
                playerOne.updateHand();
                playerTwo.setTurn(false);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();

                oneCode = playerOne.getInput();
                if (oneCode / 10 == 3)
                {
                    while (oneCode / 10 == 3)
                    {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                    }
                }
                else
                {
                    handleAction(playerOne, oneCode);
                }

                playerOne.setTurn(false);
                playerOne.updateHand();
                playerTwo.setTurn(true);
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
            }
            if (twoCode == 2 && oneCode == 2)
            {
                bothStand = true;
                playerOne.setTurn(false);
                playerTwo.setTurn(false);
                playerOne.updateHand();
                playerTwo.updateHand();
                gameGUI.updateGameWindow();
            }
        }
        int valOne = playerOne.calculate();
        int valTwo = playerTwo.calculate();
        int distanceFromGoal1 = goal - valOne;
        int distanceFromGoal2 = goal - valTwo;
        if (valOne > goal && valTwo > goal)
        {
            if (distanceFromGoal1 == distanceFromGoal2)
            {
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2)
            {
                return 2;
            }
            else if (distanceFromGoal2 < distanceFromGoal1)
            {
                return 1;
            }
        }
        else if (valOne > goal)
        {
            return 2;
        }
        else if (valTwo > goal)
        {
            return 1;
        }
        else
        {
            if (distanceFromGoal1 == distanceFromGoal2)
            {
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2)
            {
                return 1;
            }
            else if (distanceFromGoal2 < distanceFromGoal1)
            {
                return 2;
            }
        }
        return 3; // ERROR

    }


    /**
     * takes in the parameter of a player and a code (symbolizing action of the
     * player to either hit, stand or play trump) executes result of selecting
     * the hit, stand or playing a trump card
     * 
     * @param Player
     *            activePlayer
     * @param int
     *            code @
     */
    public void handleAction(Player activePlayer, int code)
    {
        if (code == 1)
        {
            NumberCard newNumberCard = (NumberCard)numberCardDeck.draw();
            newNumberCard.setIsHidden(false);
            activePlayer.giveNumberCard(newNumberCard);
        }
        else if (code / 10 == 3)
        {
            int numTrump = code % 10;
            TrumpCard trump = activePlayer.getTrumpCardHand().get(numTrump-1);
            cache.add(trump);
            String trumpType = trump.getType();
            if (trumpType.equals("trumpgofor"))
            {
                goal = trump.getValue();
            }
            else if (trumpType.equals("trumpdraw"))
            {
                NumberCard tempCard = (NumberCard)numberCardDeck.getCard(trump.getValue());
                if (tempCard == null)
                {
                    return;
                }
                activePlayer.giveNumberCard(tempCard);
            }
            else if (trumpType.equals("trumpup"))
            {
                bet += trump.getValue();
            }

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
     * generate changing probability of a player dying on a single shot. This
     * probability will increase after each shot doesn't kill the player
     * (probability is 1/n, and n decreases by 1 everyshot). if, on any given
     * shot, the player gets unlucky (randomly generated probability falls under
     * probability), the player dies.
     * 
     * @param bet
     * @return boolean if player will die
     */
    public void punish(Player player, int bet)
    {
        status = 2;
        punishStatus = 0;
        playerWillBeEliminated = false;

        gameGUI.updateGameWindow();
        GameGUI.wait(2.0);
        System.out.println("Waiting... 0");

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
            System.out.println("Waiting... 1");

            if (Math.random() < probability)
            {
                playerWillBeEliminated = true;
                result = true;
                needToBreak = true;
            }

            punishStatus = 2;
            gameGUI.updateGameWindow();
            System.out.println("Waiting... 2");

            GameGUI.wait(1.5);

            punishStatus = 3;
            gameGUI.updateGameWindow();
            System.out.println("Waiting... 3");

            GameGUI.wait(1.5);

            if (needToBreak)
            {
                break;
            }

            increment--;
        }

        if (result)
        {
            player.setAlive(false);
        }
    }


    /**
     * Returns whether or not the player being punished will be eliminated
     * 
     * @return
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
     * @return true if this is the dealer's first time dealing the cards, false otherwise
     */
    public boolean isFirstDeal()
    {
        return isFirstDeal;
    }

}
