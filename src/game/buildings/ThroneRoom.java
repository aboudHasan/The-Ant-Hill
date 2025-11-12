package game.buildings;

import game.shapes.Circle;
import game.shapes.Rect;
import game.shapes.Shape;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ThroneRoom extends Building {

    public ThroneRoom(double x, double y){
        super(25,5,5,25,x,y);
        //below is where you create its graphics
        graphics.add(new Circle(x,y,150,75, Color.rgb(186, 155, 74)));
        graphics.add(new Rect(x+125,y+34,60,20,Color.rgb(186, 155, 74)));
        graphics.add(new Circle(x+25,y+55,5,Color.RED));
        graphics.add(new Circle(x+15,y+50,3,Color.RED));
        graphics.add(new Circle(x+110,y+13,3,Color.RED));
        graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
        graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
        graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
    }
}
