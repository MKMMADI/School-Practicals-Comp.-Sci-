/**@author MK MMADI 221052648
  *@see FireDisplay : a related class
  */
  
 package acsse.csc2a.fmb;
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
	 
	 
 }
			 
