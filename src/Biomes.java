import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Biomes {

    //variable to fix 'select' button
    private boolean nest = false;
    //universal variables (location stuff and some graphics related things) (also the randomizer)
    private Random rand = new Random();
    private double usx;
    private double usy;
    private double usw;
    private double ush;
    private double x;
    private double y;
    private boolean found = false;
    private boolean adjacent = false;
    private final int thisNumber; // records which number was printed on it
    static int number;// this counts how many biomes there are so far in the list, so that it prints that number onto the current biome ( while its being generated or printed)... just don't touch it.
    //graphics
    ArrayList<Shape> graphics = new ArrayList<Shape>();
    // specific variables;
    private String content = "nothing";
    private double amount = 0;
    private int antMultiplier = 0;
    private Boolean isBug = false;
    private int strength = 0;

    // ant hill
    public Biomes(double usx,double usy,double x,double y){
        this.found = true;
        this.nest = true;
        this.x = x;
        this.y = y;
        this.usx = usx;
        this.usy = usy;
        thisNumber = number; //setting its number/ID
        number = number + 1;
        content = "Your ants live here";
    }
    // Picnic
    public Biomes(double usx,double usy,double x,double y, int antMultiplier){
        this.x = x;
        this.y = y;
        this.antMultiplier = antMultiplier;
        //selecting what it contains
        String[] contents = {"crumbs" /*food*/, "human", "beetles","protein"};
        int selector = rand.nextInt(0,4);
        this.content = contents[selector];
        //selecting how much it contains... except if its human... then the spot just doesn't work.
        if (this.content.equals("crumbs")){
            selector = rand.nextInt(25,50);
            this.amount = selector;
        } else if (this.content.equals("protein")){
            selector = rand.nextInt(15,35);
            this.amount = selector;
        }else if (this.content.equals("beetles")){
            selector = rand.nextInt(1,5);
            this.amount = selector;
            this.isBug = true;
            this.strength = 4 * selector;
        } else {
            content = "Human, stay away from them...";
        }
        //where it is
        this.usx = usx;
        this.usy = usy;
        thisNumber = number;//setting its number/ID
        number = number + 1;
    }
    // grassy patch
    public Biomes(double usx,double usy,double x,double y, String content, int antMultiplier){
        this.x = x;
        this.y = y;
        this.antMultiplier = antMultiplier;
        //selecting what it contains
        String[] contents = {"mushrooms" /*food*/, "nothing", "nothing", "beetles", "grasshoppers", "grasshoppers" /* aphids eventually */};
        int selector = rand.nextInt(0,6);
        this.content = contents[selector];
        //selecting how many or how much it contains
        if (this.content.equals("mushrooms")){
            selector = rand.nextInt(1,11);
            this.amount = selector;
        } else if(this.content.equals("beetles")){
            selector = rand.nextInt(1,3);
            this.amount = selector;
            this.isBug = true;
            this.strength = 4 * selector;
        } else if(this.content.equals("grasshoppers")) {
            selector = rand.nextInt(1,5);
            this.amount = selector;
            this.isBug = true;
            this.strength = 2 * selector;
        }

        //where it is
        this.usx = usx;
        this.usy = usy;
        graphics.add(new Circle( usx+(x)-100, usy+(y)-100,100, Color.GREEN));
        thisNumber = number;// saving the biomes number/ID
        number = number + 1;
    }
    // Flower Field
    //will eventually have multiple colours of flowers.
    public Biomes(double usx, double usy, double x, double y, int antMultiplier, Color colour){
        this.x = x;
        this.y = y;
        this.antMultiplier = antMultiplier;
        //selecting what it contains
        String[] contents = {"nectar"/*food*/, "pollen"/*protein*/,"seeds"/*food, but heavy */ /*aphids eventually*/};
        int selector = rand.nextInt(0,3);
        this.content = contents[selector];
        //selecting how many or how much it contains
        if (this.content.equals("nectar")){
            selector = rand.nextInt(10,35);
            this.amount = selector;
        } else if(this.content.equals("pollen")){
            selector = rand.nextInt(3,10);
            this.amount = selector;
        } else if(this.content.equals("seeds")) {
            selector = rand.nextInt(1,15);
            this.amount = selector;
        }

        //where it is
        this.usx = usx;
        this.usy = usy;
        graphics.add(new Circle( usx+(x)-100, usy+(y)-100,100, Color.GREEN));
        thisNumber = number;// saving the biomes number/ID
        number = number + 1;
    }

    //drawing everything method (cycles through stuff)
    public void draw(GraphicsContext gc){
        loadGraphics(); //ensuring that what we print is the most up to date thing.
        for (int i = 0; i < graphics.size(); i++){
            graphics.get(i).draw(gc);
        }
    }


    //selected method
    public void selected(GraphicsContext gc){
        new Circle(usx + (x) - 103, usy + (y) - 103, 103, Color.WHITE).draw(gc);
        draw(gc);
    }

    //graphics
    public void loadGraphics(){
        graphics.clear();
        if(this instanceof AntHill){
            graphics.add(new Circle( usx-100 + x, usy-100 + y,100, Color.GREEN));
            graphics.add(new Circle( usx+(usw/2)-25 + x, usy+(ush/2)-25 + y,25, Color.rgb(61, 35, 13)));
            graphics.add(new Rect(usx+(usw/2)-25 + x, usy+(ush/2) + y,50, 26, Color.GREEN));
            graphics.add(new Texts(usx+(usw/2)-5 + x, usy+(ush/2) - 80 + y, thisNumber, Color.BLACK));
        } else if (this instanceof GrassyPatch){
            graphics.add(new Circle( usx+(x)-100, usy+(y)-100,100, Color.GREEN));
            if (this.content.equals("mushrooms") && this.amount != 0){
                graphics.add(new Circle(usx+x-6,usy+y-8,17,10,Color.WHITE));
                graphics.add(new Rect(usx+x,usy+y,5,10,Color.WHITE));
                graphics.add(new Circle(usx+x-6+10,usy+y-8-20,17,10,Color.WHITE));
                graphics.add(new Rect(usx+x+10,usy+y-20,5,10,Color.WHITE));
                graphics.add(new Circle(usx+x-6-18,usy+y-8-20,17,10,Color.WHITE));
                graphics.add(new Rect(usx+x-18,usy+y-20,5,10,Color.WHITE));
            } else if (this.content.equals("beetles")){
                graphics.add(new Circle(usx+x-25,usy+y-5,20,10,Color.BLACK));
                graphics.add(new Circle(usx+x-30,usy+y-4,18,6,Color.GREEN));
                graphics.add(new Circle(usx+x-6,usy+y-8,30,20,Color.BLACK));
                graphics.add(new Rect(usx+x+10,usy+y+11, 2, 9, Color.BLACK));
            } else if (this.content.equals("grasshoppers")){
                graphics.add(new Circle(usx+x,usy+y,20,10,Color.BLACK));
            } else if (this.content.equals("protein") && this.amount != 0) {
                graphics.add(new Circle(usx + x - 35, usy + y + 35, 10, Color.RED));
                graphics.add(new Circle(usx + x + 25, usy + y + 40, 10, Color.RED));
                graphics.add(new Circle(usx + x - 10, usy + y - 10, 10, Color.RED));
            }
        } else if (this instanceof Picnic){
            graphics.add(new Circle( usx+(x)-100, usy+(y)-100,100, Color.GREEN));
            int yCord = 50;
            for (int i = 4; i <= 8; i++){
                graphics.add(new Rect(usx+(x)-55, usy+(y)-yCord, 90, 15, Color.rgb(250,250,250)));
                yCord -= 15;
                //this next loop is for the red squares.
                int xCord = 55;
                if (i % 2 == 0) {
                    xCord -= 15;
                }
                for (int j = 0; j <= 2; j++){
                    graphics.add(new RectS(usx+(x)-xCord, usy+(y)-yCord - 15, 15, 15, Color.BLACK));
                    graphics.add(new Rect(usx+(x)-xCord, usy+(y)-yCord - 15, 15, 15, Color.rgb(250,5,10)));
                    xCord -= 30;
                }
            }
            if (this.content.equals("crumbs")){
                graphics.add(new Circle(usx+x-6,usy+y-8,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6+10,usy+y-8-20,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6-18,usy+y-8-20,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6-40,usy+y-8-20,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6-5,usy+y-8+20,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6-20,usy+y-8+56,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6+5,usy+y-8+35,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6+29,usy+y-8+45,17,10,Color.BEIGE));
                graphics.add(new Circle(usx+x-6+11,usy+y-8+25,17,10,Color.BEIGE));
            } else if (this.content.equals("beetles")){
                graphics.add(new Circle(usx+x-25,usy+y-5,20,10,Color.BLACK));
                graphics.add(new Circle(usx+x-30,usy+y-4,18,6,Color.GREEN));
                graphics.add(new Circle(usx+x-6,usy+y-8,30,20,Color.BLACK));
                graphics.add(new Rect(usx+x+10,usy+y+11, 2, 9, Color.BLACK));
            } else if (this.content.equals("Human, stay away from them...")){

            } else if (this.content.equals("protein") && this.amount != 0) {
                graphics.add(new Circle(usx + x - 35, usy + y + 35, 10, Color.RED));
                graphics.add(new Circle(usx + x + 25, usy + y + 40, 10, Color.RED));
                graphics.add(new Circle(usx + x - 10, usy + y - 10, 10, Color.RED));
            }
        }
        //for the clouds when a place isn't discovered yet.
        if(!this.found) {
            if(this.isAdjacent()){
                graphics.add(new Images(usx - 110 + x, usy - 100 + y, "clouds_transparent"));
            } else {
                graphics.add(new Images(usx - 110 + x, usy - 100 + y, "clouds"));
            }
        }
        //printing the numbers, but not for the nest, which will handle it by itself, because otherwise it doesn't print right...
        if (!this.nest) {
            graphics.add(new Texts(usx + (x)-10, usy + (y) - 80, thisNumber, Color.BLACK));
        }
    }

    //battling and aftermath
    public int battle(int numOfAnts){
        //enemy power
        int bugPower = rand.nextInt(strength/2-strength,strength/2) + strength;
        //your power is your numOfAnts (this will change later when you can send different types of ants...)
        if (numOfAnts >= bugPower){
            this.content = "protein";
            this.amount = amount * strength;
            this.isBug = false;
            this.strength = 0;
            loadGraphics();
            return numOfAnts - bugPower + rand.nextInt(0,bugPower);
        } else {
            return numOfAnts;
        }
    }

    //methods for movement of the map
    public void right(int num){
        this.x = this.x - num;
        loadGraphics();
    }
    public void left(int num){
        this.x = this.x + num;
        loadGraphics();
    }
    public void up(int num){
        this.y = this.y + num;
        loadGraphics();
    }
    public void down(int num){
        this.y = this.y - num;
        loadGraphics();
    }


    //getters
    public double getY() {
        return this.y;
    }
    public double getX() {
        return this.x;
    }
    public boolean getFound(){
        return found;
    }
    public int getStrength(){
        return strength;
    }
    public Boolean getIsBug(){
        return isBug;
    }
    public String getContent() {
        return content;
    }
    public int getAmount() {
        return (int) amount;
    }
    public int getAntMultiplier(){
        return antMultiplier;
    }
    public boolean isAdjacent() {
        return adjacent;
    }
    public String getType(){
        if(this instanceof GrassyPatch) {
            return "Grassy Patch";
        } else if (this instanceof AntHill){
            return "Ant Hill";
        } else if (this instanceof Picnic){
            return "Picnic Spot";
        } else {
            return "unknown";
        }
    }

    //setters
    public void setFound(boolean found){
        this.found = found;
    }
    public void adjacent(boolean adjacent){
        this.adjacent = adjacent;
    }

    //adders
    public void addAmount(int num) {
        amount += num;
    }

    //subtractors
    public void subtractAmount(int num) {
        amount -= num;
    }
}
