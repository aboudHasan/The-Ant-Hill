import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;


//this class is for grouping buildings. (using extends)
public class Building {
    //storage related stats
    private int maxAnts = 0;
    private int population = 0; // general population including all living things in Nest
    private int maxFood = 0;
    private int food = 0;
    private int maxProtein = 0;
    private int protein = 0;
    private int maxEggs = 0;
    private int maxLarva = 0;
    private ArrayList<Ants> ants = new ArrayList<Ants>();; //all the ants in the building.
    private ArrayList<Eggs> eggs = new ArrayList<Eggs>();; //all the eggs in the building.
    private ArrayList<Larva> larva = new ArrayList<Larva>();; //all the larva in the building.
    private ArrayList<Shape> graphics = new ArrayList<Shape>();
    private double x = 0;
    private double y = 0;
    private BuildingSpot spot = new BuildingSpot(false,0,0,0,0);
    private PathSpot pathSpot = new PathSpot(0,0,0,0);

    //Throne room
    public Building(int maxFood, int maxEggs, int maxLarva, int maxProtein, double x, double y){
        this.maxAnts = 1;
        calcPopulation();
        this.maxFood = maxFood;
        this.maxProtein = maxProtein;
        this.maxEggs = maxEggs;
        this.maxLarva = maxLarva;
        this.protein = 0;
        this.food = 25;
        this.x = x;
        this.y = y;
        //below is where you create its graphics
        graphics.add(new Circle(x,y,150,75, Color.rgb(186, 155, 74)));
        graphics.add(new Rect(x+125,y+34,60,20,Color.rgb(186, 155, 74)));
        graphics.add(new Circle(x+25,y+55,5,Color.RED));
        graphics.add(new Circle(x+15,y+50,3,Color.RED));
        graphics.add(new Circle(x+110,y+13,3,Color.RED));
        graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
        graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
        graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
    }
    //starting barrack
    public Building(int maxAnts, double x, double y,boolean flip){
        this.maxAnts = maxAnts;
        calcPopulation();
        if (flip == false) {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x - 25, y + 34, 60, 20, Color.rgb(186, 155, 74)));
        } else {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x+125,y+34,60,20,Color.rgb(186, 155, 74)));
        }
        this.x = x;
        this.y = y;
    }
    //barracks
    public Building(int maxAnts, double x, double y,boolean flip, BuildingSpot spot){
        this.maxAnts = maxAnts;
        this.spot = spot;
        calcPopulation();
        if (flip == false) {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x - 25, y + 34, 60, 20, Color.rgb(186, 155, 74)));
        } else {
            graphics.add(new Circle(x, y, 150, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Rect(x+125,y+34,60,20,Color.rgb(186, 155, 74)));
        }
        this.x = x;
        this.y = y;
    }
    //paths
    public Building(double x,double y, PathSpot spot){
        this.x = x;
        this.y = y;
        this.pathSpot = spot;
        graphics.add(new Rect(x,y,20,250,Color.rgb(186, 155, 74)));
    }
    //food storage
    public Building(double x, double y, boolean flip, int maxStuff, BuildingSpot spot){
        this.x = x;
        this.y = y;
        this.spot = spot;
        this.maxFood = maxStuff;
        if (flip == true){
            graphics.add(new Circle(x, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.BISQUE));
            graphics.add(new Circle(x+15,y+50,3,Color.BISQUE));
            graphics.add(new Circle(x+110,y+13,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
            graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
        } else {
            graphics.add(new Circle(x-25, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.BISQUE));
            graphics.add(new Circle(x+15,y+50,3,Color.BISQUE));
            graphics.add(new Circle(x+110,y+13,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+18,4,Color.BISQUE));
            graphics.add(new Circle(x+131,y+18,3,Color.BISQUE));
            graphics.add(new Circle(x+120,y+58,3,Color.BISQUE));
        }
    }
    //protein storage
    public Building(double x, double y, boolean flip, double maxStuff, BuildingSpot spot){
        this.x = x;
        this.y = y;
        this.spot = spot;
        this.maxProtein = (int)maxStuff;
        if (flip){
            graphics.add(new Circle(x, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.RED));
            graphics.add(new Circle(x+15,y+50,3,Color.RED));
            graphics.add(new Circle(x+110,y+13,3,Color.RED));
            graphics.add(new Circle(x+120,y+18,4,Color.RED));
            graphics.add(new Circle(x+131,y+18,3,Color.RED));
            graphics.add(new Circle(x+120,y+58,3,Color.RED));
        } else {
            graphics.add(new Circle(x-25, y, 175, 75, Color.rgb(186, 155, 74)));
            graphics.add(new Circle(x+25,y+55,5,Color.RED));
            graphics.add(new Circle(x+15,y+50,3,Color.RED));
            graphics.add(new Circle(x+110,y+13,3,Color.RED));
            graphics.add(new Circle(x+120,y+18,4,Color.RED));
            graphics.add(new Circle(x+131,y+18,3,Color.RED));
            graphics.add(new Circle(x+120,y+58,3,Color.RED));
        }
    }



    //getters
    public ArrayList<Ants> getAnts(){
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
    public ArrayList<Shape> getGraphics() {
        return graphics;
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


    //setters/adders/subtractors
    public void addAnts(Ants ant){
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
        this.population = ants.size() + eggs.size() + larva.size();
    }
    public void addProtein(int x){
        this.protein += x;
    }
    public void addFood(int x){
        this.food += x;
    }
    public void minusFood(double num){
        this.food -= (int) num;
    }
    public void minusProtein(double num){
        this.protein -= (int) num;
    }



    //actual methods
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
                ", graphics=" + graphics +
                '}';
    }
}
