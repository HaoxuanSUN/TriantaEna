/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * TEPlayer Game
 * An subclass inherits from GeneralPlayer
 * Specially designed for TE game
 * The methods in this class controls unique function of a TE player: get/set final hand value,
 * set/get bet, add card to hand list, and clear hand list.
 */




import java.util.ArrayList;
import java.util.List;

public class TEPlayer extends GeneralPlayer {

	private List<Card> handList = new ArrayList<>();
	private int bet = 0;
	private String status;
	private int finalHandValue;
	
	public TEPlayer(String name, String role, int initialBalance) {
		super(name, role, initialBalance);
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String newStatus) {
		status = newStatus;
	}
	
	public int getFinalHandValue() {
		return finalHandValue;
	}
	
	public void setFinalHandValue(int value) {
		finalHandValue = value;
	}
	
	public void setBet(int setBetNumber) {
		bet = setBetNumber;
	}
	
	public int getBet() {
		return bet;
	}
	
	
	public void addHand(Card newCard) {
		handList.add(newCard);
	}
	
	public List<Card> getHandList() {
		return handList;
	}
	
	public void clearHandList() {
		handList.clear();
	}
	
	
}
