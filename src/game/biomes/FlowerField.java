package game.biomes;

import game.shapes.Circle;
import javafx.scene.paint.Color;

import static game.biomes.BiomeType.*;

public class FlowerField extends Biomes {

    private static final BiomeType[] TYPE_CHANCES = new BiomeType[]{
            NECTAR, POLLEN, SEEDS, APHIDS
    };

    private final BiomeType type;

    //this specific biome resets EVERY season, unlike the rest. (least that's the plan)
    public FlowerField(double usx, double usy, double x, double y) {
        // Colour was set to red but never used, so removed it
        super(usx, usy, x, y, 4);

        //selecting what it contains
        type = TYPE_CHANCES[rand.nextInt(TYPE_CHANCES.length)];
        this.content = type.name().toLowerCase();

        //selecting how many or how much it contains
        switch (type) {
            case NECTAR -> this.amount = rand.nextInt(10, 35);
            case POLLEN -> this.amount = rand.nextInt(3, 10);
            case SEEDS -> this.amount = rand.nextInt(1, 15);
            case APHIDS -> this.amount = rand.nextInt(1, 4);
        }

        drawables.add(new Circle(usx + (x) - 100, usy + (y) - 100, 100, Color.GREEN));
    }

    @Override
    public void loadGraphics() {
        drawables.clear();
        drawables.add(new Circle(usx + (x) - 100, usy + (y) - 100, 100, Color.GREEN));
        drawables.add(new Circle(usx + x - 35, usy + y + 35, 10, Color.PINK));
        drawables.add(new Circle(usx + x + 25, usy + y + 40, 10, Color.YELLOW));
        drawables.add(new Circle(usx + x - 10, usy + y - 10, 10, Color.MISTYROSE));
        if (amount > 0) {
            switch (type) {
                case POLLEN -> {
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + -19, usy + y - 39, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 7, usy + y - 8 - 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 10, usy + y - 8 - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 - 40, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 + 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 15, usy + y - 8 + 56, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 5, usy + y - 8 + 35, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 29, usy + y - 8 + 45, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 11, usy + y - 8 + 25, 5, 3, Color.BEIGE));
                    x += 60;
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + -19, usy + y - 39, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 7, usy + y - 8 - 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 10, usy + y - 8 - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 - 40, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 + 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 15, usy + y - 8 + 56, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 5, usy + y - 8 + 35, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 29, usy + y - 8 + 45, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 11, usy + y - 8 + 25, 5, 3, Color.BEIGE));
                    x -= 110;
                    y -= 30;
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + -19, usy + y + 59, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 7, usy + y - 8 - 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 10, usy + y - 8 - 25, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 + 80, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 - 15, usy + y - 8 + 20, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 15, usy + y - 8 + 56, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 5, usy + y - 8 + 35, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 29, usy + y - 8 + 45, 5, 3, Color.BEIGE));
                    drawables.add(new Circle(usx + x - 6 + 11, usy + y - 8 + 25, 5, 3, Color.BEIGE));
                    x += 50;
                    y += 30;
                }
                case NECTAR -> {
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 9, Color.DARKGOLDENROD));
                    drawables.add(new Circle(usx + x - 50, usy + y - 37, 5, 9, Color.DARKGOLDENROD));
                    drawables.add(new Circle(usx + x + 30, usy + y - 56, 5, 9, Color.DARKGOLDENROD));
                    drawables.add(new Circle(usx + x - 50, usy + y + 37, 5, 9, Color.DARKGOLDENROD));
                    drawables.add(new Circle(usx + x + 37, usy + y + 25, 5, 9, Color.DARKGOLDENROD));
                }
                case SEEDS -> {
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 9, Color.BROWN));
                    drawables.add(new Circle(usx + x + 35, usy + y + 30, 5, 9, Color.BROWN));
                    drawables.add(new Circle(usx + x + 43, usy + y - 47, 5, 9, Color.BROWN));
                }
                case APHIDS -> {
                    drawables.add(new Circle(usx + x - 25, usy + y - 25, 5, 10, Color.YELLOWGREEN));
                    drawables.add(new Circle(usx + x + 35, usy + y + 30, 5, 10, Color.YELLOWGREEN));
                    drawables.add(new Circle(usx + x + 43, usy + y - 47, 5, 10, Color.YELLOWGREEN));
                }
            }
        }
        super.loadGraphics();
    }
}
