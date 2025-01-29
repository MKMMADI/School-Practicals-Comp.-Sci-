package acsse.csc2a.supermarket.model;

import java.util.ArrayList;

public class Store 
{
	private ArrayList<Food> Foods = new ArrayList<Food>();
	
	
	
	public Food getFoodAtIndex(int index)
	{
		return Foods.get(index);
	}
	
	public void addFoodItem(Food item)
	{
		Foods.add(item);
	}
	

	public void display() 
	{
		for(Food item :Foods)
		{
			if(item instanceof CannedFood Can)
			{
				System.out.println(Can.toString());
			}
			
			if(item instanceof FrozenFood Frozen)
			{
				System.err.println(Frozen.toString());
			}
		}
	}
	
}
