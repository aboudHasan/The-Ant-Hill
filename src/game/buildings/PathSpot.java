package game.buildings;

import game.shapes.Rect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import game.shapes.Shape;

public class PathSpot {
    private boolean buildingDone = false;
    private int buildingX;
    private int buildingY;
    private double usx;
    private double usy;
    private Shape build; //little highlighted area

    public PathSpot(int buildingX, int buildingY, double usx, double usy) {
        this.buildingX = buildingX;
        this.buildingY = buildingY;
        this.usx = usx;
        this.usy = usy;
        build = new Rect(buildingX,buildingY-1,20,250,Color.SADDLEBROWN);
    }

    public void spaceOpen(GraphicsContext gc){
        if(!buildingDone){
            build.draw(gc);
        }
    }

    public void used(){
        buildingDone = true;
    }

    //getters
    public boolean isBuildingDone() {
        return buildingDone;
    }
    public int getBuildingX() {
        return buildingX;
    }
    public int getBuildingY() {
        return buildingY;
    }
    public double getUsx() {
        return usx;
    }
    public double getUsy() {
        return usy;
    }
    public Shape getBuild() {
        return new Rect(buildingX,buildingY-1,20,250,Color.SANDYBROWN);
    }
}
