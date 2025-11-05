package game;

import game.ants.Ant;
import game.ants.Queen;
import game.biomes.AntHill;
import game.buildings.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import game.shapes.*;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Owen Culver
 * Ant Simulator Games View File
 * notes for work:
 * add more usful comments, remove/move logic code into appropriate classes, also, fix the way buildings are recongise as built...
 */
public class AntGameView extends Application {
    // TODO: Instance Variables for View Components and Model
    //variables for start up/set up
    private GraphicsContext gc;
    private final double screenY = (Screen.getPrimary().getVisualBounds()).getHeight();
    private final double screenX = (Screen.getPrimary().getVisualBounds()).getWidth();
    private final double usw = 1440; //usable screens length/w
    private final double ush = 816; //usable screens height
    private final double usx = (screenX - usw)/2;//the amount of space between the black and usable background (x)
    private final double usy = (screenY - ush)/2;//the amount of space between the black and usable background (y)

    //variables for drawings and images and stuff like that.
    private ArrayList<Shape> background = new ArrayList<>();

    //main variables that NEED to be saved for sure (the main objects)
    private Nest nest;
    private Map map = new Map(usx,usy,usw,ush);
    /*building stuff*/
    private PathSpot pathSpot;
    private BuildingSpot spot;
    private boolean buildCancel = false;
    private double buildingX;
    private double buildingY;
    private boolean flip = false; //determines the direction of a new building.
    private final BuildingSpot build1 = new BuildingSpot(true ,550,225,usx,usy);
    private final BuildingSpot build2 = new BuildingSpot(false,760,225,usx,usy);
    private final BuildingSpot build3 = new BuildingSpot(true ,550,475,usx,usy);
    private final PathSpot build4 = new PathSpot(720,404,usx,usy);//for a path only
    private final BuildingSpot build5 = new BuildingSpot(true ,550,600,usx,usy);
    private final BuildingSpot build6 = new BuildingSpot(false,760,475,usx,usy);
    private final BuildingSpot build7 = new BuildingSpot(false,760,600,usx,usy);


    //start up ui features
    private TextField name;
    private Button confName;
    //UI features
    private Button nextDay;
    private Button layEggButton;
    private Button createAntButton;
    private Text population;
    private Text aphids;
    private Text food;
    private Text protein;
    private Text ants;
    private Text larvas;
    private Text eggs;
    private Text antsInUse;
    private Button mapButton;
    private String title = "";
    private String messege1 = "";
    private String messege2 = "";
    private String messege3 = "";

    /*UI for the build method*/
    private Button buildButton;
    private Button barracksButton;
    private Button pathsButton;
    private Text requirements1; //for paths and barracks
    private Button foodStorageButton;
    private Text requirements2; //for just food storage so far
    private Button proteinStorageButton;
    private Text requirements3; //just for protein storage so far
    private Button aphidFarmButton;
    private Text requirements4; //just for protein storage so far



    // stuff for the map (everything here except for the map button
    private boolean mapDrawn = false;
    private Spinner mapSelect;
    private Button mapSelectButton;
    private boolean selected = false;
    private Button cancelSelection;
    private int num = 0;//this is just for sending stuff and selecting stuff, and storing that number.
    private double mousex = 0;
    private double mousey = 0;



    //all stuff for prior to the game starting (for now)
    /// erasing the text for collecting the name
    public void nameErase(MouseEvent me){
        name.setText("");
    }

    /// start new game method, for when you are starting a brand-new game.
    public void startNewGame(ActionEvent e){
        confName.setDisable(true);
        nest = new Nest(name.getText());
        //starter buildings
        nest.addBuilding(new ThroneRoom(usx+550,usy+350));
        nest.getBuildings().get(0).addAnts(new Queen());
        nest.addBuilding(new Barracks(usx+760,usy+350,flip));
        nest.getBuildings().get(1).addAnts(new Ant());
        nest.getBuildings().get(1).addAnts(new Ant());
        nest.getBuildings().get(1).addAnts(new Ant());
        nest.getBuildings().get(1).addAnts(new Ant());
        nest.getBuildings().get(1).addAnts(new Ant());
        nest.calcAll();
        //the background (don't touch unless your improving graphics)
        background.add(new Rect(usx,usy,usw,ush,Color.rgb(135, 206, 235)));
        background.add(new Rect(usx,usy+200,usw,ush,Color.GREEN));
        background.add(new Rect(usx,usy+210,usw,ush,Color.rgb(61, 35, 13)));
        background.add(nest.Graphics(usx,usy).get(0));
        background.add(nest.Graphics(usx,usy).get(1));
        background.add(nest.Graphics(usx,usy).get(2));
        background.add(new Rect(usx,usy+780,usw,100,Color.DARKGRAY));
        background.add(new Circle(-20,-20,50,Color.YELLOW));
        background.add(new Rect(usx+5,usy+5,225,80,Color.WHITE));
        background.add(new RectS(usx+5,usy+5,225,80,Color.BLACK));
        background.add(new Texts(usw-usx - 95,usy + 264,"             5 Protein",Color.WHITE)); //don't touch this. (it's for egg laying)
        background.add(new Texts(usw-usx - 147,usy + 324,"1 Larva, 5 Protein, 5 Food",Color.WHITE)); // hatching larva
                //drawing background first
        reDraw();
        new Texts(usx + usw - 40, usy + ush - 43, "Day " + nest.getDays(), Color.WHITE).draw(gc); // this changes all everyday, so we don't add it to the background.
        /*drawing / updating everything else*/
        confName.relocate(screenX + screenX,screenY+screenY);
        name.relocate(screenX + screenX,screenY+screenY);
        buildButton.relocate(usx+1310,usy+10);
        mapButton.relocate(usx+1258,usy+10);
        nextDay.relocate(usx+1365,usy+10);
        aphids.relocate(usx + 15, usy+30);
        population.relocate(usx+15,usy+10);
        food.relocate(usx+15,usy+45);
        protein.relocate(usx+15,usy+60);
        ants.relocate(usx+125,usy+30);
        larvas.relocate(usx+125,usy+45);
        eggs.relocate(usx+125,usy+60);
        antsInUse.relocate(usx+125,usy+10);
        updateStats();
        showButtons();
    }

