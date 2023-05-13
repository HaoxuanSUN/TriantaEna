/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * GeneralPlayer Game
 * An abstract class that have all the common player features/properties that a game has.
 * The class TEPlayer inherits from this class.
 * The methods in this class controls common part of player: get player name,
 * set player role, get/update/set balance 
 */

public class GeneralPlayer {
	
	private String playerName;
	private String playerRole;
	private int balance;
	//private List<Card> handList = new ArrayList<>();
	//private int bet = 0;
	//private String status;
	//private int finalHandValue;
	
	public GeneralPlayer(String name, String role, int initialBalance) {
		playerName = name;
		playerRole = role;
		balance = initialBalance;
	}
	
	
	
	public String getPlayerName() {
		return playerName;
	}
	
	public String getPlayerRole() {
		return playerRole;
	}

//	public String getStatus() {
//		return status;
//	}
//	
//	public void setStatus(String newStatus) {
//		status = newStatus;
//	}
	
	public void setBanker() {
		this.playerRole = "banker";
	}
	
	public void setPlayer() {
		this.playerRole = "player";
	}
	
	public void updateBalance(int money) {
		this.balance = balance + money;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int num) {
		balance = num;
	}
	
//	public int getFinalHandValue() {
//		return finalHandValue;
//	}
//	
//	public void setFinalHandValue(int value) {
//		finalHandValue = value;
//	}
//	
//	public void setBet(int setBetNumber) {
//		bet = setBetNumber;
//	}
//	
//	public int getBet() {
//		return bet;
//	}
//	
//	
//	public void addHand(Card newCard) {
//		handList.add(newCard);
//	}
//	
//	public List<Card> getHandList() {
//		return handList;
//	}
//	
//	public void clearHandList() {
//		handList.clear();
//	}
	
	public String toString() {
        return "\n \t [ Player: '" + playerName + "'  balance: " + balance + " ]";
    }

}
