package game.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle extends Shape {

    private Color color;
    private double x;
    private double y;
    private double r = 0;
    private String oval = "not oval"; //this also gets reused for the text making thing.
    private double w;
    private double h;

    public Circle(double x, double y, double radius, Color color) {
        super(x,y,radius,color);
        this.x = x;
        this.y = y;
        this.r = radius;
        this.color = color;
    }
    public Circle(double x, double y, double width, double height, Color color) {
        super(x,y,width,height,color,"oval");
        this.x = x;
        this.y = y;
        this.color = color;
        this.oval = oval;
        this.h = height;
        this.w = width;
    }

    public void draw(GraphicsContext gc) {
        if (oval.equals("oval")) {
            gc.setFill(color);
            gc.fillOval(x, y, w, h);
        } else {
            gc.setFill(color);
            gc.fillOval(x, y, r * 2, r * 2);
        }
    }
}