    /// redDraw method that draws the background, then the majority of the foreground.
    public void reDraw(){
        for(int i = 0; i < background.size(); i++){
            background.get(i).draw(gc);
        }
        for(int i = 0; i < nest.getBuildings().size(); i++){
            nest.getBuildings().get(i).draw(gc);
            for (int j = 0; j < nest.getBuildings().get(i).getAnts().size(); j++){
                nest.getBuildings().get(i).getAnts().get(j).drawAnt(
                        nest.getBuildings().get(i).getX() + usx,
                        nest.getBuildings().get(i).getY() + usy,
                        gc, j);
            }
        }
    }

    //building methods (take up most of the file honestly... def was a MUCH better way to do this.)
    /// this build method is what starts the process, call it again to end building.
    /// it searches through the different building spots to check if they are being used... this is inefficient...
    public void build(ActionEvent e){//starting building
        showButtons();
        mapSelectButton.relocate(-100,-100);
        mapSelect.relocate(-100,-100);
        mapButton.setText("Map");
        mapDrawn = false;
        cancelSelectionMethod(e);
        reDraw();
        if (!buildCancel) {
            // checking if a building is already built, and ONLY that.
            for(int i = 0; i < nest.getBuildings().size(); i++) {

                if (nest.getBuildings().get(i).getSpot().equals(build1)) {
                    build1.used();
                }
                if (nest.getBuildings().get(i).getSpot().equals(build2)) {
                    build2.used();
                }
                if (!build4.isBuildingDone()){//this is for a road path
                    if (nest.getBuildings().get(i).getPathSpot().equals(build4)) {
                        build4.used();
                        build3.unused();
                        build5.unused();
                        build6.unused();
                        build7.unused();
                    } else {
                    build3.used();
                    build5.used();
                    build6.used();
                    build7.used();
                    }
                }
                //this is where we display things that require a path to be built
                if (!build3.isBuildingDone()){ //only runs if build4 (a path) has been completed
                    if (nest.getBuildings().get(i).getX() == build3.getBuildingX() && nest.getBuildings().get(i).getY() == build3.getBuildingY()) {
                        build3.used();
                    }
                }
                if (!build5.isBuildingDone()){ //only runs if build4 (a path) has been completed
                    if (nest.getBuildings().get(i).getX() == build5.getBuildingX() && nest.getBuildings().get(i).getY() == build5.getBuildingY()) {
                        build5.used();
                    }
                }
                if (!build6.isBuildingDone()){ //only runs if build4 (a path) has been completed
                    if (nest.getBuildings().get(i).getX() == build6.getBuildingX() && nest.getBuildings().get(i).getY() == build6.getBuildingY()) {
                        build6.used();
                    }
                }
                if (!build7.isBuildingDone()){ //only runs if build4 (a path) has been completed
                    if (nest.getBuildings().get(i).getX() == build7.getBuildingX() && nest.getBuildings().get(i).getY() == build7.getBuildingY()) {
                        build7.used();
                    }
                }
            }
            //all the starting places you can build
            build1.spaceOpen(gc);
            build2.spaceOpen(gc);
            build4.spaceOpen(gc);
            //use space open plus for places that require a path to be built first
            spaceOpenPlus(build3);
            spaceOpenPlus(build5);
            spaceOpenPlus(build6);
            spaceOpenPlus(build7);

            //turning the build button into a cancel button
            buildButton.setText("Cancel");
            buildCancel = true;
        } else { //ending building using the new cancel button
            doneBuilding();
        }
    }

    /// This is for recording which building spot you are selecting to build on, and then displaying what you can build.
    public void theBuildSpace(BuildingSpot building){ //for other buildings (not paths)
        buildingX = building.getBuildingX();
        buildingY = building.getBuildingY();
        barracksButton.relocate(usx + 10, usy + 220);
        requirements1.setText("5 Ants not working, 10 Food");
        foodStorageButton.relocate(usx+10,usy + 280);
        requirements2.setText("5 Ants not working, 5 Food, 5 Protein");
        proteinStorageButton.relocate(usx+10,usy + 340);
        requirements3.setText("5 Ants not working, 5 Food, 5 Protein");
        aphidFarmButton.relocate(usx+10,usy+400);
        requirements4.setText("10 Ants not working, 10 protein");
        pathsButton.relocate(-100,-100);
        flip = building.getFlip();
        spot = building;
    }
    /// This is for recording which building spot you are selecting to build on, and then displaying what you can build.
    public void theBuildSpace(PathSpot building){ //for only paths
        buildingX = building.getBuildingX();
        buildingY = building.getBuildingY();
        barracksButton.relocate(-100,-100);
        pathsButton.relocate(usx + 10, usy + 220);
        requirements1.setText("5 Ants not working, 5 Food");
        foodStorageButton.relocate(-100,-100);
        requirements2.setText("");
        proteinStorageButton.relocate(-100, -100);
        requirements3.setText("");
        aphidFarmButton.relocate(-100,-100);
        requirements4.setText("");
        pathSpot = building;
    }

