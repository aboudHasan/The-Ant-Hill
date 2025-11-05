package game.biomes;

import javafx.scene.paint.Color;

public class FlowerField extends Biomes {
    //this specific biome resets EVERY season, unlike the rest. (least that's the plan)
    public FlowerField(double usx, double usy, double x, double y) {
        //will also contain aphids eventually
        super(usx, usy, x, y, 4, Color.RED);
    }
}
