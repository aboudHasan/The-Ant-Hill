package game.shapes;

import game.IDrawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape implements IDrawable {

    public Shape(){}

    @Override
    public void draw(GraphicsContext gc) {}
}
