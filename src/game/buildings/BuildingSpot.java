package game.buildings;

import game.shapes.Rect;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BuildingSpot {
    //variables
    private boolean buildingDone = false;
    private boolean flip;
    private double buildingX;
    private double buildingY;
    //UI stuff
    private Shape build; //little highlighted area

    public BuildingSpot(boolean flip, int buildingX, int buildingY,double usx,double usy) {
        this.flip = flip;
        this.buildingX = buildingX + usx;
        this.buildingY = buildingY + usy;
        if (!flip) {
            build = new Rect(buildingX -20, buildingY, 170, 75, Color.SADDLEBROWN);
        } else {
            build = new Rect(buildingX, buildingY, 170, 75, Color.SADDLEBROWN);
        }
    }

    public void spaceOpen(GraphicsContext gc){
        if(!buildingDone){
            build.draw(gc);
        }
    }
    public void used(){
        buildingDone = true;
    }
    public void unused(){
        buildingDone = false;
    }

    //getters
    public boolean isBuildingDone() {
        return buildingDone;
    }
    public boolean getFlip() {
        return flip;
    }
    public double getBuildingX() {
        return buildingX;
    }
    public double getBuildingY() {
        return buildingY;
    }
    public Shape getBuild() {
        return build;
    }
}
