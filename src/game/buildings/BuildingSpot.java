package game.buildings;

import game.shapes.Rect;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

//will eventually be what handles the buildings!
public class BuildingSpot {
    //variables
    private boolean buildingDone = false;
    private boolean flip;
    private double buildingX;
    private double buildingY;
    //UI stuff
    private Shape build; //little highlighted area
    private Button buildButton;

    public BuildingSpot(boolean flip, int buildingX, int buildingY,double usx,double usy) {
        this.flip = flip;
        this.buildingX = buildingX + usx;
        this.buildingY = buildingY + usy;
        if (!flip) {
            build = new Rect(buildingX -20, buildingY, 170, 75, Color.SADDLEBROWN);
        } else {
            build = new Rect(buildingX, buildingY, 170, 75, Color.SADDLEBROWN);
        }
        buildButton = new Button("build here");
    }

    public void spaceOpen(GraphicsContext gc){
        if(!buildingDone){
            build.draw(gc);
            if (!flip) {
                buildButton.relocate(buildingX + 25, buildingY + 25);
            } else {
                buildButton.relocate(buildingX + 50, buildingY + 25);
            }
        }
    }
    public void used(){
        buildingDone = true;
    }
    public void unused(){
        buildingDone = false;
    }

    public void relocate(){
        buildButton.relocate(-100,-100);
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
    public Button getBuildButton() {
        return buildButton;
    }

}
