package acsse.csc2a.supermarket.model;

public abstract class Food 
{
	
	protected String barcode ;// an 8 character string.
	protected String quality;
	
	
	/**
	 * @param barcode
	 * @param quality
	 */
	public Food(String barcode, String quality) 
	{
		this.barcode = barcode;
		this.quality = quality;
	}
	/**
	 * @return the barcode
	 */
	public String getBarcode() 
	{
		return barcode;
	}
	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) 
	{
		this.barcode = barcode;
	}
	/**
	 * @return the quality
	 */
	public String getQuality() 
	{
		return quality;
	}
	/**
	 * @param quality the quality to set
	 */
	public void setQuality(String quality) 
	{
		this.quality = quality;
	}
	
 	@Override
 	public String toString() {
 		// TODO Auto-generated method stub
 		return "Barcode : " + this.barcode+"\t"+"Quality : "+this.quality;
 	}
	
}
