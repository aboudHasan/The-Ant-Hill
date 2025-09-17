import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

//this class is to contain a shorthand for drawing rectangles
public class Rect extends Shape{
    private Color color;
    private double x;
    private double y;
    private double w;
    private double h;

    public Rect(double x, double y, double width, double height, Color color) {
        super(x,y,width,height,color);
        this.h = height;
        this.y = y;
        this.w = width;
        this.x = x;
        this.color = color;
    }
}
