package acsse.csc2a.supermarket.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acsse.csc2a.supermarket.model.CannedFood;
import acsse.csc2a.supermarket.model.EDietType;
import acsse.csc2a.supermarket.model.FrozenFood;
import acsse.csc2a.supermarket.model.Store;

public class FoodFileHandler
{
	public static Store readStore(File FILE)
	{
		Store FloorMart=new Store();
		
			try(Scanner SC = new Scanner(FILE))
			{

				while(SC.hasNextLine())
				{
					String Line = "";
					StringTokenizer Split = new StringTokenizer(Line,"\t");
					String barcode="";
					String quality="";
					double weight=0.0;
					int temparature=0;
					EDietType diet=null;

					if(isCannedFood(Line)==true)
					{
						barcode = Split.nextToken();
						quality=Split.nextToken();
						weight=Double.parseDouble(Split.nextToken());
						FloorMart.addFoodItem(new CannedFood(barcode, quality, weight));
					}else if(isFrozenFood(Line)==true)
					{
						barcode = Split.nextToken();
						quality=Split.nextToken();
						temparature=Integer.parseInt(Split.nextToken());
						diet=EDietType.valueOf(Split.nextToken());
						FloorMart.addFoodItem(new FrozenFood(barcode, quality, temparature, diet));
					}
				}

			}catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			return FloorMart;
		}
		
	


	


private static boolean isFrozenFood(String line)
{
	String regex = "([A-Z]{4}\\d{2}[A-Z]{2})\\t([A-Za-z]+)\\t([-]\\d+)\\t([VEGETARIAN|LACTOFREE|MEAT|VEGAN])";
	Pattern ptn = Pattern.compile(regex);
	Matcher mt = ptn.matcher(line);

	if(mt.matches())
	{
		return true;
	}
	else
	{
		return false;
	}
}

private static boolean isCannedFood(String line)
{
	String regex = "([A-Z]{4}\\d{2}[A-Z]{2})\\t([A-Za-z]+)\\t(\\d+[.]\\d+)";
	Pattern ptn = Pattern.compile(regex);
	Matcher mt = ptn.matcher(line);

	if(mt.matches())
	{
		return true;
	}
	else
	{
		return false;
	}
}
}
