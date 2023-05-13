/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 * 
 * Enum type
 * there are 4 types of card in each deck: Club, Spade, Diamond, Heart.
 */

public enum CardSuitType {
    CLUB("Club", "♣"),
    SPADE("Spade", "♠"),
    DIAMOND("Diamond", "◆"),
    HEART("Heart", "❤");


    private String suitTypeName;
    private String suitTypeNotation;


    CardSuitType(String suitTypeName, String suitTypeNotation) {
        this.suitTypeName = suitTypeName;
        this.suitTypeNotation = suitTypeNotation;
    }

    public String getSuitTypeName() {
        return suitTypeName;
    }

    public String getSuitTypeNotation() {
        return suitTypeNotation;
    }
}
