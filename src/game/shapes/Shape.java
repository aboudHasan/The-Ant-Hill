package game.shapes;

import game.IDrawable;
import javafx.scene.canvas.GraphicsContext;

public abstract class Shape implements IDrawable {

    public Shape(){}

    @Override
    public void draw(GraphicsContext gc) {}
}
