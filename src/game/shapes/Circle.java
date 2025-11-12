package game.shapes;

import game.IDrawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    private Color color;
    private double x;
    private double y;
    private double r = 0;
    private double w = 0;
    private double h = 0;

    public Circle(double x, double y, double radius, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.r = radius;
        this.color = color;
    }
    public Circle(double x, double y, double width, double height, Color color) {
        super();
        this.x = x;
        this.y = y;
        this.color = color;
        this.h = height;
        this.w = width;
    }

    public void draw(GraphicsContext gc) {
        if (this.r==0) {
            gc.setFill(color);
            gc.fillOval(x, y, w, h);
        } else {
            gc.setFill(color);
            gc.fillOval(x, y, r * 2, r * 2);
        }
    }
}
