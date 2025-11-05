package game.buildings;

public class Barracks extends Building{

    public Barracks(double x, double y,boolean flip){
        super(10,x,y,flip);
    }
    public Barracks(double x, double y,boolean flip, BuildingSpot spot){
        super(10,x,y,flip,spot);
    }
}
