package game.shapes;

import game.IDrawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Images implements IDrawable {

    private final double x, y;
    private final Image image;

    public Images(double x, double y, String fileName) {
        this.x = x;
        this.y = y;
        image = new Image(fileName, 225, 225, true, true); // scale this thing...
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
}
