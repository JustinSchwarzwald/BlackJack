import CardDeck.Card;
import java.util.*;

public class Game {

	static Deck playingDeck;
	static int numOfDeck;
	static Player dealer = new Player();
	static Player player = new Player();
	static Card drawnCard;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("How many decks would you like to play with?");
		numOfDeck = sc.nextInt();
		
		getNewDeck();
		System.out.println("Game Starting...\n\n");
		
		
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
		
		
	}
	
	
	static void getNewDeck()
	{
		playingDeck =  new Deck(numOfDeck);
		playingDeck.shuffleDeck();
	}
	
	static int checkWinner(Player player, Player dealer)
	{
		//TODO:add logic and replace return
		return 0;
	}
	
	void hit() 
	{
		
	}
	
	void stay()
	{
		
	}

}
