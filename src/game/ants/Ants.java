package game.ants;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import game.shapes.Circle;

public class Ants {


    public Ants() {

    }

    public void drawAnt(double x, double y, GraphicsContext gc, int numberDrawn){
        if (this instanceof Ant) {
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
        } else if (this instanceof Queen) { //this is else if, since we will eventually make more kinds of ants...
            x += 84;
            y += 30;
            new Circle(x + 16, y + 2, 11, 13, Color.BLACK).draw(gc);
            new Circle(x + 0, y + 0, 8, Color.BLACK).draw(gc);
            new Circle(x - 30, y - 5, 32, 24, Color.BLACK).draw(gc);
        }
    }
}
