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
    private Deck tradeDeck;

    // 0: plant, 1: trade card 1 ...
    private int phase;


    public BohnanzaState() {
        mainDeck = new Deck();
        mainDeck.addAllCards();
        discardDeck = null;
        tradeDeck = null;
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

        for(int i = 0; i<4; i++) {
            playerList[i].setCoins(0);
            playerList[i].setHasThirdField(false);
            playerList[i].setMakeOffer(0);
            for(int j = 0; j<3; j++) {
                playerList[i].setField(null, j);
            }
        }

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

        //Main, trade, and discard decks
        mainDeck.turnHandOver();
        discardDeck.turnHandOver();
        tradeDeck = new Deck(orig.tradeDeck);

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
        return true;
    }
    //turntwotradecards
    public boolean turn2Cards(int playerId){
        if( turn != playerId ){
            return false;
        }
        //move top two cards to trade deck
        mainDeck.moveTopCardTo(tradeDeck);
        mainDeck.moveTopCardTo(tradeDeck);
        return true;
    }
    //startTrading
    public boolean startTrading(int playerId) {
        if( turn != playerId ){
            return false;
        }
        return true;
    }
    //makeOffer
    public boolean makeOffer(int playerId) {
        playerList[playerId].setMakeOffer(2);
        return true;
    }
    //abstainFromTrading
    public boolean abstainFromTrading(int playerId) {
        playerList[playerId].setMakeOffer(1);
        return true;
    }
    //acceptoffer
    public boolean acceptOffer(int playerId) {
        if( turn != playerId ){
            return false;
        }
        return true;
    }
    //draw3cards
    public boolean draw3Cards (int playerId) {
        if(turn != playerId) {
            return false;
        }
        mainDeck.moveTopCardTo(playerList[playerId].getHand());
        return true;
    }

    @Override
    public String toString(){
        //Player 1 info
        String player1info = "Player 1 info:\n"+
                "Field 1: "+playerList[0].getField(0).toString()+
                "\n Field 2: "+playerList[0].getField(1).toString()+
                "\n Field 3: "+playerList[0].getField(2).toString()+
                "\n Hand: "+playerList[0].getHand().toString()+
                "\n Score: "+playerList[0].getCoins();

        //Player 2 info
        String player2info = "Player 2 info:\n"+
                "Field 1: "+playerList[1].getField(0).toString()+
                "\n Field 2: "+playerList[1].getField(1).toString()+
                "\n Field 3: "+playerList[1].getField(2).toString()+
                "\n Hand: "+playerList[1].getHand().toString()+
                "\n Score: "+playerList[1].getCoins();


        //Player 3 info
        String player3info = "Player 3 info:\n"+
                "Field 1: "+playerList[2].getField(0).toString()+
                "\n Field 2: "+playerList[2].getField(1).toString()+
                "\n Field 3: "+playerList[2].getField(2).toString()+
                "\n Hand: "+playerList[2].getHand().toString()+
                "\n Score: "+playerList[2].getCoins();


        //Player 4 info
        String player4info = "Player 4 info:\n"+
                "Field 1: "+playerList[3].getField(0).toString()+
                "\n Field 2: "+playerList[3].getField(1).toString()+
                "\n Field 3: "+playerList[3].getField(2).toString()+
                "\n Hand: "+playerList[3].getHand().toString()+
                "\n Score: "+playerList[3].getCoins();

        //other variables
        String whosTurn = "Who's turn is it? player "+turn+"\n";
        String gamePhase = "Phase: "+phase;




        String bigString = ""+player1info+player2info+player3info+player4info+whosTurn+gamePhase;

        return bigString;
    }

    public BohnanzaPlayerState[] getPlayerList() { return playerList; }
}
