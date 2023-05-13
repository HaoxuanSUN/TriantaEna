/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * Deck Class
 * Class for init deck for a class.
 * methods implemented: shuffle, init based on a certain input 
 * and get next card of a deck
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private int deckCount;
    protected List<Card> deck = new ArrayList<>();
    
    
	public Deck(int deckCount) {
        this.deckCount = deckCount;
        initDeck();
    }
	
	
	
	// Shuffle the entire deck list
    public void shuffle() {
        initDeck();
        System.out.println("Shuffling...");
        Collections.shuffle(deck);
    }
	
    // init deck based on how many decks is played in game.
	public void initDeck() {
        for (int i = 0; i < deckCount; i++) {
        	deck.addAll(initADeck());
        }
    	//System.out.println("Full Deck: " + deck.toString());
    }

	// init one deck 13 types * 4 cards = 52 cards each
	private List<Card> initADeck() {
		List<Card> tempDeck = new ArrayList<>();
        for(CardValueType cardValueType : CardValueType.values()){
            for(CardSuitType cardSuitType : CardSuitType.values()){
                tempDeck.add(new Card(cardSuitType, cardValueType));
            }
        }
        return tempDeck;
	}
	
	// get the next card in the deck list
	// if the number of cards left in the deck is smaller than 1/2 of a whole whole (26), shuffle the whole deck
    public Card getNextCard() {
        if(deck.size() < (52 * 0.5)){
            shuffle();
        }
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
}
