package acsse.csc2a.visitor;

import acsse.csc2a.model.Circle;
import acsse.csc2a.model.Rectangle;
import acsse.csc2a.model.Triangle;
public class ShapeAreaVisitor implements AbstractShapeVisitor
{

	@Override
	public void visit(Circle c) {
		// TODO Auto-generated method stub
	
		double area = Math.PI*Math.pow(c.getRaduis(),2);
		String Area = String.valueOf(area);
		if(Area.contains(","))
		{
			Area.replace(",", ".");
		}
		
		System.out.println("Circle Area: "+Area);
	}

	@Override
	public void visit(Rectangle c) {
		// TODO Auto-generated method stub

		double area =c.getHieght()*c.getWidth();
		System.out.println("Rectangle Area: "+area);
	}

	@Override
	public void visit(Triangle c) {
		// TODO Auto-generated method stub
		double area = 0.5*c.getBase()*c.getHieght();
		System.out.println("Triangle Area: "+area);
	}

	

}
