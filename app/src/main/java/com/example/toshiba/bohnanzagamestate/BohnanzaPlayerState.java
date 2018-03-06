package com.example.toshiba.bohnanzagamestate;

/**
 * Created by Toshiba on 3/4/2018.
 */

public class BohnanzaPlayerState {
    private String name;
    private int coins;
    private Deck[] fields;
    private Deck hand;
    private boolean hasThirdField;
    private int makeOffer;

    public BohnanzaPlayerState(String playerName) {
        name = playerName;
        hand = new Deck();
        coins = 0;
        hasThirdField = false;
        makeOffer = 0;
    }

    public BohnanzaPlayerState(BohnanzaPlayerState orig){
        name =  orig.name;
        coins = orig.coins;
        for( int i = 0; i<3; i++) {
            fields[i] = new Deck(orig.fields[i]);
        }
        hand = new Deck(orig.hand);
        hasThirdField = orig.hasThirdField;
    }

    //Getter methods
    public Deck getField(int field) { return fields[field]; }
    public String getName() {return name;}
    public int getCoins() {return coins;}
    public Deck getHand() {return hand;}
    public boolean getHasThirdField() {return hasThirdField;}
    public int getMakeOffer() {return makeOffer;}

    //setter methods
    public void setName (String newName) {name = newName;}
    public void setCoins (int newCoins) {coins = newCoins;}
    public void setHasThirdField (boolean newHasThirdField) {
        hasThirdField = newHasThirdField;
    }
    public void setHand (Deck newHand) {hand = newHand;}
    public void setField (Deck newField, int field) {
        fields[field] = newField;
    }
    //setMakeOffer 2 if user will make an offer, 1 if user will not
    // make an offer, 0 if they have not decided
    public void setMakeOffer(int newMakeOffer) {
        makeOffer = newMakeOffer;
    }
    public void setOffer(Card[] offer){
        for(int i = 0; i<offer.length; i++){
            hand.getCards().remove(i);
            hand.add(offer[i]);
        }
    }

}
