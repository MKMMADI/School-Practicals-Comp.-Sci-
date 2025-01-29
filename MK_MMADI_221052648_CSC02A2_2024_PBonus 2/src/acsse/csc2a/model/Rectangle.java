package acsse.csc2a.model;

import acsse.csc2a.visitor.AbstractShapeVisitable;
import acsse.csc2a.visitor.AbstractShapeVisitor;

public class Rectangle implements AbstractShapeVisitable 
{
	
	private double width;
	private double hieght;
	
	
	/**
	 * @param width
	 * @param hieght
	 */
	public Rectangle(double width, double hieght) {
		this.width = width;
		this.hieght = hieght;
	}


	/**
	 * @return the width
	 */
	public double getWidth() {
		return width;
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
