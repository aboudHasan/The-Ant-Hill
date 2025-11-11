package game.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Texts extends Shape {

    private Color color;
    private double x;
    private double y;
    private String text;

    public Texts(double x, double y, int text, Color color){
        super();
        this.text = String.valueOf(text);
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public Texts(double x, double y, String text, Color color){
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public Texts(double x, double y, String text){
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillText(text, x, y);
    }
}
