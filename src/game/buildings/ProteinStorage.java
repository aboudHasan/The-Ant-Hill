package game.buildings;

import game.shapes.Circle;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ProteinStorage extends Building {

    public ProteinStorage(double x, double y, boolean flip, BuildingSpot spot) {
        super(x, y, flip, 25.00, spot);
        if (flip) {
            graphics.add(new Circle(x, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x + 25, y + 55, 5, Color.RED));
            graphics.add(new Circle(x + 15, y + 50, 3, Color.RED));
            graphics.add(new Circle(x + 110, y + 13, 3, Color.RED));
            graphics.add(new Circle(x + 120, y + 18, 4, Color.RED));
            graphics.add(new Circle(x + 131, y + 18, 3, Color.RED));
            graphics.add(new Circle(x + 120, y + 58, 3, Color.RED));
        } else {
            graphics.add(new Circle(x - 25, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x + 25, y + 55, 5, Color.RED));
            graphics.add(new Circle(x + 15, y + 50, 3, Color.RED));
            graphics.add(new Circle(x + 110, y + 13, 3, Color.RED));
            graphics.add(new Circle(x + 120, y + 18, 4, Color.RED));
            graphics.add(new Circle(x + 131, y + 18, 3, Color.RED));
            graphics.add(new Circle(x + 120, y + 58, 3, Color.RED));
        }
    }
}
