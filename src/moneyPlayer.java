
public class moneyPlayer extends Player {
	int money = 1000;

	int getMoney()
	{
		return money;
	}
	
	void updateMoney(int val)
	{
		money = money + val;
	}
	
	boolean isBankrupt()
	{
		if (money < 1) 
			return true;
		else
			return false;
	}
}
