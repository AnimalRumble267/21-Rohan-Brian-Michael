import java.util.*;
/**
 * 
 * 
 * @author
 * @version
 * 
 */
public class Dealer
{
    private Deck numberCardDeck;
    private Deck trumpCardDeck;
    private int bet;
    private ArrayList<Player> players = new ArrayList<Player>();
    private boolean punishTracker = false;
    private int goal = 21;
    private int status = 1; // TODO defaults to 4 for testing purposes, should be 0
    private boolean willDie = false;

    private GameGUI gameGUI;
    /**
     * Assigns players, creates Decks
     * @param playerOne
     * @param playerTwo
     * @param bet
     */
    public Dealer(Player playerOne, Player playerTwo, int bet) {
        players.add(playerOne);
        players.add(playerTwo);
        this.bet = bet;

        ArrayList<Card> tempNumberDeck = new ArrayList<Card>(11);
        for (int i = 1; i <= 11; i++) {
            tempNumberDeck.add(new NumberCard(i, true));
        }
        numberCardDeck = new Deck(tempNumberDeck);

        ArrayList<Card> tempTrumpDeck = new ArrayList<Card>(0);
        
        // add number trumps
        for (int j = 1; j < 8; j++) {
            tempTrumpDeck.add(new TrumpCard(j, "trumpDraw"));
        }
    
        // go for trumps
        tempTrumpDeck.add(new TrumpCard(17 , "trumpgofor"));
        tempTrumpDeck.add(new TrumpCard(24 , "trumpgofor"));
        tempTrumpDeck.add(new TrumpCard(27 , "trumpgofor"));

        // bet trumps
        tempTrumpDeck.add(new TrumpCard(1 , "trumpup"));
        tempTrumpDeck.add(new TrumpCard(2 , "trumpup"));

        trumpCardDeck = new Deck(tempTrumpDeck);
        
        numberCardDeck.shuffle();
        trumpCardDeck.shuffle();
        // have no implemented -- will test first
        // trump cards that have not been implemented: bet (shieldPlus, bless, bloodshed, destroy, friendship, reincarnation)
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
     * 
     */
    public int deal()
    {
        status = 1;
        numberCardDeck.shuffle();
        trumpCardDeck.shuffle();
        
        // Give starting hand (2 Cards, 1 TC)
        NumberCard muteCardOne = (NumberCard)numberCardDeck.draw();
        muteCardOne.setIsHidden(true);
        players.get(0).giveNumberCard(muteCardOne);
        NumberCard muteCardTwo = (NumberCard)numberCardDeck.draw();
        muteCardTwo.setIsHidden(true);
        players.get(1).giveNumberCard(muteCardTwo);

        NumberCard cardOne = (NumberCard)numberCardDeck.draw();
        cardOne.setIsHidden(false);
        players.get(0).giveNumberCard(cardOne);
        NumberCard cardTwo = (NumberCard)numberCardDeck.draw();
        cardTwo.setIsHidden(false);
        players.get(1).giveNumberCard(cardTwo);

        TrumpCard trumpCardOne = (TrumpCard)trumpCardDeck.draw();
        players.get(0).giveTrumpCard(trumpCardOne);
        TrumpCard trumpCardTwo = (TrumpCard)trumpCardDeck.draw();
        players.get(1).giveTrumpCard(trumpCardTwo);

        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        

        // Starting turn
        if (Math.random() < 0.5) {
            playerOne.setTurn(true);
        }
        else {
            playerTwo.setTurn(true);
        }

        // Wait until both players stand
        boolean bothStand = false;
        int oneCode;
        int twoCode;
        while (bothStand == false) {
            if (playerOne.isTurn()) {
                oneCode = playerOne.getInput();
                if (oneCode / 10 == 3) {
                    while (oneCode / 10 == 3) {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                    }
                }
                else {
                    handleAction(playerOne, oneCode);
                }

                playerOne.setTurn(false);
                playerTwo.setTurn(true);

                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3) {
                    while (twoCode / 10 == 3) {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                    }
                }

                playerOne.setTurn(true);
                playerTwo.setTurn(false);

            }
            else {
                twoCode = playerTwo.getInput();
                if (twoCode / 10 == 3) {
                    while (twoCode / 10 == 3) {
                        handleAction(playerTwo, twoCode);
                        twoCode = playerTwo.getInput();
                    }
                }

                playerOne.setTurn(true);
                playerTwo.setTurn(false);


                oneCode = playerOne.getInput();
                if (oneCode / 10 == 3) {
                    while (oneCode / 10 == 3) {
                        handleAction(playerOne, oneCode);
                        oneCode = playerOne.getInput();
                    }
                }
                else {
                    handleAction(playerOne, oneCode);
                }

                playerOne.setTurn(false);
                playerTwo.setTurn(true);
                
            }
            if (twoCode == 2 && oneCode == 2) {
                bothStand = true;
            }
        }
        int valOne = playerOne.calculate();
        int valTwo = playerTwo.calculate();
        int distanceFromGoal1 = goal - valOne;
        int distanceFromGoal2 = goal - valTwo;
        if (valOne > goal && valTwo > goal) {
            if (distanceFromGoal1  == distanceFromGoal2) {
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2) {
                return 2;
            }
            else if (distanceFromGoal2 < distanceFromGoal1) {
                return 1;
            }
        }
        else if (valOne > goal) {
            return 2;
        }
        else if (valTwo > goal) {
            return 1;
        }
        else {
            if (distanceFromGoal1 == distanceFromGoal2) {
                return 0;
            }
            else if (distanceFromGoal1 < distanceFromGoal2) {
                return 1;
            }
            else if (distanceFromGoal2 < distanceFromGoal1) {
                return 2;
            }
        }
        return 3; // ERROR
        

    }

    
    public void handleAction(Player activePlayer, int code) {
        if (code == 1) {
            activePlayer.giveNumberCard((NumberCard) numberCardDeck.draw());
        }
        else if (code / 10 == 3) {
            // TRUMP CARD LOGIC
        }
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void incrementBet() {
        bet++;
    }

    public void clearBet() {
        bet = 1;
    }

    public int getBet() {
        return bet;
    }

     /**
     * generate changing probability of a player dying on a single shot. This probability will increase after each shot 
     * doesn't kill the player (probability is 1/n, and n decreases by 1 everyshot). if, on any given shot, the player
     * gets unlucky (randomly generated probability falls under probability), the player dies.
     * @param bet
     * @return boolean if player will die
     */
    public boolean punish(Player playerNumber, int bet)
    { 
        status = 2;
        boolean result = false;
        boolean needToBreak = false;
        
        int increment = 6;
        double probability = 1 / increment;

        for (int i = 0; i < bet; i++) {
            
            if (Math.random() < probability) {
                willDie = true;
                result = true;
                needToBreak = true;
            }

            gameGUI.updateGameWindow();
            
            if (needToBreak) {
                break;
            }
            
            increment--;
        }

        if (result) {
            return true;
        }
        else {
            return false;  
        }

    }

    public boolean playerWillDie() {
        return willDie;
    }    

    /**
     * Returns the current status of the dealer.
     * 0 = starting game
     * 1 = playing the game normally
     * 2 = punishing a player
     * 3 = ending game
     * @return the current status
     */
    public int getStatus()
    {
        return status;
    }
    
}
