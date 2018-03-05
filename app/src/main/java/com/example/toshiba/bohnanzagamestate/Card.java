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
}
