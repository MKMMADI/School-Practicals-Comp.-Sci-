import java.io.File;
import java.util.ArrayList;

import acsse.csc2a.supermarket.file.FoodFileHandler;
import acsse.csc2a.supermarket.model.Store;

public class Main 
{

	public static void main(String[] args) 
	{
		File Data = new File("data");

		String[] Files = Data.list();

		ArrayList<Store> FloorMartStores = new ArrayList<Store>();

		for(String strFile : Files)
		{
			System.out.println(strFile);
			FloorMartStores.add(FoodFileHandler.readStore(new File("./data/"+strFile)));
			
		}
		System.out.println("Printing");
		for(Store Store : FloorMartStores)
		{
			Store.display();
		}
	}

}
