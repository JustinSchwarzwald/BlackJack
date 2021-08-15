import java.util.ArrayList;

import CardDeck.Card;

public class Player {
	ArrayList<Card> hand; 
	int value;
	int aces;

	Player()
	{
		this.value = 0;
		this.aces = 0;
		hand = new ArrayList<Card>();
	}
	
	void insertCard(Card card)
	{
		hand.add(card);
		if(card.isAce())
			aces = aces + 1;
		value = value + card.getValue();
		if(value > 21 && card.isAce())
			value = value - 10;
	}
	
	void removeAll()
	{
		hand.clear();
		value = 0;
		aces = 0;
	}
	
	int getValue()
	{
		int tempValue = value;
		
		if(value > 21 && aces > 0)
		{
			for(int i = 0; i < aces; i++) 
			{
				tempValue = tempValue - 10;
				
				if(tempValue < 21)
					return tempValue;
			}
			return tempValue;
			
		}
		else 
			return tempValue;
	}
	
	void printCards()
	{
		for (int i = 0; i < hand.size(); i++)
		{
			hand.get(i).getCardDetail();
			if(i != hand.size()-1)
				System.out.print(", ");
		}
	}

	public void printOneCard() 
	{
		hand.get(0).getCardDetail();
		System.out.println(", Unknown");
	}
	
	public boolean checkBust()
	{
		if(value - aces*10 > 21)
			return true;
		else return false;
	}
}
