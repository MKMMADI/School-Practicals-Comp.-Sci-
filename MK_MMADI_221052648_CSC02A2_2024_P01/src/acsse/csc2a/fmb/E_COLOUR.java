/**@author MK MMADI 221052648
  *@see FireDisplay : a related class
  *
  */

package acsse.csc2a.fmb;

public enum E_COLOUR
{
	RED, 
	GREEN, 
	BLUE, 
	YELLOW, 
	MAGENTA, 
	WHITE, 
	CYAN;
	
	public void Set_Colour(int Colour ,E_COLOUR E_Colour)
	{
		if((Colour>0) && (Colour<8))
		{
			switch(Colour)
			{
				case 1 :
				{
					E_Colour=E_COLOUR.RED;
					break;
				}
				
				case 2:
				{
					E_Colour=E_COLOUR.GREEN;
					break;
				}
				
				case 3:
				{
					E_Colour=E_COLOUR.BLUE;
					break;
				}
				
				case 4 :
				{
					E_Colour=E_COLOUR.YELLOW;
					break;
				}
				
				case 5:
				{
					E_Colour=E_COLOUR.MAGENTA;
					break;
				}
				
				case 6:
				{
					E_Colour=E_COLOUR.WHITE;
					break;
				}
				
				case 7 :
				{
					E_Colour=E_COLOUR.CYAN;
					break;
				}
				
			}
		}else
		{	
			System.err.println("Invalid Selection");
		};
	}
		
}