package acsse.csc2a.model;

import acsse.csc2a.visitor.AbstractShapeVisitable;
import acsse.csc2a.visitor.AbstractShapeVisitor;

public class Triangle implements AbstractShapeVisitable
{

	
	private double base;
	private double hieght;
	
	
	
	/**
	 * @param base
	 * @param hieght
	 */
	public Triangle(double base, double hieght) {
		this.base = base;
		this.hieght = hieght;
	}



	/**
	 * @return the base
	 */
	public double getBase() {
		return base;
	}




	/**
	 * @return the hieght
	 */
	public double getHieght() {
		return hieght;
	}




	@Override
	public void accept(AbstractShapeVisitor v) {
		// TODO Auto-generated method stub
		v.visit(this);
	}

}
