package game.ants;

import game.shapes.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Queen implements IAnt {

    public void drawAnt(double x, double y, GraphicsContext gc, int numberDrawn) {
        x += 84;
        y += 30;
        new Circle(x + 16, y + 2, 11, 13, Color.BLACK).draw(gc);
        new Circle(x + 0, y + 0, 8, Color.BLACK).draw(gc);
        new Circle(x - 30, y - 5, 32, 24, Color.BLACK).draw(gc);
    }
}
