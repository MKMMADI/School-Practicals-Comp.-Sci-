package acsse.csc2a.supermarket.model;

public class CannedFood extends Food
{

	private double weight ; 

	public CannedFood(String barcode, String quality,double wieght) 
	{
		super(barcode, quality);
		this.weight=wieght;
	}
	
	

	/**
	 * @return the weight
	 */
	public double getWeight() 
	{
		return weight;
	}



	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) 
	{
		this.weight = weight;
	}

	@Override
	public String toString() 
	{
		return super.toString()+"\tWeight :"+this.weight;
	}


}