    /// these are all for assigning the buildHere buttons functions...
    public void buildHere1(MouseEvent me) {
        theBuildSpace(build1);
    }
    public void buildHere2(MouseEvent me) {
        theBuildSpace(build2);
    }
    public void buildHere3(MouseEvent me) {
        theBuildSpace(build3);
    }
    public void buildHere4(MouseEvent me){
        theBuildSpace(build4);
    }
    public void buildHere5(MouseEvent me){
        theBuildSpace(build5);
    }
    public void buildHere6(MouseEvent me){
        theBuildSpace(build6);
    }
    public void buildHere7(MouseEvent me){
        theBuildSpace(build7);
    }

    /// the function that runs when you select that you want to build a Barrack.
    public void buildABarrack(ActionEvent e){
        if (nest.AddAntsInUse(5)){
            if (nest.minusFood(10)) {
                nest.addBuilding(new Barracks(usx + buildingX, usx + buildingY, flip, spot));
                title = "Building Completed";
                messege1 = "You built a barrack";
                messege2 = "+10 max ants";
            } else {
                nest.minusAntsInUse(5);
                title = "Insufficient Resources";
                messege1 = "Not enough food";
            }
        } else {
            title = "Not Enough Ants";
            messege1 = "You don't have enough unused ants";
        }
        doneBuilding();
        food.setText("food: " + nest.getFood() + " / " + nest.getMaxFood());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
        ants.setText("ants: " + (nest.getAnts().size()-1) + " / " + (nest.getMaxAnts()-1));
    }
    /// the method for building a FoodStorage
    public void buildAFoodStorage(ActionEvent e){
        if (nest.AddAntsInUse(5)) {
            if (nest.minusFood(5)) {
                if (nest.minusProtein(5)) {
                    nest.addBuilding(new FoodStorage(buildingX, buildingY, flip, spot));
                    title = "Building Completed";
                    messege1 = "You built a food storage";
                    messege2 = "+25 max food";
                } else {
                    nest.addFood(5);
                    nest.minusAntsInUse(5);
                    title = "Insufficient Resources";
                    messege1 = "Not enough protein";
                }
            } else {
                nest.minusAntsInUse(5);
                title = "Insufficient Resources";
                messege1 = "Not enough food";
            }
        } else {
            title = "Not Enough Ants";
            messege1 = "You don't have enough unused ants";
        }
        doneBuilding();
        food.setText("food: " + nest.getFood() + " / " + nest.getMaxFood());
        protein.setText("protein: " + nest.getProtein() + " / " + nest.getMaxProtein());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
    }
    /// the method for building a protein storage.
    public void buildAProteinStorage(ActionEvent e){
        if (nest.AddAntsInUse(5)) {
            if (nest.minusFood(5)) {
                if (nest.minusProtein(5)) {
                    nest.addBuilding(new ProteinStorage(buildingX, buildingY, flip, spot));
                    title = "Building Completed";
                    messege1 = "You built a protein storage";
                    messege2 = "+25 max protein";
                } else {
                    nest.addFood(5);
                    nest.minusAntsInUse(5);
                    title = "Insufficient Resources";
                    messege1 = "Not enough protein";
                }
            } else {
                nest.minusAntsInUse(5);
                title = "Insufficient Resources";
                messege1 = "Not enough food";
            }
        } else {
            title = "Not Enough Ants";
            messege1 = "You don't have enough unused ants";
        }
        doneBuilding();
        food.setText("food: " + nest.getFood() + " / " + nest.getMaxFood());
        protein.setText("protein: " + nest.getProtein() + " / " + nest.getMaxProtein());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
    }
    /// the method for building an aphid farm.
    public void buildAAphidFarm(ActionEvent e){
        if (nest.AddAntsInUse(10)) {
            if (nest.minusProtein(10)) {
                nest.addBuilding(new AphidFarm(buildingX, buildingY, flip, spot));
                title = "Building Completed";
                messege1 = "You built an aphid farm";
                messege2 = "+5 aphid storage space";
            } else {
                nest.minusAntsInUse(10);
                title = "Insufficient Resources";
                messege1 = "Not enough protein";
            }
        } else {
            title = "Not Enough Ants";
            messege1 = "You don't have enough unused ants";
        }
        doneBuilding();
        aphids.setText("aphids: " + nest.getAphids() + " / "+nest.getMaxAphids());
        protein.setText("protein: " + nest.getProtein() + " / " + nest.getMaxProtein());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
    }
    /// the method for building a Path
    public void buildAPath(ActionEvent e){
        if (nest.AddAntsInUse(5)){
            if (nest.minusFood(5)) {
                nest.addBuilding(new Path(usx + buildingX, usy + buildingY, pathSpot));
                title = "Building Completed";
                messege1 = "You built a new path";
                messege2 = "new building spots available";
            } else {
                nest.minusAntsInUse(5);
                title = "Insufficient Resources";
                messege1 = "Not enough food";
            }
        } else {
            title = "Not Enough Ants";
            messege1 = "You don't have enough unused ants";
        }
        doneBuilding();
        food.setText("food: " + nest.getFood() + " / " + nest.getMaxFood());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
    }
    ///universal shut down for building
    public void doneBuilding(){
        background.add(nest.Graphics(usx,usy).get(0));
        background.add(nest.Graphics(usx,usy).get(1));
        background.add(nest.Graphics(usx,usy).get(2));
        reDraw();
        buildCancel = false;
        buildButton.setText("Build");
        barracksButton.relocate(-100,-100);
        pathsButton.relocate(-100,-100);
        requirements1.setText("");
        foodStorageButton.relocate(-100,-100);
        requirements2.setText("");
        proteinStorageButton.relocate(-100,usy -100);
        requirements3.setText("");
        aphidFarmButton.relocate(-100,-100);
        requirements4.setText("");
        build2.relocate();
        build1.relocate();
        build3.relocate();
        build4.relocate();
        build5.relocate();
        build7.relocate();
        build6.relocate();
        if (!Objects.equals(title, "")) {
            showTextBox();
        }
    }
    ///extra checker for checking if space open can run (used for when a path is needed before you can build).
    public void spaceOpenPlus(BuildingSpot building){
        if (!building.isBuildingDone()){
            building.spaceOpen(gc);
        }
    }













