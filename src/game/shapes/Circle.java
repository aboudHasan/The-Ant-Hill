package game.shapes;

import javafx.scene.paint.Color;

public class Circle extends Shape {

    public Circle(double x, double y, double radius, Color color) {
        super(x,y,radius,color);
    }
    public Circle(double x, double y, double width, double height, Color color) {
        super(x,y,width,height,color,"oval");
    }
}
