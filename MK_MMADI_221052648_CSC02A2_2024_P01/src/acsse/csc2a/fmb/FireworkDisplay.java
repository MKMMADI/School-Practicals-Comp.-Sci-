/**@author MK MMADI 221052648
  *@see FireDisplay : a related class
  */
  
 package acsse.csc2a.fmb;
 import java.util.Scanner;

import acsse.csc2a.fmb.Pyrotechnician;

 
 public class FireworkDisplay
 {
	 private String Display_ID ;
	 private String Display_Name;
	 private String Diplay_Theme;
	 private Pyrotechnician Lead_Technician;
	 private Firework[] Fireworks;
	 
	 /**@Method initializing "Fireworks" array with 2 "firework" objects
	 */
	 public FireworkDisplay()
	 {
		 this.Lead_Technician = new Pyrotechnician();
		 Fireworks = new Firework[2];
		 for(int i =0 ;i<2;i++)
		 {
			 Fireworks[i]=new Firework();
		 }
	 }
	 
	 public void setDisplay_ID(String ID)
	 {
		 this.Display_ID=ID;
	 }
	 
	 public void setDisplay_Name(String Name)
	 {
		 this.Display_Name=Name;
	 }
	 
	 public void setDiplay_Theme(String Theme)
	 {
		 this.Diplay_Theme=Theme;
	 }
	 
	 public void setLead_Technician(String Name , String PhoneNumber)
	 {
		 this.Lead_Technician.setname(Name);
		 this.Lead_Technician.setPhone_Number(PhoneNumber);
	 }
	 
	 
	  public String getDisplay_ID()
	 {
		 return this.Display_ID;
	 }
	 
	 public String getDisplay_Name()
	 {
		 return this.Display_Name;
	 }
	 
	 public String setDiplay_Theme()
	 {
		 return this.Diplay_Theme;
	 }
	 
	 public String getLead_Technician_Name()
	 {
		 return this.Lead_Technician.getName();
	 }
	 
	 public String getLead_Technician_PhoneNumber()
	 {
		 return this.Lead_Technician.getPhone_Number();
	 }
	 
	 public Firework getFireworks(int index)
	 {
		 return this.Fireworks[index];
	 }
	 
	 public String getDisplayTheme()
	 {
		 return this.Diplay_Theme;
	 }
	 
	 public void printDisplay()
	{
		System.out.format("%-20s %-20s %-20s %-30s %-20s\n", "Display Name", "Display ID", "Display Theme", "Lead Technician Name And Surname", "Lead Technician Phone Number");
	    System.out.format("%-20s %-20s %-20s %-40s %-60s\n", this.getDisplay_Name(), this.getDisplay_ID(), this.getDisplayTheme(), this.getLead_Technician_Name(), this.getLead_Technician_PhoneNumber());
	    
	    System.out.println();
	    System.out.println("Enter a numrical value :");
	    System.out.println("1. Dispaly Firework 1 info");
	    System.out.println("2.Display Firework 2 info");
	    System.out.println("3.Return to main menu");
	    
	    int intChoice=0;
	    Scanner sc = new Scanner(System.in);
	    intChoice=sc.nextInt();
	    
		    switch(intChoice)
		    {
		    	case 1:
		    	{
		    		PrintFirework1();
		    		System.out.println("Enter any key to continiue");
					new Scanner(System.in).nextLine();
		    		break;
		    	}
		    	
		    	case 2:
		    	{
		    		PrintFirework2();
		    		System.out.println("Enter any key to continiue");
					new Scanner(System.in).nextLine();
		    		break;
		    	}
		    	
		    	case 3 :
		    	{
		    		break;
		    	}
		    	
		    	default :
		    	{
		    		System.out.println("Enter any key to continiue");
					new Scanner(System.in).nextLine();
		    	}
		    }
	}
	private void PrintFirework1() 
	{
		System.out.format("%-20s %-20s %-20s %-30s \n", "Firewowrk Name", "Firework ID", "Firework Fuse length", "Colour");
		System.out.format("%-20s %-20s %-20s %-30s \n", this.getFireworks(0).getFirework_Name(),this.getFireworks(0).getFirework_ID()
															, this.getFireworks(0).getFirework_Fuse_Length(),this.getFireworks(0).getFirework_Colour());

	}
	
	private void PrintFirework2() 
	{
		System.out.format("%-20s %-20s %-20s %-30s \n", "Firewowrk Name", "Firework ID", "Firework Fuse length", "Colour");
		System.out.format("%-20s %-20s %-20s %-30s \n", this.getFireworks(1).getFirework_Name(),this.getFireworks(1).getFirework_ID()
															, this.getFireworks(1).getFirework_Fuse_Length(),this.getFireworks(1).getFirework_Colour());

	}
	
	
	public void setFireWorks(String Firework_ID,String Firework_Name, double Firework_Fuse_Length ,int Colour)
	{
		Fireworks[0].setFirework_Name(Firework_Name);
		Fireworks[0].setFirework_ID(Firework_ID);
		Fireworks[0].setFirework_Fuse_Length(Firework_Fuse_Length);
		Fireworks[0].setFirework_Colour( Colour,Fireworks[0].getFirework_Colour());
		
		Fireworks[1].setFirework_Name("The Awe");
		Fireworks[1].setFirework_ID("TA-0114554");
		Fireworks[1].setFirework_Fuse_Length(10.00);
		Fireworks[1].setFirework_Colour(3,Fireworks[1].getFirework_Colour());
	}
	public void addFirework(String Firework_ID,String Firework_Name, double Firework_Fuse_Length ,int intColour)
	{
		Firework NewFireWork = new Firework();
		
		NewFireWork.setFirework_ID(Firework_ID);
		NewFireWork.setFirework_Name(Firework_Name);
		NewFireWork.setFirework_Fuse_Length(Firework_Fuse_Length);
		NewFireWork.setFirework_Colour(intColour,NewFireWork.getFirework_Colour());
	}
	
	
	 
	 
 }
			 
