/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 * 
 * Card class
 * new a card based on suit type and value type
 * deck is composed of various type of card
 */


public class Card {
	private CardSuitType suitType;
    private CardValueType valueType;


    public Card(CardSuitType suitType, CardValueType valueType) {
        this.suitType = suitType;
        this.valueType = valueType;
    }

    public String toString() {
        if(suitType == null){
            return valueType.getValueTypeName();
        }else{
            return suitType.getSuitTypeName() + "-" + valueType.getValueTypeName();
        }
    }

    public CardSuitType getSuitType() {
        return suitType;
    }

    public CardValueType getValueType() {
        return valueType;
    }
}
