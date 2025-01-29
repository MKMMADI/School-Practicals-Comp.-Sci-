package acsse.csc2a.supermarket.model;

public class FrozenFood extends Food
{
	
	private int temparature ;
	private EDietType diet;

	public FrozenFood(String barcode, String quality,int temparature , EDietType diet) 
	{
		super(barcode, quality);
		this.temparature=temparature;
		this.diet=diet;
	}

	/**
	 * @return the temparature
	 */
	public int getTemparature() 
	{
		return temparature;
	}

	/**
	 * @param temparature the temparature to set
	 */
	public void setTemparature(int temparature) 
	{
		this.temparature = temparature;
	}

	/**
	 * @return the diet
	 */
	public EDietType getDiet() 
	{
		return diet;
	}

	/**
	 * @param diet the diet to set
	 */
	public void setDiet(EDietType diet) 
	{
		this.diet = diet;
	}
	
	@Override
	public String toString() 
	{
		return super.toString()+"\tTemperature :"+ this.temparature +"\tDiet Type :" + this.diet;
	}
	
	
	
	
	
	
}
