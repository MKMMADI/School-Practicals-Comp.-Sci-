/**@author MK MMADI 221052648
  *@see FireDisplay : a related class
  *
  */

package acsse.csc2a.fmb;
import java.util.Scanner;
import acsse.csc2a.fmb.E_COLOUR;

public class Firework
{
	private String Firework_ID ;
	private String Firework_Name ;
	private double Firework_Fuse_Length ;
	private E_COLOUR Firework_Colour ;
	
	public Firework(){};
	
	public void setFirework_ID(String ID)
	{
		this.Firework_ID=ID;
	}
	
	public String getFirework_ID()
	{
		return this.Firework_ID;
	}
	
	public void setFirework_Name(String Name)
	{
		this.Firework_Name=Name;
	}
	
	public String getFirework_Name()
	{
		return this.Firework_Name;
	}
	
	public void setFirework_Fuse_Length(double length)
	{
		this.Firework_Fuse_Length=length;
	}
	
	public double getFirework_Fuse_Length()
	{
		return this.Firework_Fuse_Length;
	}
	
	/*public void setFirework_Colour(E_COLOUR Colour)
	{
		
	}*/
	
	public E_COLOUR getFirework_Colour()
	{
		return this.Firework_Colour;
	}
	
}
	
