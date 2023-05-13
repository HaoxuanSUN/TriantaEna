/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 * 
 * Enum type
 * there are 13 types of card value in each deck: from 1 to 10, J, Q, K.
 * and their value.
 */


public enum CardValueType {
    ACE("Ace", "A", 1, 11),
    TWO("Two", "2", 2, 2),
    THREE("Three", "3", 3, 3),
    FOUR("Four", "4", 4, 4),
    FIVE("Five", "5", 5, 5),
    SIX("Six","6", 6, 6),
    SEVEN("Seven", "7", 7, 7),
    EIGHT("Eight", "8", 8, 8),
    NINE("Nine", "9", 9, 9),
    TEN("Ten", "10", 10, 10),
    JACK("Jack", "J", 10, 10),
    QUEEN("Queen","Q", 10, 10),
    KING("King", "K", 10, 10);


    private String valueTypeName;
    private String valueTypeNotation;
    private int valueTypeValue1;
    private int valueTypeValue2;

    CardValueType(String valueTypeName, String valueTypeNotation, int valueTypeValue1, int valueTypeValue2) {
        this.valueTypeName = valueTypeName;
        this.valueTypeNotation = valueTypeNotation;
        this.valueTypeValue1 = valueTypeValue1;
        this.valueTypeValue2 = valueTypeValue2;
    }


    public String getValueTypeName() {
        return valueTypeName;
    }

    public String getValueTypeNotation() {
        return valueTypeNotation;
    }
    
    public int getValueTypeValue1() {
        return valueTypeValue1;
    }
    
    public int getValueTypeValue2() {
        return valueTypeValue2;
    }
}