package game.buildings;

import game.shapes.Circle;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class FoodStorage extends Building {

    public FoodStorage(double x, double y, boolean flip, BuildingSpot spot){
        super(x,y,spot);
        this.maxFood = 25;
        if (flip == true){
            graphics.add(new Circle(x, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.BISQUE));
            graphics.add(new Circle(x+15,y+50,3,Color.BISQUE));
            graphics.add(new Circle(x+110,y+13,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
            graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
        } else {
            graphics.add(new Circle(x-25, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.BISQUE));
            graphics.add(new Circle(x+15,y+50,3,Color.BISQUE));
            graphics.add(new Circle(x+110,y+13,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
            graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
        }
    }
}
