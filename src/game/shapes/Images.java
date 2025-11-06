package game.shapes;

import game.IDrawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;

public class Images implements IDrawable {

    private static final HashMap<String, Image> imageCache = new HashMap<>();

    private final double x, y;
    private final Image image;

    public Images(double x, double y, String fileName) {
        this.x = x;
        this.y = y;

        if (imageCache.containsKey(fileName)) {
            image = imageCache.get(fileName);
        } else {
            Image img = new Image(fileName, 225, 225, true, true);
            imageCache.put(fileName, img);
            image = img;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(image, x, y);
    }
}
