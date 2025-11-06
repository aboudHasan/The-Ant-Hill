package game.ants;

import game.shapes.Circle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ant implements IAnt {

    public void drawAnt(double x, double y, GraphicsContext gc, int numberDrawn) {
        if (numberDrawn < 3) {
            x += 33 + numberDrawn * 35;
            y += 15;
        } else if (numberDrawn < 7) {
            x += 20 + (numberDrawn - 3) * 35;
            y += 35;
        } else {
            x += 33 + (numberDrawn - 7) * 35;
            y += 50;
        }
        new Circle(x - 6, y + 0.3, 5, 7, Color.BLACK).draw(gc);
        new Circle(x - 3, y + 3, 2, 2, Color.BLACK).draw(gc);
        new Circle(x + 0, y + 0, 4, Color.BLACK).draw(gc);
        new Circle(x + 8, y - 1, 12, 10, Color.BLACK).draw(gc);
        new Circle(x - 3, y + 3, 5, 2, Color.BLACK).draw(gc);
    }
}
