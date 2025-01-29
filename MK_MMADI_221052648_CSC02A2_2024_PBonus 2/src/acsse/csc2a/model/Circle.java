package acsse.csc2a.model;

import acsse.csc2a.visitor.AbstractShapeVisitable;
import acsse.csc2a.visitor.AbstractShapeVisitor;

public class Circle implements AbstractShapeVisitable 
{

	private double raduis;
	
	
	/**
	 * @param raduis
	 */
	public Circle(double raduis) {
		this.raduis = raduis;
	}


	/**
	 * @return the raduis
	 */
	public double getRaduis() {
		return raduis;
	}



	@Override
	public void accept(AbstractShapeVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
