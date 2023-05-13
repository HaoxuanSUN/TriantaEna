/**
 * # CS611 - Assignment 2
 * ##TrianraEna
 * <Student Name:  Haoxuan Sun   >
 * <Student Email: hs4379@bu.edu >
 * <Student BUID:  U58198360     >
 *
 * Game Library Class
 * The home page of this program. When run the game for the 
 * first time, or exit a game, user will back to game library for further 
 * operation.  
 */

import java.util.Scanner;

public class GameLibrary {
	// start game
    public static void start() {
	    System.out.println();
	    System.out.println();
	    System.out.println();
    	System.out.println("----------------------------------------------------------");
	   	System.out.println("                  Welcome to Game Library                 ");
	   	System.out.println("----------------------------------------------------------");
	    System.out.println();
	   	printGameList();
	    System.out.println();
	   	System.out.print("Enter number to start game or exit: ");

	   	
        while (true) {
    	    String selectnumber = new Scanner(System.in).nextLine();
    	    // Choose EXIT
		    if (selectnumber.equals(GameList.Exit.getGameNum())) {
		    	System.out.println("Leaving game...Thank you! Hope you come back soon!");
		        System.exit(1);;
		    // choose Show_Developer_Info
		    } else if (selectnumber.equals(GameList.Show_Developer_Info.getGameNum())) {
		    	System.out.println("Haoxuan Sun ~ U58198360 ~ hs4379@bu.edu");
		    	System.out.print("Enter number to start game or exit: ");
		    	continue;
		    } else {
	    		GeneralCardGame creategame = createGame(selectnumber);
	            if (creategame == null) {
	                System.out.print("Please enter valid number. ");
	            } else {
	            	creategame.start();
	            }
	        }
        }
    }

    // print game list
	private static void printGameList() {
		GameList[] gamelistvalue = GameList.values();
        for(GameList eachgamelistvalue : gamelistvalue){
            System.out.println(eachgamelistvalue);
        }
	}
	
	// create a game instance base on what game the user just choose
	public static GeneralCardGame createGame(String number){
		if(number.equals(GameList.TriantaEna.getGameNum())){
			return new TriantaEna();
		} else {
	        return null;
	    }
	}
}