    ///next day function
    /// this takes care of the day counter, how much food is used up, eggs hatching, and dying.
    public void nextDayMethod(ActionEvent e){
        //removing the map buttons after returning to main screen, and returning buttons that should appear on the nest screen.
        mapSelectButton.relocate(-100,-100);
        mapSelect.relocate(-100,-100);
        mapButton.setText("Map");
        showButtons();
        //actual end day function
        int previousAnts = nest.getAnts().size();//recording how many game.ants we had
        int foodGenerated = nest.aphidFoodGeneration(); //gets how much food is generated by farms, AND adds that food to the nest.
        mapDrawn = false;
        cancelSelectionMethod(e);
        doneBuilding();
        nest.clearAntsInUse();
        /*calculating, how many game.ants should die (if any)*/
        int remainingFood = nest.getFood();
        if (!nest.minusFood(nest.getAnts().size())){
            nest.minusFood(nest.getFood());
            //checking for dead game.ants and a loss of the game.
            int deaths =  nest.getAnts().size() - remainingFood;
            if (!nest.minusAnts(deaths)){
                gc.setFill(Color.BLACK);
                gc.fillRect(usx,usy,usw,ush);
                //removing all remaining gui things
                nextDay.relocate(-100,-100);
                buildButton.relocate(-100,-100);
                mapButton.relocate(-100,-100);
                hideButtons();
                requirements1.relocate(usx + usw/2,usy + ush/2);
                requirements1.setFill(Color.WHITE);
                requirements1.setText("YOU LOSE");
            } else {
                reDraw();
            }
        }
        //checking for the dead larva
        if (!nest.getAnts().isEmpty()) { //basically checking that the nest still alive
            if (!nest.minusFood(nest.getLarva().size())) {
                remainingFood = nest.getFood();
                nest.minusFood(nest.getFood()); // removes all the food to feed what we could.
                int death = nest.getLarva().size() - remainingFood; //calculating total deaths
                nest.minusLarva(death); // actually getting rid of the larva handled elegantly by nest class
                reDraw(); //redraws for when larva graphics are included.
            }
        }
        //the next day method also handles whether an egg will hatch.
        messege2 = "Larva hatched: " + nest.nextDay();
        title = "DAY : " + (nest.getDays()-1) + " ~> " + nest.getDays();
        messege1 = "Total Ants Lost: " + (nest.getAnts().size() - previousAnts);
        if (nest.getAphids() > 0) {
            messege2 = "Food generated by farms: " + foodGenerated;
        }
        updateStats();
        showTextBox();
        new Texts(usx + usw - 40, usy + ush - 43, "Day " + nest.getDays(), Color.WHITE).draw(gc);
    }
    /// this updates all the states being displayed on the screen, to match the nests stats.
    public void updateStats(){
        population.setText("population: "+(nest.getPopulation()));
        aphids.setText("aphids: " + nest.getAphids() + " / "+nest.getMaxAphids());
        food.setText("food: "+nest.getFood()+" / "+nest.getMaxFood());
        protein.setText("protein: "+nest.getProtein()+" / "+nest.getMaxProtein());
        ants.setText("ants: "+(nest.getAnts().size()-1)+" / "+(nest.getMaxAnts()-1));
        larvas.setText("larva: "+(nest.getLarva().size())+" / "+nest.getMaxLarva());
        eggs.setText("eggs: "+(nest.getEggs().size())+" / "+nest.getMaxEggs());
        antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
    }















    //map function(s)
    ///opening and closing map button
    public void drawMap(ActionEvent e){
        if (!mapDrawn) { //opening map
            if (buildCancel) {
                build(e);
            }
            map.draw(gc);
            hideButtons();
            mapDrawn = true;
            mapButton.setText("Nest");
        } else { //closing map
            cancelSelectionMethod(e);
            reDraw();
            showButtons();
            mapSelectButton.relocate(-100,-100);
            mapSelect.relocate(-100,-100);
            mapDrawn = false;
            mapButton.setText("Map");
            new Texts(usx + usw - 40, usy + ush - 43, "Day " + nest.getDays(), Color.WHITE).draw(gc); // this changes all everyday, so we don't add it to the background.
        }
    }

