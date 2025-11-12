package game.buildings;

import game.Eggs;
import game.Larva;
import game.ants.IAnt;
import game.shapes.Circle;
import game.shapes.Rect;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import game.shapes.Shape;

import java.util.ArrayList;


//this class is for grouping buildings. (using extends)
public class Building {
    //storage related stats
    protected int aphids = 0;
    protected int maxAphids = 0;
    protected int maxAnts = 0;
    protected int population = 0; // general population including all living things in Nest
    protected int maxFood = 0;
    protected int food = 0;
    protected int maxProtein = 0;
    protected int protein = 0;
    protected int maxEggs = 0;
    protected int maxLarva = 0;
    private ArrayList<IAnt> ants = new ArrayList<IAnt>();; //all the ants in the building.
    private ArrayList<Eggs> eggs = new ArrayList<Eggs>();; //all the eggs in the building.
    private ArrayList<Larva> larva = new ArrayList<Larva>();; //all the larva in the building.
    protected double x = 0;
    protected double y = 0;
    private BuildingSpot spot = new BuildingSpot(false,0,0,0,0);
    private PathSpot pathSpot = new PathSpot(0,0,0,0);
    protected ArrayList<Shape> graphics = new ArrayList<Shape>();

    //starting barrack
    public Building(double x, double y){
        this.x = x;
        this.y = y;
    }
    //paths
    public Building(double x,double y, PathSpot spot){
        this.x = x;
        this.y = y;
        this.pathSpot = spot;
    }
    //other buildings
    public Building(double x, double y, BuildingSpot spot){
        this.spot = spot;
        this.x = x;
        this.y = y;
    }


    //getters
    public ArrayList<IAnt> getAnts(){
        return ants;
    }
    public ArrayList<Eggs> getEggs(){
        return eggs;
    }
    public ArrayList<Larva> getLarva(){
        return larva;
    }
    public int getPopulation(){
        return population;
    }
    public int getMaxAnts(){
        return maxAnts;
    }
    public int getMaxFood(){
        return maxFood;
    }
    public int getMaxProtein(){
        return maxProtein;
    }
    public int getMaxEggs(){
        return maxEggs;
    }
    public int getProtein(){
        return protein;
    }
    public int getMaxLarva(){
        return maxLarva;
    }
    public int getFood(){
        return food;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public BuildingSpot getSpot() {
        return spot;
    }
    public PathSpot getPathSpot(){
        return this.pathSpot;
    }
    public int getMaxAphids(){return this.maxAphids;}
    public int getAphids(){return this.aphids;}
    public ArrayList<Shape> getGraphics() {
        return graphics;
    }


    //setters/adders/subtractors
    public void addAnts(IAnt ant){
        this.ants.add(ant);
        calcPopulation();
    }
    public void deleteAnt(int num){
        for (int i = num; i > 0; i--) {
            try {
                this.ants.removeFirst();
            } catch (Exception ignored) {
                //this will just result in an end game, and cannot run anyway, but just in case somehow.
            }
        }
        calcPopulation();
    }
    public void addEggs(Eggs egg){
        this.eggs.add(egg);
        calcPopulation();
    }
    public void deleteEgg(int num){
        for (int i=num;i>0;i--) {
            this.eggs.removeFirst();
        }
        calcPopulation();
    }
    public void addLarva(Larva larva){
        this.larva.add(larva);
        calcPopulation();
    }
    public void deleteLarva(int num){
        for (int i=num;i>0;i--) {
            this.larva.removeFirst();
        }
        calcPopulation();
    }
    public void calcPopulation(){
        this.population = ants.size() + larva.size();
    }
    public void addProtein(int x){
        this.protein += x;
    }
    public void addFood(int x){
        this.food += x;
    }
    public void addAphids(int x){this.aphids += x;}
    public void minusFood(double num){
        this.food -= (int) num;
    }
    public void minusProtein(double num){
        this.protein -= (int) num;
    }
    public void minusAphids(int num){this.aphids -= (int) num;}

    public void draw(GraphicsContext gc){
        for (int i = 0; i < graphics.size(); i++) {
            graphics.get(i).draw(gc);
        }
    }

    @Override
    public String toString() {
        return "Building{" +
                "maxAnts=" + maxAnts +
                ", numOfAnts=" + population +
                ", maxFood=" + maxFood +
                ", food=" + food +
                ", maxProtein=" + maxProtein +
                ", protein=" + protein +
                ", maxEggs=" + maxEggs +
                ", maxLarva=" + maxLarva +
                ", ants=" + ants +
                ", eggs=" + eggs +
                ", larva=" + larva +
                '}';
    }
}
