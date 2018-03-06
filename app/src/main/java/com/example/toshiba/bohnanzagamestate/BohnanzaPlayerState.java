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

    public BohnanzaPlayerState(String playerName, Deck playerHand) {
        name = playerName;
        hand = new Deck(playerHand);
        coins = 0;
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

    //setter methods
    public void setName (String newName) {name = newName;}
    public void setCoins (int newCoins) {coins = newCoins;}
    public void setHasThirdField (boolean newHasThirdField) {hasThirdField = newHasThirdField;}
    public void setHand (Deck newHand) {hand = newHand;}
}