    //map interactions (super large... unfortunately)
    /// this allows the enter button to control the spinner(the selector).
    public void sendings(KeyEvent e){
        if (e.getCode() == KeyCode.ENTER) {
            sending(null);
        }
    }
    /// the sending function, which is also first the selecting function for the map.
    /// selects a space on the map, then sends stuff to it after running this method again.
    /// It also handles all the interactions between the nest and map, and all prints concerning that... will move SOME
    /// of these into the map file.
    public void sending(ActionEvent e){ // the action can be null, so don't program anything here that would need a non-null event...
        map.draw(gc); // just ensuring that all graphics are correct... for if a bug occurred... (one was occurring, but putting this here fixed it)
        boolean complete = false; //tracts if the actual sending function is completed.
        int exploreNum = 5; //the minimum number of game.ants it takes to explore an area
        if (!selected) { //selecting something
            num = (Integer) mapSelect.getValue();
            map.biome(num).selected(gc);
            //displaying square
            new Rect((usx) + 235, usy + 5, 220, 80, Color.WHITE).draw(gc);
            new RectS((usx) + 235, usy + 5, 220, 80, Color.BLACK).draw(gc);
            new Texts((usx) + 244, usy + 24, map.biome(num).getType()).draw(gc);
            //collecting and displaying info
            if (!map.biome(num).getFound()){ //current statement for seeing if a biome is found yet.
                if (map.biome(num).isAdjacent()) {
                    new Rect((usx) + 235, usy + 5, 220, 80, Color.WHITE).draw(gc);
                    new RectS((usx) + 235, usy + 5, 220, 80, Color.BLACK).draw(gc);
                    new Texts((usx) + 244, usy + 24, "Area not yet Explored").draw(gc);
                    new Texts((usx) + 244, usy + 39, "Send " + exploreNum + " ants to explore this tile?").draw(gc);
                    mapSelect.relocate(-100, -100);
                    mapSelectButton.relocate(usx + 675,usy + 10);
                } else {
                    new Rect((usx) + 235, usy + 5, 220, 80, Color.WHITE).draw(gc);
                    new RectS((usx) + 235, usy + 5, 220, 80, Color.BLACK).draw(gc);
                    new Texts((usx) + 244, usy + 24, "Cannot Explore").draw(gc);
                    new Texts((usx) + 244, usy + 39, "Area not next to an explored tile.").draw(gc);
                    selected = true; // causes the whole interaction to reset early. (not go through with the sending)
                }

            // for spots that cannot be interacted with (whether that be changed later or not) (other than spots that contain nothing)
            } else if (map.biome(num) instanceof AntHill || map.biome(num).getContent().equals("Human, stay away from them...")
                    || map.biome(num).getContent().equals("nothing") || map.biome(num).getAmount() == 0) {
                new Texts((usx) + 244, usy + 39, map.biome(num).getContent()).draw(gc);
                if (map.biome(num).getAmount() == 0){
                    new Texts((usx) + 244, usy + 54, "Empty").draw(gc);
                }
                selected = true;
            } else {//other spots typical display for their content.
                mapSelectButton.relocate(usx + 775,usy + 10);
                mapSelect.relocate(usx + 600, usy + 10);
                new Texts((usx) + 244, usy + 39, map.biome(num).getContent() + " :  " + map.biome(num).getAmount()).draw(gc);
                if (map.biome(num).getContent().equals("aphids")){
                    new Texts((usx) + 244, usy + 54, "items per ant :  " + 1).draw(gc);
                } else {
                    new Texts((usx) + 244, usy + 54, "items per ant :  " + map.biome(num).getAntMultiplier()).draw(gc);
                }
                // extra info for when a bug is on the space
                if (map.biome(num).getIsBug()){
                    new Texts((usx) + 244, usy + 69, "Total Bug Strength :  " + map.biome(num).getStrength()).draw(gc);
                }
            }
            //noinspection unchecked
            mapSelect.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000000));
            //noinspection unchecked
            mapSelect.getValueFactory().setValue(null); // resetting the spinner

