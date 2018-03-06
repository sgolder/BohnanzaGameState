package com.example.toshiba.bohnanzagamestate;


/**
 * Created by Toshiba on 2/26/2018.
 */

public class BohnanzaState {
    private int turn = 0; // 0 = player 1, 1 =  player 2, etc.

    private BohnanzaPlayerState[] playerList = new BohnanzaPlayerState[4];
    private Deck[] playerHands;

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
                mainDeck.moveTopCardTo(playerHands[j]);
            }
        }

        playerList[0] = new BohnanzaPlayerState("Reeca", playerHands[0]);
        playerList[1] = new BohnanzaPlayerState("Alyssa", playerHands[1]);
        playerList[2] = new BohnanzaPlayerState("Adam", playerHands[2]);
        playerList[3] = new BohnanzaPlayerState("Sarah", playerHands[3]);

    }
    /**
     * Deep copy a saved state
     */
    public BohnanzaState(BohnanzaState orig, int playerId) {
        turn = orig.turn;
        phase = orig.phase; // 0: plant, 1: trade card 1 ...

        for(int i = 0; i<4; i++) {
            playerList[i] = new BohnanzaPlayerState(orig.playerList[i]);
        }

        //Main and discard decks
        mainDeck = new Deck(orig.mainDeck);
        discardDeck = new Deck(orig.discardDeck);

        for(int i = 0; i<orig.playerList.length; i++){
            if(i != playerId){
                playerList[i].getHand().turnHandOver();
            }
        }
    }

    //Buy new field
    public boolean buyThirdField(int playerId){
        if( playerList[playerId].getHasThirdField() ){
            return false;
        }
        else {
            if( playerList[playerId].getCoins() >= 3 ){
                playerList[playerId].setHasThirdField(true);
                playerList[playerId].setCoins(
                        playerList[playerId].getCoins() - 3 );
                return true;
            }
            else{
                return false;
            }
        }
    }
    //plantBean
    public boolean plantBean(int playerId, int fieldId, Card toPlant,
                             Deck origin){
        //Check if player's turn
        if( turn != playerId ){
            return false;
        }
        //
        Deck targetField = new Deck(playerList[playerId].getField(fieldId));
        if (targetField == null ||
                targetField.peekAtTopCard().equals(toPlant)){
            origin.moveTopCardTo(playerList[playerId].getField(fieldId));
            return true;
        }
        return false;
    }
    //harvestfield
    public boolean harvestField(int playerId, Deck field){
        if( field == null ) {
            return false;
        }
        field.getCards().clear();

    }
    //turntwotradecards
    public boolean turn2Cards(int playerId){
        if( turn != playerId ){
            return false;
        }
    }
    //starttrading
    public boolean startTrading(int playerId) {
        if( turn != playerId ){
            return false;
        }
    }
    //makeoffer
    public boolean makeOffer(int playerId) {

    }
    //acceptoffer
    public boolean acceptOffer(int playerId) {
        if( turn != playerId ){
            return false;
        }
    }
    //draw3cards


    @Override
    public String toString(){
        String bigString = "";

        return bigString;
    }

}