
//the picnic spot will also every once in a while have a random human appear on it, which will prevent you from using the space.
public class Picnic extends Biomes{

    public Picnic(double usx,double usy,double x,double y){
        //array of contents
        //"crumbs" /*food*/, "beetle", "human";
        //amounts (food = 50), (beetle = 5) human... doesn't matter, you just cant use this space.
        //its ant multiplier is 3, meaning each regular ant can take 3 items from this grassy patch.
        super(usx, usy, x, y, 3);
    }
}
