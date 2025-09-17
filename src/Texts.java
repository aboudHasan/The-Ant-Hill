import javafx.scene.paint.Color;
public class Texts extends Shape{
    public Texts(double x, double y, int text, Color color){
        super(x,y, String.valueOf(text),color);

    }
    public Texts(double x, double y, String text, Color color){
        super(x,y, text,color);
    }
    public Texts(double x, double y, String text){
        super(x,y, text,Color.BLACK);
    }
}
