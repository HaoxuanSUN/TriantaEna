/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * GameList class
 * stores the games, easier to add or delete games from game library
 */

public enum GameList {
	TriantaEna("1", "Trianta Ena"), 
	Exit("2", "Exit"),
	Show_Developer_Info("3", "Show Game Developer Info");
	
    private final String gameNum;
    private final String gameName;
	
    GameList(String number, String name) {
        gameNum = number;
        gameName = name;
    }
    
    public String getGameName() {
        return gameName;
    }
    
	public String getGameNum() {
        return gameNum;
    }
    
    //override toString method in Enum class
    public String toString() {
        return "               " + gameNum + ": " + gameName;
    }
}
