package game.buildings;

import game.shapes.Circle;
import game.shapes.Rect;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Barracks extends Building{

    //starting barrack
    public Barracks(double x, double y,boolean flip){
        super(x,y);
        maxAnts = 10;
        setGraphics(flip);
        calcPopulation();
    }

    //other barracks
    public Barracks(double x, double y,boolean flip, BuildingSpot spot){
        super(x,y,spot);
        maxAnts = 10;
        setGraphics(flip);
        calcPopulation();
    }

    public void setGraphics(boolean flip){
        if (flip == false) {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x - 25, y + 34, 60, 20, Color.rgb(186, 155, 74)));
        } else {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x+125,y+34,60,20,Color.rgb(186, 155, 74)));
        }
    }
}