            //end of selecting function
            if (!selected) {
                new Rect(usx + 5, usy + 5, 225, 80, Color.WHITE).draw(gc);
                new RectS(usx + 5, usy + 5, 225, 80, Color.BLACK).draw(gc);
                mapSelectButton.setText("Send");
                selected = true;
                if (map.biome(num).isAdjacent() && !map.biome(num).getFound()) {
                    cancelSelection.relocate(usx + 725, usy + 10);
                } else {
                    cancelSelection.relocate(usx + 835, usy + 10);
                }
            } else {
                selected = false;
            }
            //start of sending function
        } else {
            //actual function for interaction //(could prolly be stored somewhere else other than in this file)... willllll I change it? prolly not.
            title = "";
            messege1 = "";
            messege2 = "";
            messege3 = "";
            if (!map.biome(num).getFound()) { //for when the interaction is with an un-found/undiscovered biome/area
                if (nest.AddAntsInUse(exploreNum)) { //for when it is adjacent.
                    title = "Area Explored";
                    messege1 = "Explored a " + map.biome(num).getType() + ".";
                    map.biome(num).setFound(true);
                    map.draw(gc);
                } else { // the only other thing possible here now is if the player send the 'exploreNum' amount of game.ants.
                    title = "Cannot Send";
                    messege1 = "You don't have enough unused ants";
                }
                //end of un-found area interaction


            } else if (mapSelect.getValue() != null && nest.AddAntsInUse((Integer) mapSelect.getValue())) {
                if (map.biome(num).getAmount() == 0) { //for when it is an empty space or has been used up
                    nest.minusAntsInUse((Integer) mapSelect.getValue());
                    title = "Empty Space";
                    messege1 = "Don't send ants here!";
                    //end of empty space interaction

                    //this next if statement is for ALL battling bugs.
                } else if (map.biome(num).getIsBug()) {
                    if ((Integer) mapSelect.getValue() > 0) {
                        int antsLost = (map.biome(num).battle((Integer) mapSelect.getValue()));
                        nest.minusAnts(antsLost);
                        if (antsLost == (Integer) mapSelect.getValue()) {
                            title = "Battle Lost";
                            messege1 = "Your sent ants died";
                        } else {
                            title = "Battle Won!";
                            messege1 = "Space changed into protein";
                            map.draw(gc);
                        }
                        messege2 = "Total deaths :  " + antsLost;
                    } else {
                        title = "No ants sent";
                        messege1 = "You sent no ants";
                    }
                    //end of bug interaction

                    //this statement is for seeds
                } else if (map.biome(num).getContent().equals("seeds")) {
                    for (int i = 0; i <= map.biome(num).getAntMultiplier(); i++) {
                        if (map.biome(num).getAmount() >= ((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) {
                            map.biome(num).subtractAmount((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            int foodLeft = nest.addFood((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            int proteinLeft = nest.addProtein((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            title = "Ants Sent :  " + mapSelect.getValue();
                            messege1 = "You collected " + ((((int) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) - foodLeft) + " food";
                            messege2 = "You collected " + ((((int) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) - proteinLeft) + " protein";
                            if (foodLeft > 0 && proteinLeft > 0) {
                                if (foodLeft < proteinLeft) {
                                    map.biome(num).addAmount(foodLeft);
                                    messege3 = foodLeft + " seeds left behind. (storage is full)";
                                } else {
                                    map.biome(num).addAmount(proteinLeft);
                                    messege3 = proteinLeft + " seeds left behind. (storage is full)";
                                }

                            }
                            complete = true;
                            break;
                        }
                    }
                    if (!complete) {
                        nest.minusAntsInUse((Integer) mapSelect.getValue());
                        title = "To Many Ants!";
                        messege1 = "You tried sending more ants then";
                        messege2 = "needed";
                    }
                    //end of seeds interactions

                    //this next statement is for food
                } else if (map.biome(num).getContent().equals("mushrooms") || map.biome(num).getContent().equals("crumbs") || map.biome(num).getContent().equals("nectar")) {
                    for (int i = 0; i <= map.biome(num).getAntMultiplier(); i++) {
                        if (map.biome(num).getAmount() >= ((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) {
                            map.biome(num).subtractAmount((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            int foodLeft = nest.addFood((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            title = "Ants Sent :  " + mapSelect.getValue();
                            messege1 = "You collected " + ((((int) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) - foodLeft) + " food";
                            if (foodLeft > 0) {
                                map.biome(num).addAmount(foodLeft);
                                messege2 = foodLeft + " food left behind. (storage is full)";
                            }
                            complete = true;
                            break;
                        }
                    }
                    if (!complete) {
                        nest.minusAntsInUse((Integer) mapSelect.getValue());
                        title = "To Many Ants!";
                        messege1 = "You tried sending more ants then";
                        messege2 = "needed";
                    }
                    //end of food interactions

                    //start of protein interaction
                } else if (map.biome(num).getContent().equals("protein") || map.biome(num).getContent().equals("pollen")) {
                    for (int i = 0; i <= map.biome(num).getAntMultiplier(); i++) {
                        if (map.biome(num).getAmount() >= ((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) {
                            map.biome(num).subtractAmount((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            int proteinLeft = nest.addProtein((Integer) mapSelect.getValue() * map.biome(num).getAntMultiplier() - i);
                            title = "Ants Sent :  " + mapSelect.getValue();
                            messege1 = "You collected " + ((((int) mapSelect.getValue() * map.biome(num).getAntMultiplier()) - i) - proteinLeft) + " protein";
                            if (proteinLeft > 0) {
                                map.biome(num).addAmount(proteinLeft);
                                messege2 = proteinLeft + " protein left behind. (storage is full)";
                            }
                            complete = true;
                            break;
                        }
                    }
                    if (!complete) {
                        nest.minusAntsInUse((Integer) mapSelect.getValue());
                        title = "To Many Ants!";
                        messege1 = "You tried sending more ants then";
                        messege2 = "needed";
                    }
                    //end of protein interaction

                    //start of aphid interaction
                } else if (map.biome(num).getContent().equals("aphids")) {
                    if (map.biome(num).getAmount() >= ((Integer) mapSelect.getValue())) {
                    map.biome(num).subtractAmount((Integer) mapSelect.getValue());
                    int aphidsLeft = nest.addAphids((Integer) mapSelect.getValue());
                    title = "Ants Sent :  " + mapSelect.getValue();
                    messege1 = "You collected " + ((int) mapSelect.getValue() - aphidsLeft) + " aphids";
                    if (aphidsLeft > 0) {
                        map.biome(num).addAmount(aphidsLeft);
                        messege2 = aphidsLeft + " protein left behind. (storage is full)";
                    }
                    complete = true;
                }
                if (!complete) {
                    nest.minusAntsInUse((Integer) mapSelect.getValue());
                    title = "To Many Ants!";
                    messege1 = "You tried sending more ants then";
                    messege2 = "needed";
                }
                    //end of aphids interaction
                } else { // end of interactions, catching if a 'bug' occurs and returning your game.ants (not using them up)
                        nest.minusAntsInUse((Integer) mapSelect.getValue());
                        title = "Error";
                        messege1 = "'Bug' occurred, all ants returned";
                }
            } else { //nothing could be done, as you don't have enough game.ants
                title = "Cannot Send";
                messege1 = "You don't have enough unused ants";
            }

            //end of sending function (drawing an updated version of everything)
            mapSelect.relocate(-100, -100);
            mapSelectButton.relocate(-100,-100);
            cancelSelection.relocate(-100,-100);
            selected = false;
            aphids.setText("aphids: " + nest.getAphids()+" / "+nest.getMaxAphids());
            food.setText("food: " + nest.getFood() + " / " + nest.getMaxFood());
            protein.setText("protein: " + nest.getProtein() + " / " + nest.getMaxProtein());
            antsInUse.setText("available ants: " + ((nest.getAnts().size() - 1 ) - (nest.getAntsInUse())) + " / " + (nest.getAnts().size() - 1));
            ants.setText("ants: "+(nest.getAnts().size()-1)+" / "+(nest.getMaxAnts()-1));
            population.setText("population: "+(nest.getPopulation()));
            map.draw(gc);
            showTextBox();
            //noinspection unchecked
            mapSelect.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, map.biomes.size()-1));
            //noinspection unchecked
            mapSelect.getValueFactory().setValue(null); //resetting the spinner
        }
    }

    /// detects when the mouse first goes down.
    public void mousePressed(MouseEvent me){
        mousex = me.getX();
        mousey = me.getY();
        cancelSelectionMethod(null);
    }

    /// cancels the selecting that you've made on the map.
    public void cancelSelectionMethod(ActionEvent e){
        mapSelectButton.setText("Select");
        selected = false;
        if (mapDrawn) {
            map.draw(gc);
        }
        //returning buttons to normal
        mapSelectButton.relocate(-100,-100);
        mapSelect.relocate(-100,-100);
        cancelSelection.relocate(-100,-100);
    }

    /// detects when the mouse pointer has been pressed, and records that
    public void mouseMoving(MouseEvent me) {
        //canceling your selection
        mapSelectButton.setText("Select");
        selected = false;
        if (mapDrawn) {
            map.draw(gc);
        }
        cancelSelection.relocate(-100, -100);

        ///making the map move using the mouse... does work right YET
        if (mapDrawn) {

            if (mousex > me.getX()) {
                map.right(gc, (int) (mousex - me.getX()));
                mousex = me.getX();
                map.draw(gc);
            } else if (mousex < me.getX()) {
                map.left(gc, (int) (me.getX() - mousex));
                mousex = me.getX();
                map.draw(gc);
            }
            if (mousey < me.getY()) {
                map.up(gc, (int) (me.getY() - mousey));
                mousey = me.getY();
                map.draw(gc);
            } else if (mousey > me.getY()) {
                map.down(gc, (int) (mousey - me.getY()));
                mousey = me.getY();
                map.draw(gc);
            }

        }
    }

    /**
     *  this method allows us to select biome/area tiles by clicking them
     * @param me (mouseEvent)
     */
    public void selecting(MouseEvent me){
        mapSelect.requestFocus();
        if (mapDrawn) {
            int select = map.selected(me.getX(), me.getY());
            if (select != -1) {
                mapSelect.getValueFactory().setValue(select);
                cancelSelectionMethod(null);
                sending(null);
            }
        }
    }










    ///adding game. ants and eggs code and stuff, and what not. graphics for these things will be located in the game.ants.Ants, or game.Eggs file.
    public void layingEgg(ActionEvent e) {
        if (nest.getProtein() >= 5) {
            nest.minusProtein(5);
            if (!nest.addEgg()){
                title = "Insufficient Egg Space";
                messege1 = "You've hit your maximum amount";
                messege2 = "of eggs";
            } else { //this is when success happens
                title = "New Egg!";
                messege1 = "Your Queen laid an egg";
                messege2 = "Thank her";
            }
        } else {
            title = "Insufficient Resources";
            messege1 = "Not enough protein";
        }
        showTextBox();
        updateStats();
    }

    public void hatchingLarva(ActionEvent e) {
        if (!nest.getLarva().isEmpty()) {
            //nest.delete a larva at some point after this
            if (nest.getFood() >= 5) {
                nest.minusFood(5);
                if (nest.getProtein() >= 5) {
                    nest.minusProtein(5);
                    if (!nest.addAnt()) {
                        title = "Insufficient Ant Space";
                        messege1 = "You've hit your maximum amount";
                        messege2 = "of Ants";
                        nest.addFood(5);
                        nest.addProtein(5);
                    } else { //this is when success happens
                        //find a way to put this bellow calculation into a dif file, but right now, if I don't do it this way, the game.ants graphics won't appear for some reason...
                        for (int i = 0; i < nest.getBuildings().size(); i++) {
                            if (nest.getBuildings().get(i).getAnts().size() < nest.getBuildings().get(i).getMaxAnts()) {
                                nest.getBuildings().get(i).addAnts(new Ant());
                            }
                        }
                        title = "New Ant";
                        messege1 = "A larva as turned into an ant";
                        messege2 = "+ 1 ant";
                        nest.minusLarva();
                        reDraw();
                    }
                } else {
                    title = "Insufficient Resources";
                    messege1 = "You need at least 5 protein";
                    nest.addFood(5);
                }
            } else {
                title = "Insufficient Resources";
                messege1 = "You need at least 5 food";
            }
        } else {
            title = "Insufficient Resources";
            messege1 = "You have no larva";
            messege2 = "Ants come from larva";
        }
        showTextBox();
        updateStats();
    }










    /// hides the buttons that don't already hide.
    private void hideButtons(){
        layEggButton.relocate(-100,-100);
        createAntButton.relocate(-100,-100);
    }
    /// shows the buttons that hide because of the last method.
    private void showButtons(){
        layEggButton.relocate(usw-usx -70,usy + 220);
        createAntButton.relocate(usw-usx -93,usy + 280);
    }
    /// this shows the text box, for updates to what happened. 
    private void showTextBox(){
        new Rect((usx)+235,usy+5,220,80,Color.WHITE).draw(gc);
        new RectS((usx)+235,usy+5,220,80,Color.BLACK).draw(gc);
        new Texts((usx)+244,usy+24,title).draw(gc);
        new Texts((usx)+244,usy+39, messege1).draw(gc);
        new Texts((usx)+244,usy+54, messege2).draw(gc);
        new Texts((usx)+244,usy+69, messege3).draw(gc);
        title = "";
        messege1 = "";
        messege2 = "";
        messege3 = "";
    }
    /**
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, screenX, screenY); // set the size here
        stage.setTitle("The Ant Hill"); // set the window title here
        stage.setResizable(false);
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        Canvas canvas = new Canvas(screenX, screenY);
        gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(usx,usy, screenX, screenY); //this just gives you the original black screen that we will turn into a menu screen


        // 2. Create the GUI components
        name = new TextField("Nest Name");
        confName = new Button("confirm");
        nextDay = new Button("End Day");
        createAntButton = new Button("Hatch Larva");
        layEggButton = new Button("Lay Egg");

        /*map stuffs*/
        mapButton = new Button("Map");
        mapSelect = new Spinner(0,(map.biomes.size())-1,0);
        //noinspection unchecked
        mapSelect.getValueFactory().setValue(null); // ensuring there is no value in it.
        mapSelect.setEditable(true);
        mapSelectButton = new Button("Select");
        cancelSelection = new Button("Cancel");

        /*building buttons*/
        buildButton = new Button("Build");
        barracksButton = new Button("Barrack");
        pathsButton = new Button("Path");
        population = new Text(-100,-100,"");
        aphids = new Text (-100,-100,"");
        food = new Text(-100,-100,"");
        protein = new Text(-100,-100,"");
        larvas= new Text();
        eggs = new Text();
        ants = new Text();
        antsInUse = new Text();
        requirements1 = new Text();
        foodStorageButton = new Button("food storage");
        requirements2 = new Text();
        proteinStorageButton = new Button("protein storage");
        requirements3 = new Text();
        aphidFarmButton = new Button("aphid farm");
        requirements4 = new Text();

        // 3. Add components to the root
        root.getChildren().addAll(canvas,aphids,food,population,protein,buildButton,nextDay,name,confName,ants,eggs,larvas,
                barracksButton,build1.getBuildButton(), build2.getBuildButton(), build3.getBuildButton(),mapButton,
                build4.getBuildButton(), pathsButton, build5.getBuildButton(), build6.getBuildButton(),
                build7.getBuildButton(), requirements1, antsInUse, foodStorageButton, requirements2, mapSelect,
                mapSelectButton, cancelSelection,createAntButton,layEggButton,requirements3,proteinStorageButton,
                aphidFarmButton, requirements4);

        // 4. Configure the components (this is now done within the startGameMethod
        nextDay.relocate(-100,-100);
        hideButtons();
        name.relocate(usx+(usw/2)-50,usy+(ush/2));
        confName.relocate(usx+(usw/2),usy+(ush/2)+50);

        /*map stuff*/
        mapButton.relocate(-100,-100);
        mapSelect.relocate(-100,-100);
        mapSelectButton.relocate(-100,-100);
        cancelSelection.relocate(-100,-100);

        /*build buttons*/
        buildButton.relocate(-100,-100);
        barracksButton.relocate(-100,-100);
        pathsButton.relocate(-100,-100);
        requirements1.relocate(usx + 10,usy + 250);
        requirements1.setFill(Color.WHITE);
        foodStorageButton.relocate(-100,-100);
        requirements2.relocate(usx+10,usy+310);
        requirements2.setFill(Color.WHITE);
        proteinStorageButton.relocate(-100,-100);
        requirements3.relocate(usx+10,usy+370);
        requirements3.setFill(Color.WHITE);
        aphidFarmButton.relocate(-100,-100);
        requirements4.relocate(usx+10, usy+430);
        requirements4.setFill(Color.WHITE);

        /*build spots*/
        build2.relocate();
        build1.relocate();
        build3.relocate();
        build4.relocate();
        build5.relocate();
        build7.relocate();
        build6.relocate();

        // 5. Add Event Handlers and do final setup
        nextDay.setOnAction(this::nextDayMethod);
        confName.setOnAction(this::startNewGame);
        name.addEventHandler(MouseEvent.MOUSE_PRESSED, this::nameErase);
        mapButton.setOnAction(this::drawMap);
        mapSelectButton.setOnAction(this::sending);
        cancelSelection.setOnAction(this::cancelSelectionMethod);
        layEggButton.setOnAction(this::layingEgg);
        createAntButton.setOnAction(this::hatchingLarva);
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mousePressed);
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseMoving);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this::selecting);
        buildButton.setOnAction(this::build);
        barracksButton.setOnAction(this::buildABarrack);
        pathsButton.setOnAction(this::buildAPath);
        foodStorageButton.setOnAction(this::buildAFoodStorage);
        proteinStorageButton.setOnAction(this::buildAProteinStorage);
        aphidFarmButton.setOnAction(this::buildAAphidFarm);
        mapSelect.addEventHandler(KeyEvent.KEY_PRESSED, this::sendings);
        build1.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere1);
        build2.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere2);
        build3.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere3);
        build4.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere4);
        build5.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere5);
        build7.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere7);
        build6.getBuildButton().addEventHandler(MouseEvent.MOUSE_RELEASED, this::buildHere6);

        // 7. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}

