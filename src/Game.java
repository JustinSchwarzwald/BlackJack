import CardDeck.Card;
import java.util.*;

public class Game {

	static Deck playingDeck;
	static int numOfDeck;
	static Player dealer = new Player();
	static Player player = new Player();
	static Card drawnCard;
	static boolean endTurn;
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) 
	{

		System.out.println("How many decks would you like to play with?");
		numOfDeck = sc.nextInt();
		
		getNewDeck();
		System.out.println("\n\nGame Starting...\n\n");
		
		while(true)
		{
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
			
			System.out.println("\n\nDealer's Hand");
			dealer.printOneCard();
			
			takeTurns();
			
			if(playingDeck.needNewShuffle())
			{
				System.out.println("Deck is low reshuffling");
				getNewDeck();
			}
		}
		
	}
	
	
	static void getNewDeck()
	{
		playingDeck =  new Deck(numOfDeck);
		playingDeck.shuffleDeck();
	}
	
	static int checkWinner()
	{
		if(dealer.getValue()>21)
			System.out.println("Dealer Busted - You Win!");
		else
			if(dealer.getValue() > player.getValue())
				System.out.println("Dealer wins");
			else if(player.getValue() > dealer.getValue())
				System.out.println("You Win!");
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
			System.out.println("Dealer stays at " + dealer.getValue());
			System.out.println("\n\nDealer's Hand");
			System.out.print(dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\n\n\n");
		}
		while(dealer.getValue() < 17)
		{
			System.out.println(" \n\n" + "Your Hand");
			System.out.print(player.getValue() + " = ");
			player.printCards();
			
			drawnCard = playingDeck.drawCard();
			dealer.insertCard(drawnCard);
			
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
			System.out.println("\n\nHit or stay");
			String input = sc.next();
			if(input.compareTo("hit") == 0)
			{
				hit(player);
				System.out.println(" \n\n" + "Your Hand");
				System.out.print(player.getValue() + " = ");
				player.printCards();
				
				if(!player.checkBust())
				{
					System.out.println("\n\nDealer's Hand");
					dealer.printOneCard();
				}
			}
				if(input.compareTo("stay") == 0)
					stay();
		}
		if(player.checkBust())
		{
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
			System.out.print("Dealer\n" + dealer.getValue() + " = ");
			dealer.printCards();
			System.out.println("\nDealer Wins");
		}
		
		player.removeAll();
		dealer.removeAll();
	}
	
}
