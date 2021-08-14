package CardDeck;

public class Card {
	int value;
	char suit;
	boolean face;
	boolean ace;

	public Card(int val, char suit) {
		this.value = val;
		this.suit = suit;
		if (val == 1)
		{
			ace = true;
			face = false;
		}
		else if (val >= 10)
		{
			face = true;
			ace = false;
		}
		else
		{
			face = false;
			ace = false;
		}
	}

	public int getValue()
	{
		if (ace)
			return 11;
		else if (face)
			return 10;
		else
			return value;
	}
	
	public char getSuit()
	{
		return suit;
	}
	
	public boolean isFace()
	{
		return face;
	}
	
	public boolean isAce()
	{
		return ace;
	}
	
	public void getCardDetail()
	{
		switch (value){
		case 11:
			System.out.print("J" + "" + suit + " ");
			break;
		case 12:
			System.out.print("Q" + "" + suit + " ");
			break;
		case 13:
			System.out.print("K" + "" + suit + " ");
			break;
		case 1:
			System.out.print("A" + "" + suit + " ");
			break;
		default:
					System.out.print(value + "" + suit + " ");
		}

	}
	
}
