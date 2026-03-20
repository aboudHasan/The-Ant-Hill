package game.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Texts extends Shape {

    private Color color;
    private double x;
    private double y;
    private String text;
    private int size = 14;

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

    public Texts(double x, double y, String text, int size){
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = Color.BLACK;
        this.size = size;
    }

    public Texts(double x, double y, String text, Color color, int size){
        super();
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.setFont(Font.font(size));
        gc.fillText(text, x, y);

    }
}
