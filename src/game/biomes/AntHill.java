package game.biomes;

import game.shapes.Circle;
import game.shapes.Rect;
import javafx.scene.paint.Color;

public class AntHill extends Biomes {

    public AntHill(double usx, double usy, double x, double y) {
        super(usx, usy, x, y);
        this.found = true;
        content = "Your ants live here";
    }

    @Override
    public void loadGraphics() {
        drawables.clear();
        drawables.add(new Circle(usx - 100 + x, usy - 100 + y, 100, Color.GREEN));
        drawables.add(new Circle(usx + (usw / 2) - 25 + x, usy + (ush / 2) - 25 + y, 25, Color.rgb(61, 35, 13)));
        drawables.add(new Rect(usx + (usw / 2) - 25 + x, usy + (ush / 2) + y, 50, 26, Color.GREEN));

//        drawables.add(new Texts(usx + (usw / 2) - 5 + x, usy + (ush / 2) - 80 + y, thisNumber, Color.BLACK));

        super.loadGraphics();
    }
}
