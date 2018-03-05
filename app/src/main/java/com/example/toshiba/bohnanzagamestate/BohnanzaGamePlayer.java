package com.example.toshiba.bohnanzagamestate;

/**
 * Created by Toshiba on 3/4/2018.
 */

public class BohnanzaGamePlayer {
    private String name;
    private int coins;
    private Deck field1;
    private Deck field2;
    private Deck field3;
    private Deck hand;
    private boolean hasThirdField;

    public BohnanzaGamePlayer(String playerName, Deck playerHand) {
        name = playerName;
        hand = new Deck(playerHand);
        coins = 0;
    }

    public BohnanzaGamePlayer(BohnanzaGamePlayer orig){
        name =  orig.name;
        coins = orig.coins;
        field1 = new Deck(orig.field1);
        field2 = new Deck(orig.field2);
        field3 = new Deck(orig.field3);
        hand = new Deck(orig.hand);
        hasThirdField = orig.hasThirdField;
    }
}
