package acsse.csc2a.fmb.gui;

import java.util.ArrayList;

import acsse.csc2a.fmb.model.Firework;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FireworkDisplayCanvas extends Canvas 
{
	ArrayList<Firework> Fireworks = new ArrayList<>();
	
	public FireworkDisplayCanvas()
	{
		super(750,750);
	}
	
	public void redrawConvas() 
	{
		
        double width = getWidth();
        double height = getHeight();
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        int rows = 15;
        int columns = 15;
        double cellWidth = width / columns;
        double cellHeight = height / rows;

        // Draw grid lines
        gc.setStroke(Color.BLACK);
        for (int row = 0; row < rows; row++) {
            double y = row * cellHeight;
            gc.strokeLine(0, y, width, y);
        }
        for (int col = 0; col < columns; col++) {
            double x = col * cellWidth;
            gc.strokeLine(x, 0, x, height);
        }
        
        
        
	}
}
