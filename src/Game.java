import CardDeck.Card;
import java.util.*;

public class Game {

	static Deck playingDeck;
	static int numOfDeck;
	static Player dealer = new Player();
	static moneyPlayer player = new moneyPlayer();
	static Card drawnCard;
	static boolean endTurn;
	static Scanner sc = new Scanner(System.in);
	static int quickSleep = 1200;
	static int wager = 0;
	static String input;
	static int iWager = -1;
	public static void main(String[] args) 
	{

		System.out.println("How many decks would you like to play with?");
		numOfDeck = sc.nextInt();
		
		getNewDeck();
		System.out.println("\n\nGame Starting...\n\n");
		
		while(!player.isBankrupt())
		{
			int amountAvail = player.getMoney();
			System.out.println("Your balance is " + amountAvail);
			System.out.println("How much would you like to wager?");
			wager = sc.nextInt();
			if(wager > amountAvail)
				//TODO: add code to stop wager being over the amount that the player has
			{}
			
			
			drawnCard = playingDeck.drawCard();
			player.insertCard(drawnCard);
			drawnCard = playingDeck.drawCard();
			dealer.insertCard(drawnCard);
			
	
			
			
			drawnCard = playingDeck.drawCard();
			player.insertCard(drawnCard);
			drawnCard = playingDeck.drawCard();
			dealer.insertCard(drawnCard);
			
			
			System.out.println("Your Hand");
			System.out.print(player.getValue() + " = ");
			player.printCards();
			
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\n\nDealer's Hand");
			dealer.printOneCard();
			
			if(dealer.hand.get(0).getValue() == 1)
				System.out.println("Would you like insurance? \n Yes/No");
			input = sc.next();
			//TODO: insurance cannot be more than half of wager and must have funds
			System.out.println("How much would you like to wager on insurance");
			iWager = sc.nextInt();
			
			
			switch (checkBlackJack())
			{
				case 1:
					System.out.println("You Win - BlackJack!");
					break;
					
				case 2:
					System.out.println("Dealer Wins - BlackJack!");
					break;
			
				case 3:
					System.out.println("Push!");
					break;
			
				default:
					takeTurns();
					break;
			}
				
			
			
			if(playingDeck.needNewShuffle())
			{
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Deck is low reshuffling\n\n");
				getNewDeck();
			}
		}
		
		System.out.println("You are out of money");
		
	}
	
	
	private static int checkBlackJack() {
		if(player.getValue() == 21)
			if(dealer.getValue() == 21)
				return 3;
			else
				return 1;
		if(dealer.getValue() == 21)
			return 2;	
		
		return 0;
	}


	static void getNewDeck()
	{
		playingDeck =  new Deck(numOfDeck);
		playingDeck.shuffleDeck();
	}
	
	static int checkWinner()
	{
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(dealer.getValue()>21)
		{
			System.out.println("Dealer Busted - You Win!");
			player.updateMoney(wager);
		}
		else
			if(dealer.getValue() > player.getValue())
			{
				System.out.println("Dealer wins");
				player.updateMoney(wager*-1);
			}
			else if(player.getValue() > dealer.getValue())
			{
				System.out.println("You Win!");
				player.updateMoney(wager);
			}
			else
				System.out.println("Push");
		return 0;
	}
	
	static void hit(Player person) 
	{
		drawnCard = playingDeck.drawCard();
		person.insertCard(drawnCard);
	}
	
	static void stay()
	{
		endTurn = true;
	}
	
	
	static void dealerTurn()
	{
		if(dealer.getValue() >= 17 )
		{
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Dealer stays at " + dealer.getValue());
			System.out.println("\n\nDealer's Hand");
			System.out.print(dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\n\n\n");
		}
		while(dealer.getValue() < 17)
		{
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(" \n\n" + "Your Hand");
			System.out.print(player.getValue() + " = ");
			player.printCards();
			
			System.out.println("\n\nDealer's Hand");
			System.out.print(dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\n");
			
			System.out.print("\n\n\nDealer Hits");
			drawnCard = playingDeck.drawCard();
			dealer.insertCard(drawnCard);
			
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\n\nDealer's Hand");
			System.out.print(dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\n\n\n");
			

		}	
	}
	
	static boolean playerTurn()
	{
		while(player.checkBust() == false && !endTurn)
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\n\nHit or stay");
			input = sc.next();
			if(input.compareTo("hit") == 0)
			{
				try {
					Thread.sleep(900);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				hit(player);
				System.out.println(" \n\n" + "Your Hand");
				System.out.print(player.getValue() + " = ");
				player.printCards();
				
				if(!player.checkBust())
				{
					try {
						Thread.sleep(900);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					System.out.println("\n\nDealer's Hand");
					dealer.printOneCard();
				}
			}
				if(input.compareTo("stay") == 0)
					stay();
		}
		if(player.checkBust())
		{
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("\nYou Busted\n\n");
			return false;
		}
		else 
			return true;
	}

	static void takeTurns()
	{
		if(playerTurn())
		{
			dealerTurn();
			checkWinner();
		}
		else
		{
			try {
				Thread.sleep(900);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.print("Dealer\n" + dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\nDealer Wins");
			player.updateMoney(wager*-1);
		}
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("\n\n\nNew Hand\n\n\n");
		player.removeAll();
		dealer.removeAll();
		iWager = -1;
		endTurn = false;
	}
	
}
