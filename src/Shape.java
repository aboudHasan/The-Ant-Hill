import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Shape {
    private Color color;
    private double x;
    private double y;
    private double w;
    private double h;
    private double r = -1111111; // i cant remember why but don't touch, it works.
    private boolean stroke = false;
    private String oval = "not oval"; //this also gets reused for the text making thing.
    private boolean texts = false;
    private Image image;
    private static Image clouds = new Image("clouds.png", 225, 225, true, true); // scale this thing...

    //image
    public Shape(double x, double y, String name){
        if (name.equals("clouds")) {
            image = clouds;
        }
        this.x = x;
        this.y = y;
    }
    //square
    public Shape(double x, double y, double w, double h, Color color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    //circle
    public Shape(double x, double y, double radius, Color color) {
        this.x = x;
        this.color = color;
        this.y = y;
        this.r = radius;
    }
    public Shape(double x, double y, double w, double h, Color color, boolean s) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.stroke = s;
    }
    //Oval
    public Shape(double x, double y, double w, double h, Color color, String oval){
        this.color = color;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.oval = oval;
    }
    //Text
    public Shape(double x, double y, String text,Color color){
        this.texts = true;
        this.oval = text;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void draw(GraphicsContext gc) {
        if (this.image != null) {
            gc.drawImage(image, x, y);
        } else if (oval.equals("oval")) {
            gc.setFill(color);
            gc.fillOval(x, y, w, h);
        }else if (texts){
            gc.setFill(color);
            gc.fillText(oval,x,y);
        } else if (stroke) {
            gc.setStroke(color);
            gc.strokeRect(x,y,w,h);
        } else if (r == -1111111) {
            gc.setFill(color);
            gc.fillRect(x, y, w, h);
        } else {
            gc.setFill(color);
            gc.fillOval(x,y,r*2,r*2);
        }
    }
}
