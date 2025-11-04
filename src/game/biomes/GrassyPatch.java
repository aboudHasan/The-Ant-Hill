package game.biomes;

public class GrassyPatch extends Biomes {

    public GrassyPatch(double usx,double usy,double x,double y){
        //array of contents
        //"mushroom" /*food*/, "nothing", "nothing", "beetle", "grasshopper", "grasshopper" /* aphids eventually */;
        //amounts (food = 10), (beetle = 2) (grasshopper = 4)
        //its ant multiplier is 4, meaning each regular ant can take 4 items from this grassy patch.
        super(usx, usy, x, y, "item",4);
    }
}
