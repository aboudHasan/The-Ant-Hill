package game.buildings;

import game.shapes.Rect;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Path extends Building {

    public Path(double x, double y, PathSpot spot){
        super(x,y,spot);
        graphics.add(new Rect(x,y,20,250, Color.rgb(186, 155, 74)));
    }
}
