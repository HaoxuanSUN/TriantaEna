/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * TriantaEna class
 * A subclass inherits from GeneralCardGame
 * Specially designed and implemented for TE game
 * The methods in this class controls unique function of a TE game: player/banker's first round,
 * player/banker's later round, get cards, check bust, round over operation and check whether a banker's 
 * hand value are all bigger than a special value.
 */



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TriantaEna extends GeneralCardGame {
    
	// all parameters that TriantaEna need
	public TriantaEna() {
    	gameName = "Trianta Ena";
    	playerInitalBalance = 100;
    	bankerBalanceFactor = 3;
    	deckCount = 2;
        playerValueMax = 31;
        bankerValueMax = 27;
    }

	@Override
	// each player get chance to get first card and choose whether to bet or fold
	protected void playerFirstRound(Deck deck, List<TEPlayer> playerList) {
		for (TEPlayer eachPlayer : playerList) {
			System.out.println();
			System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			List<Integer> handValueList = new ArrayList<>();
			List<Integer> validHandValueList = new ArrayList<>();
			System.out.println(eachPlayer.getPlayerName() + "'s turn now:      Initial balance: " + eachPlayer.getBalance());
			// get a new card from deck
			Card nextCard = deck.getNextCard();
			// player's hand add a new card
			eachPlayer.addHand(nextCard);
			handValueList.clear();
			validHandValueList.clear();
			// get hand value of currently hand
			handValueList = getHandValue(eachPlayer, handValueList);
			// get all possible valid hand value
			for (Integer eachhandValueList : handValueList) {
				validHandValueList.add(eachhandValueList);
			}
			// print player's first card and hand value
			System.out.println(eachPlayer.getPlayerName() + "'s first card: " +  eachPlayer.getHandList().toString() + "       hands value: " + validHandValueList.toString());				
			// ask for bet or fold
			String getPlayerDecision;
			while (true) {
				System.out.print("Decide to bet('b') or fold('f'): ");
				getPlayerDecision = new Scanner(System.in).nextLine();
				if (getPlayerDecision.equals("b") || getPlayerDecision.equals("f")) {
					break;
				}
			}
			// if player choose fold, leave this round
			if (getPlayerDecision.equals("f")) {
				System.out.println(eachPlayer.getPlayerName() + " choose to fold. Leave game.");
			} else if (getPlayerDecision.equals("b")) {
				// if player choose bet, add player into bet list
				betplayerList.add(eachPlayer);
				int getPlayerBetInt;
				// get player's bet and check whether it's valid
				while(true) {
					System.out.print("Enter your bet: ");
					String getPlayerBet = new Scanner(System.in).nextLine();
					getPlayerBetInt = Integer.parseInt(getPlayerBet);
					if ((eachPlayer.getBalance() - getPlayerBetInt) < 0) {
						System.out.print("Invalid bet.");
					} else {
						break;
					}
				}
				// set player's bet in Player class
				eachPlayer.setBet(getPlayerBetInt);
				// deduct bet from player's balance
				eachPlayer.updateBalance(getPlayerBetInt * -1);
			}
		}
	}

	
	@Override
	// Banker's first round. banker get the first card
	protected void bankerFirstRound(Deck deck, List<TEPlayer> bankerList) {
		for (TEPlayer eachBanker : bankerList) {
			System.out.println();
			System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
			List<Integer> handValueList = new ArrayList<>();
			List<Integer> validHandValueList = new ArrayList<>();
			System.out.println("Banker gets a card");
			eachBanker.clearHandList();
			// get a new card from deck
			Card nextCard = deck.getNextCard();
			// add the new card to the player's hand
			eachBanker.addHand(nextCard);
			// get hand value
			handValueList = getHandValue(eachBanker, handValueList);
			// get all valid hand value 
			for (Integer eachhandValueList : handValueList) {
				validHandValueList.add(eachhandValueList);
			}
			System.out.println("Banker " + eachBanker.getPlayerName() + "'s first card: " +  eachBanker.getHandList().toString() + "       hands value: " + validHandValueList.toString());
		}
	}

	@Override
	// player still in game get 2 cards first and decide to hit or stand
	protected void playerLaterRound(Deck deck, List<TEPlayer> betplayerList) {
		while (true) {
			List<TEPlayer> temp = new ArrayList<TEPlayer>();
			for (TEPlayer eachPlayer : betplayerList) {
				temp.add(eachPlayer);
			}
			for (TEPlayer eachPlayer : temp) {
				System.out.println();
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				List<Integer> handValueList = new ArrayList<>();
				List<Integer> validHandValueList = new ArrayList<>();
				System.out.println(eachPlayer.getPlayerName() + "'s turn now:      balance: " + eachPlayer.getBalance());
				// get first of the 2 cards
				Card nextCard = deck.getNextCard();
				eachPlayer.addHand(nextCard);
				// get second of the 2 cards
				nextCard = deck.getNextCard();
				eachPlayer.addHand(nextCard);
				// compute the first 3 cards hand value
				handValueList = getHandValue(eachPlayer, handValueList);
				// get all valid hand value of a player's hand list
				for (Integer eachValidHandValueList : getAllValidHandValue(handValueList)) {
					validHandValueList.add(eachValidHandValueList);
				}
				System.out.println(eachPlayer.getPlayerName() + "'s card: " +  eachPlayer.getHandList().toString() + "       hands value: " + validHandValueList.toString());
	
				// Continuously get a card until bust / stand
				getCards(deck, eachPlayer, handValueList, validHandValueList, betplayerList);
			}
			break;
		}
	}

	// a player continuously get a card until bust / stand
	private void getCards(Deck deck, TEPlayer eachPlayer, List<Integer> handValueList, List<Integer> validHandValueList, List<TEPlayer> betplayerList) {
		while (true) {
			// Ask player whether to hit of stand
			System.out.print("Decide to hit('h') or stand('s'): ");
			String getPlayerDecision= new Scanner(System.in).nextLine();
			// player choose to stand
			if (getPlayerDecision.equals("s")) {
				// set player's status to 's'
				eachPlayer.setStatus("s");
				// set this player's final hand value
				eachPlayer.setFinalHandValue(Collections.max(validHandValueList));
				System.out.println(eachPlayer.getPlayerName() + " choose to stand. Good choice!");
				//System.out.println("             getFinalHandValue: " + eachPlayer.getFinalHandValue());
				break;
			} else if (getPlayerDecision.equals("h")) {
				// player choose to bet
				// set player's status to 'h'
				eachPlayer.setStatus("h");
				// get a new card
				Card nextCard = deck.getNextCard();
				// add new card to layer's hand list
				eachPlayer.addHand(nextCard);
				// get all player's hand value according to hand list
				handValueList = getHandValue(eachPlayer, handValueList);
				//System.out.println("             handValueList: " + handValueList);
				validHandValueList.clear();
				// get all valid hand value
				for (Integer eachValidHandValueList : getAllValidHandValue(handValueList)) {
					validHandValueList.add(eachValidHandValueList);
				}
				//System.out.println("             validHandValueList: " + validHandValueList);
				// check if a player gone bust now, if player's hand is bust, leave game
				if (checkBust(validHandValueList) == 1) {
					// set a bust player's hand value to 0
					eachPlayer.setFinalHandValue(0);
					//System.out.println("             getFinalHandValue: " + eachPlayer.getFinalHandValue());
					System.out.println(eachPlayer.getPlayerName() + "  BUST. Leave game.");
					// clear hand list if a player's bust
					eachPlayer.clearHandList();
					for (TEPlayer eachbanker : bankerList) {
						// banker wins the player's bet
						eachbanker.updateBalance(eachPlayer.getBet());
						System.out.println(eachbanker.getPlayerName() + " wins " + eachPlayer.getBet());
					}
					// remove this player out of the list
					betplayerList.remove(eachPlayer);
					break;
				}
				// set player's final hand value
				eachPlayer.setFinalHandValue(Collections.max(validHandValueList));
				System.out.println(eachPlayer.getPlayerName() + "'s card: " +  eachPlayer.getHandList().toString() + "       hands value: " + validHandValueList.toString());
				//System.out.println("             getHandValue: " + handValueList);
				//System.out.println("             getValidHandValue: " + validHandValueList);
				if (eachPlayer.getFinalHandValue() == 31) {
					System.out.println(eachPlayer.getPlayerName() + " reaches 31.");
					break;
				}
			}
		}
		
	}

	// check whether a player gone bust
	private int checkBust(List<Integer> validHandValueList) {
		int checkBust = 0;
		// no valid hand value, then it's bust
		if (validHandValueList.isEmpty()) {
			checkBust = 1;
		}
		return checkBust;
	}

	
	@Override
	// banker starts receiving cards, until all hand value reaches 27.
	protected void bankerLaterRound(Deck deck, List<TEPlayer> bankerList) {
		for (TEPlayer eachBanker : bankerList) {
			List<Integer> handValueList = new ArrayList<>();
			while (true) {
				System.out.println();
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				handValueList.clear();
				// banker get a new card
				Card nextCard = deck.getNextCard();
				// add hand to banker's hand list
				eachBanker.addHand(nextCard);
				// get hand value list
				handValueList = getHandValue(eachBanker, handValueList);
				System.out.println("Banker " + eachBanker.getPlayerName() + "'s card: " +  eachBanker.getHandList().toString() + "       hands value: " + handValueList.toString());
				// if all hand value above 27, then stop receive cards
				if (checkAllAbove27(handValueList)) {
					// set banker's final hand value ( the max value )
					eachBanker.setFinalHandValue(Collections.max(handValueList));
					System.out.println("Banker " + eachBanker.getPlayerName() + "'s reaches 27 (" + eachBanker.getFinalHandValue() + "). Stand");
					break;
				}
			}
			break;
		}
	}

	// check whether banker's all valid hand value are above 27
	private boolean checkAllAbove27(List<Integer> handValueList) {
		boolean checkAllAbove27 = false;
		int countAbove27 = 0;
		// if hand value > 27, record
		for (Integer eachHandValueList : handValueList) {
			if (eachHandValueList >= bankerValueMax) {
				countAbove27++;
			}
		}
		// if record num (of hand value > 27) = hand value list (means all value)
		if (countAbove27 == handValueList.size()) {
			// set true
			checkAllAbove27 = true;
		}
		return checkAllAbove27;
	}

	
	@Override
	// round stops. check winner and update everone's balance
	protected void roundOver(List<TEPlayer> bankerList, List<TEPlayer> betplayerList, List<TEPlayer> playerList) {
		for (TEPlayer eachBanker : bankerList) {
			// banker wins with the Natural Trianta Ena
			if (eachBanker.getFinalHandValue() == playerValueMax) {
				System.out.println();
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				System.out.println("Banker wins with a Natural Trianta Ena.");
				for (TEPlayer eachplayer : betplayerList) {
					// banker get gets from every player
					eachBanker.updateBalance(eachplayer.getBet());
				}
				System.out.println("ROUND END");
			} else {
				System.out.println();
				System.out.println("-  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
				for (TEPlayer eachplayer : betplayerList) {
					eachplayer.clearHandList();
					// if player's value < banker's
					if (eachplayer.getFinalHandValue() < eachBanker.getFinalHandValue()) {
						// banker get bet
						eachBanker.updateBalance(eachplayer.getBet());
						System.out.println(eachBanker.getPlayerName() + " wins " + eachplayer.getBet() + "     " + eachBanker.getFinalHandValue() + ">" + eachplayer.getFinalHandValue());
					} else if (eachplayer.getFinalHandValue() > eachBanker.getFinalHandValue()) {
						// if banker's value < player's
						// banker lost bet
						eachBanker.updateBalance(eachplayer.getBet() * -1);
						// player get his bet back and win bet
						eachplayer.updateBalance(eachplayer.getBet() * 2);
						System.out.println(eachplayer.getPlayerName() + " wins " + eachplayer.getBet() + "     " + eachplayer.getFinalHandValue() + ">" + eachBanker.getFinalHandValue());
					} else {	
						 // peace, player get his bet back
						eachplayer.updateBalance(eachplayer.getBet());
					}
				}
				System.out.println();
				System.out.println("ROUND END");
				System.out.println("Banker: " + bankerList.toString());
				System.out.println("Player: " + playerList.toString());
			}
		}
	}

	
}