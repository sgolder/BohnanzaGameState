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

    // Phase 0: Begin turn and plant initial beans
    // Phase 1: Turn over two cards and decide to trade or plant
    // Phase 2: Trading
    private int phase;


    public BohnanzaState() {
        mainDeck = new Deck();
        mainDeck.addAllCards();
        discardDeck = null;
        tradeDeck = null;
        //Implement shuffle class in the future

        playerList[0] = new BohnanzaPlayerState("Reeca");
        playerList[1] = new BohnanzaPlayerState("Alyssa");
        playerList[2] = new BohnanzaPlayerState("Adam");
        playerList[3] = new BohnanzaPlayerState("Sarah");

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 5; i++) {
                mainDeck.moveTopCardTo(playerList[i].getHand());
            }
        }

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
        if( turn != playerId || phase != 1 ){
            return false;
        }
        phase = 2;
        return true;
    }
    //makeOffer
    public boolean makeOffer(int traderId, Card[] offer) {
        if(phase != 2) {
            return false;
        }
        playerList[traderId].setMakeOffer(2);
        playerList[traderId].setOffer(offer);
        return true;
    }
    //abstainFromTrading
    public boolean abstainFromTrading(int playerId) {
        if(phase != 2) {
            return false;
        }
        playerList[playerId].setMakeOffer(1);
        if(phase != 2)
        {
            return false;
        }
        return true;
    }
    //acceptoffer
    public boolean acceptOffer(int playerId, int traderId) {
        if( turn != playerId || phase != 2 ||
                playerList[traderId].getMakeOffer() != 2){
            return false;
        }
        Deck traderHand = playerList[traderId].getHand();
        Deck playerHand = playerList[playerId].getHand();
        while( !(traderHand.getCards().get(0).getBeanName().
                equalsIgnoreCase("CardBack"))){
            traderHand.moveTopCardTo(playerHand);
        }
        return true;
    }
    //draw3cards
    public boolean draw3Cards (int playerId) {
        if(turn != playerId) {
            return false;
        }
        //trade deck empty, then change turn to +1 unless 3 then turn to 0
        if(tradeDeck.getCards().isEmpty())
        {
            mainDeck.moveTopCardTo(playerList[playerId].getHand());
            mainDeck.moveTopCardTo(playerList[playerId].getHand());
            mainDeck.moveTopCardTo(playerList[playerId].getHand());
            if(turn == 3)
            {
                turn = 0;
            }
            else
            {
                turn++;
            }
            return true;
        }
        else
        {
            return false;
        }

    }

    /*
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
*/
    public BohnanzaPlayerState[] getPlayerList() { return playerList; }

}

