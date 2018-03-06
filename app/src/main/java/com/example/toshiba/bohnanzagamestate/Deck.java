package com.example.toshiba.bohnanzagamestate;

import java.util.ArrayList;

/**
 * Created by Toshiba on 3/4/2018.
 */

public class Deck {
    private ArrayList<Card> cards;

    public Deck (){
        cards = new ArrayList<Card>();
    }

    public Deck(Deck orig) {
        // synchronize to ensure that original is not being modified as we
        // iterate over it
        synchronized(orig.cards) {
            // create a new arrayList for our new deck; add each card in it
            cards = new ArrayList<Card>();
            for (Card c: orig.cards) {
                cards.add(c);
            }
        }
    }

    /**
     * add a card to the top of a deck
     *
     * @param c
     * 		the card to add
     */
    public void add(Card c) {
        // synchronize so that the underlying ArrayList is not accessed
        // inconsistently
        synchronized(this.cards) {
            cards.add(c);
        }
    }

    /**
     * Moves the top card the current deck to the top of another; does nothing if
     * the first deck is empty
     *
     * @param targetDeck
     * 		the deck to which the card should be moved
     */
    public void moveTopCardTo(Deck targetDeck) {

        // will hold the card
        Card c = null;

        // size of the first deck
        int size;

        // indivisibly check the deck for empty, and remove the card, to
        // avoid a race condition
        synchronized(this.cards) {
            size = this.size();
            if (size > 0) {
                c = cards.remove(cards.size()-1);
            }
        }

        // if the original size was non-zero, add the card to the top of the
        // target deck
        if (size > 0) {
            targetDeck.add(c);
        }
    }

    /**
     * @return
     * 		the top card in the deck, without removing it; null
     * 		if the deck was empty
     */
    public Card peekAtTopCard() {
        synchronized (this.cards) {
            if (cards.isEmpty()) return null;
            return cards.get(cards.size()-1);
        }
    }

    public int size() {
        return cards.size();
    }

    public Deck addAllCards() {
        Deck curDeck = new Deck();
        for(int i = 0; i <6; i++) {
            curDeck.add(new Card("Garden Bean"));
        }
        for(int i = 0; i < 8; i++) {
            curDeck.add(new Card("Red Bean"));
        }
        for(int i = 0; i < 10; i++) {
            curDeck.add(new Card("Black-Eyed Bean"));
        }
        for(int i = 0; i < 12; i++) {
            curDeck.add(new Card("Soy Bean"));
        }
        for(int i = 0; i < 14; i++) {
            curDeck.add(new Card("Green Bean"));
        }
        for(int i = 0; i < 16; i++) {
            curDeck.add(new Card("Stink Bean"));
        }
        for(int i = 0; i < 18; i++) {
            curDeck.add(new Card("Chili Bean"));
        }
        for(int i = 0; i < 20; i++) {
            curDeck.add(new Card("Blue Bean"));
        }
        return curDeck;
    }

    public ArrayList<Card> getCards() { return cards; }

    public void turnHandOver() {
        int oldSize = size();
        cards.clear();
        for(int i = 0; i<oldSize; i++ ){
            cards.add(new Card("Card Back"));
        }
    }
/*
    @Override
    public String toString(){
        String beanList = "";
        for(int i = 0; i<size(); i++){
            beanList = beanList+cards.get(i).getBeanName();
        }
        return beanList;
    }
    */
}
