import CardDeck.Card;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	int count;
	ArrayList<Card> cardList = new ArrayList<Card>();
	
	Deck()
	{
		this.count = 52;
		
		for(int i = 0; i < 52/4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if (j==0)
				{
					cardList.add(new Card(i+1, 'h'));
				}
				else if (j == 1)
				{
					cardList.add(new Card(i+1, 'd'));
				}
				else if (j == 2)
				{
					cardList.add(new Card(i+1, 'c'));
				}
				else
				{
					cardList.add(new Card(i+1, 's'));
				}
			}
		}
	}
	
	Deck(int num)
	{
		this.count = num * 52;
		for(int d = 0; d < num; d++)
		{
			for(int i = 0; i < 52/4; i++)
			{
				for(int j = 0; j < 4; j++)
				{
					if (j==0)
					{
						cardList.add(new Card(i+1, 'h'));
					}
					else if (j == 1)
					{
						cardList.add(new Card(i+1, 'd'));
					}
					else if (j == 2)
					{
						cardList.add(new Card(i+1, 'c'));
					}
					else
					{
						cardList.add(new Card(i+1, 's'));
					}
				}
			}
		}
	}
	
	
	int getCount()
	{
		return count;
	}

	boolean deckEmpty()
	{
		if (count == 0)
			return true;
		else
			return false;
					
	}
	Card peekFirstCard()
	{
		return cardList.get(0);
	}
	
	Card drawCard()
	{
		Card temp = cardList.get(0);
		cardList.remove(0);
		return temp;
	}
	
	void shuffleDeck()
	{
		Collections.shuffle(cardList);
	}
}
