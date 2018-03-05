package com.example.toshiba.bohnanzagamestate;


/**
 * Created by Toshiba on 2/26/2018.
 */

public class BohnanzaState {
    private int turn = 0; // 0 = player 1, 1 =  player 2, etc.

    private BohnanzaGamePlayer[] playerList = new BohnanzaGamePlayer[4];
    private Deck[] playerHands;
    private int playerCoins = 0;
    private boolean hasThirdField;

    private Deck field1;
    private Deck field2;
    private Deck field3;

    //Deck class will include current iteration
    private Deck mainDeck;
    private Deck discardDeck;

    // 0: plant, 1: trade card 1 ...
    private int phase;


    public BohnanzaState() {
        mainDeck = new Deck();
        //Implement method to put all Beans into deck
        //Implement shuffle class in the future

        playerHands[0] = new Deck();
        playerHands[1] = new Deck();
        playerHands[2] = new Deck();
        playerHands[3] = new Deck();

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
                moveTopCardTo(playerHands[j]);
            }
        }

        BohnanzaGamePlayer player1 =
                new BohnanzaGamePlayer("Reeca", playerHands[0]);
        BohnanzaGamePlayer player2 =
                new BohnanzaGamePlayer("Alyssa", playerHands[1]);
        BohnanzaGamePlayer player3 =
                new BohnanzaGamePlayer("Adam", playerHands[2]);
        BohnanzaGamePlayer player4 =
                new BohnanzaGamePlayer("Sarah", playerHands[3]);

    }
    /**
     * Deep copy a saved state
     */
    public BohnanzaState(BohnanzaState orig) {
        turn = orig.turn;
        phase = orig.phase; // 0: plant, 1: trade card 1 ...

        for(int i = 0; i<4; i++) {
            playerList[i] = orig.playerList[i];
        }

        //Main and discard decks
        mainDeck = new Deck(orig.mainDeck);
        discardDeck = new Deck(orig.discardDeck);


    }

    @Override
    public String toString(){
        String bigString = "";

        return bigString;
    }
}
