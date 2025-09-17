import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class PathSpot {
    private boolean buildingDone = false;
    private int buildingX;
    private int buildingY;
    private double usx;
    private double usy;
    //UI stuff
    private Shape build; //little highlighted area
    private Button buildButton;

    public PathSpot(int buildingX, int buildingY, double usx, double usy) {
        this.buildingX = buildingX;
        this.buildingY = buildingY;
        this.usx = usx;
        this.usy = usy;
        build = new Rect(buildingX,buildingY,20,250,Color.SADDLEBROWN);
        buildButton = new Button("build here");
    }

    public void spaceOpen(GraphicsContext gc){
        if(!buildingDone){
            build.draw(gc);
            buildButton.relocate(this.usx + buildingX + -25, this.usy + buildingY + 100);
        }
    }

    public void used(){
        buildingDone = true;
    }

    public void relocate(){
        buildButton.relocate(-100,-100);
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
        return build;
    }
    public Button getBuildButton() {
        return buildButton;
    }

}
