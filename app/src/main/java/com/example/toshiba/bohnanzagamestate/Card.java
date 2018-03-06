package com.example.toshiba.bohnanzagamestate;

/**
 * Created by Toshiba on 3/4/2018.
 */

public class Card {
    private String beanName;

    public Card(String name){
        beanName = name;
    }

    public Card(Card orig){
        beanName = orig.beanName;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Card)) return false;
        Card c = (Card)other;
        return this.beanName.equals(c.beanName);
    }

    public void setBeanName(String newName) { beanName = newName; }

    public String getBeanName() { return beanName; }
}
