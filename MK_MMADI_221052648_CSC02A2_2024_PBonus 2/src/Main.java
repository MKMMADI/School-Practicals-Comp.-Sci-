import java.util.ArrayList;
import java.util.List;

import acsse.csc2a.model.Circle;
import acsse.csc2a.model.Rectangle;
import acsse.csc2a.model.Triangle;
import acsse.csc2a.tester.PB2Tester;
import acsse.csc2a.visitor.AbstractShapeVisitable;
import acsse.csc2a.visitor.ShapeAreaVisitor;

public class Main {

	public static void main(String[] args) {
		 List<AbstractShapeVisitable> shapes = new ArrayList<>();
	        shapes.add(new Circle(5d));
	        shapes.add(new Rectangle(4, 6));
	        shapes.add(new Triangle(3, 4));

	        ShapeAreaVisitor areaVisitor = new ShapeAreaVisitor();
	        for (AbstractShapeVisitable shape : shapes) {
	            shape.accept(areaVisitor);
	        }
	    
	        PB2Tester.Test();
	}
}
