/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * General Card Game
 * An abstract class that have all the common card game features/properties that a game has.
 * The class TriantaEna inherits from this class.
 * The methods in this class controls most part of game: get user name/number,
 * start game/ start first round / game over/ and get hand value/  check play again/
 * check switch role / print summary
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class GeneralCardGame {

	protected String gameName;
	protected int allplayerCount;		// player number; # of banker + # of player		
	protected int playerInitalBalance;	// player's  initial balance in each game
	protected int bankerBalanceFactor;	// banker's initial balance factor
    private Deck deck;
    protected int deckCount;		// deck count in each game
    protected int playerValueMax;	// player's hand value max
    protected int bankerValueMax;	// banker's hand value max
	
	protected List<TEPlayer> bankerList = new ArrayList<TEPlayer>();
	protected List<TEPlayer> playerList = new ArrayList<TEPlayer>();
	protected List<TEPlayer> betplayerList = new ArrayList<TEPlayer>();
	
	
	// initialize the game, getting player's number and name. and shuffle the deck. 
	public void init() {
		bankerList.clear();
		playerList.clear();
		String getplayerName;
		System.out.println();
		System.out.println("Welcome to "+ gameName);
		
		// get number of players of a game
		System.out.print("How many players are playing this game? (Banker + Player): ");
		String getPlayerCount= new Scanner(System.in).nextLine();
		allplayerCount = Integer.parseInt(getPlayerCount);
		
		// get banker's name
		System.out.print("Please enter the banker's name: ");
		getplayerName= new Scanner(System.in).nextLine();
		// new banker
		bankerList.add(new TEPlayer(getplayerName, "Banker", playerInitalBalance * bankerBalanceFactor));
		
		// get [layer's name and new player
		for (int i=0; i<allplayerCount-1; i++) {
			System.out.print("Please enter next player's name: ");
			getplayerName = new Scanner(System.in).nextLine();
	        playerList.add(new TEPlayer(getplayerName, "Player", playerInitalBalance));
		}
		
		// create deck based on how many decks a game need.
		deck = new Deck(deckCount);
		// deck shuffle
		deck.shuffle();
	}


	public void startGame() {
		System.out.println("");
		System.out.println("New Round:");
		
		while (true) {
			deck.shuffle();
			betplayerList.clear();
			
			// each player get a card and decides whether to bet or fold
			playerFirstRound(deck, playerList);
			
			// Banker's first round. banker get the first card
			bankerFirstRound(deck, bankerList);
			
			// player still in game get 2 cards first and decide to hit or stand
			playerLaterRound(deck, betplayerList);
			
			// after all player stands or busts, banker starts receiving cards, until hand value reaches 27.
			bankerLaterRound(deck, bankerList);
			
			// round stops. check winner and update everone's balance
			roundOver(bankerList, betplayerList, playerList);

			// Round end. 
			gameOver(playerList, bankerList);
		}
		   
    }
	


	// each player get a card and decides whether to bet or fold
	protected abstract void playerFirstRound(Deck deck, List<TEPlayer> playerList);

	// Banker's first round. banker get the first card
	protected abstract void bankerFirstRound(Deck deck, List<TEPlayer> bankerList);

	// player still in game get 2 cards first and decide to hit or stand
	protected abstract void playerLaterRound(Deck deck, List<TEPlayer> betplayerList);

	// after all player stands or busts, banker starts receiving cards, until hand value reaches 27.
	protected abstract void bankerLaterRound(Deck deck, List<TEPlayer> bankerList);
	
	// round stops. check winner and update everone's balance
	protected abstract void roundOver(List<TEPlayer> bankerList, List<TEPlayer> betplayerList, List<TEPlayer> playerList);
			
	// get hand value of a player's hand list
	public List<Integer> getHandValue(TEPlayer player, List<Integer> handValueList) {
		handValueList.clear();
		int handValue1 = 0;
		int handValue2 = 0;
		List<Card> handList = new ArrayList<>();
		handList = player.getHandList();
		
		// get count of Ace card in hand
		int aceCardCount = getAceNumber(handList);
		//System.out.println("                                       detect " + player.getPlayerName() + " has " + aceCardCount + " Ace cards");
		for (Card eachcard : handList) {
			// no Ace card in hand
			if (aceCardCount == 0) {
				handValue1 += eachcard.getValueType().getValueTypeValue1();
			} else if  (aceCardCount == 1) {	// if 1 Ace card in hand, 2 possible hand value
				handValue1 += eachcard.getValueType().getValueTypeValue1();
				handValue2 += eachcard.getValueType().getValueTypeValue2();
			} else if (aceCardCount == 2) {		// if 2 Ace cards in hand, 3 possible hand value
				handValue1 += eachcard.getValueType().getValueTypeValue1();
				handValue2 += eachcard.getValueType().getValueTypeValue2();
			} else { // aceCardCount == 3	if 3 Ace cards in hand, 4 possible hand value
				handValue1 += eachcard.getValueType().getValueTypeValue1();
				handValue2 += eachcard.getValueType().getValueTypeValue2();
			}
		}
		
		if (aceCardCount == 0) {
			handValueList.add(handValue1);
		} else if  (aceCardCount == 1) {	// if 1 Ace card in hand, 2 possible hand value
			handValueList.add(handValue1);
			handValueList.add(handValue2);
		} else if (aceCardCount == 2) {		// if 2 Ace cards in hand, 3 possible hand value
			handValue1 = handValue1 + 10;
			handValueList.add(handValue1);
			handValueList.add(handValue2);
		} else { // aceCardCount == 3	if 3 Ace cards in hand, 4 possible hand value
			handValue1 = handValue1 + 10 + 10;
			handValueList.add(handValue1);
			handValueList.add(handValue2);
		}
		return handValueList;
	}
	
	// count the number of Ace card
	private int getAceNumber(List<Card> handList) {
		int count = 0;
		for (Card eachcard : handList) {
			// if vard value is "A", count
			if (eachcard.getValueType().getValueTypeNotation().equals("A")) {
				count++;
			}
		}
		return count;
	}

	// get how many of hand values are valid based on game settings
	public List<Integer> getAllValidHandValue(List<Integer> handValueList) {
		List<Integer> allValidHandValueList = new ArrayList<>();
		for (Integer eachInteger : handValueList) {
			if (eachInteger <= playerValueMax) {
				allValidHandValueList.add(eachInteger);
			}
		}
		return allValidHandValueList;
	}

	// game over. 
	protected void gameOver(List<TEPlayer> playerList, List<TEPlayer> bankerList) {
		// if player's balance is smaller than 0, kick him out
		List<TEPlayer> temp = new ArrayList<>();
		for (TEPlayer eachPlayer: playerList) {
			temp.add(eachPlayer);
		}
		
		// if balance < 0, remove from list
		for (TEPlayer eachtemp : temp) {
			if (eachtemp.getBalance() <= 0) {
				System.out.println(eachtemp.getPlayerName() + "'s balance <= 0. Leave game.");
				playerList.remove(eachtemp);
			}
		}
		
		// all player's bankrupt, restart game.
		if (playerList.isEmpty()) {
			System.out.println("All players are bankrupt. GAME OVER");
			gameSummary();
			GameLibrary.start();
		}
		
		// all banker is bankrupt, restart game.
		if (bankerList.get(0).getBalance() <= 0) {
			System.out.println("Banker is bankrupt. GAME OVER");
			gameSummary();
			GameLibrary.start();
		}
		
		// want to play again
		if (again()) {
			//start the game again
			switchRole();
		} else {       
			// stop game and back to game library page
			// print game player summary
			gameSummary();
			GameLibrary.start();
		}
	}


	// check want to play again
	private boolean again() {
		while (true) {
			System.out.print("Do you want to play again? (y/n): ");
			String again = new Scanner(System.in).nextLine();
			if (again.equals("y")) {
				// play again
				return true;
			} else if (again.equals("n")) {
				// don't play again
				return false;
			}
		}
	}
	
	// check want to switch role of banker and any player
	private void switchRole() {
		List<TEPlayer> temp = new ArrayList<>();
		for (TEPlayer eachPlayer: playerList) {
			temp.add(eachPlayer);
		}
		// for every player, ask if want to be the banker
		for (TEPlayer eachtemp : temp) {
			String beBanker;
			while (true) {
				System.out.print(eachtemp.getPlayerName() + ",  Do you want to be the BANKER? (y/n): ");
				beBanker = new Scanner(System.in).nextLine();
				if (beBanker.equals("y") || beBanker.equals("n")) {
					break;
				}
			}
			// want to play again
			if (beBanker.equals("y")) {
				// move new banker to banker list and move new-added player to player list
				bankerList.get(0).clearHandList();
				temp.add(bankerList.get(0));
				temp.remove(eachtemp);
				bankerList.clear();
				bankerList.add(eachtemp);
				
				playerList.clear();
				for (TEPlayer eacht: temp) {
					playerList.add(eacht);
				}
				
				// initialize banker's balance
				for (TEPlayer eachBanker: bankerList) {
					eachBanker.setBalance(playerInitalBalance * bankerBalanceFactor);
				}
				// initialize each player's balance
				for (TEPlayer eachPlayer: playerList) {
					eachPlayer.setBalance(playerInitalBalance);
				}
				System.out.println();
				System.out.println("Banker: " + bankerList.toString());
				System.out.println("Player: " + playerList.toString());
				break;				
			}
		}
		// no one want to be banker, restart a new round
    	startGame();
	}
	
	// print game player summary
	private void gameSummary() {
		System.out.println();
		System.out.println();
		System.out.println("Banker: " + bankerList.toString());
		System.out.println("Player: " + playerList.toString());
		System.out.println();
		System.out.println();
	}

	// start point of the whole class.
	public void start() {
    	init();
    	startGame();
	}

}
