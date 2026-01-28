package game;

import game.biomes.*;
import game.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import game.shapes.Rect;
import game.shapes.RectS;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    Random rand = new Random();

    //universal variables
    private final double usx;
    private final double usy;
    private final double usw;
    private final double ush;
    private final double screenY = (Screen.getPrimary().getVisualBounds()).getHeight();
    private final double screenX = (Screen.getPrimary().getVisualBounds()).getWidth();
    private ArrayList<Shape> blackSides = new ArrayList<>();
    private int[] seed = new int[10000];

    ArrayList<Biomes> biomes = new ArrayList<>(); //this will be a list to contain the maps biomes


    public Map(double usx,double usy,double usw,double ush){
        this.usx = usx;
        this.usy = usy;
        this.usw = usw;
        this.ush = ush;
        blackSides.add(new Rect(usx+usw,0,screenX-(usw + usx),screenY,Color.BLACK));
        blackSides.add(new Rect(0,0,screenX-(usw + usx),screenY,Color.BLACK));
        blackSides.add(new Rect(0,0,screenX,screenY-(ush + usy),Color.BLACK));
        blackSides.add(new Rect(0,usy+ush,screenX,screenY-(ush + usy),Color.BLACK));


        //the loop that generates the map. currently, seeds aren't recorded or usable
        double y = (ush/2-11235); //first row
        for (int j = 100; j > 0; j--){
            double x = -10280; //first column
            y += 220;
            for (int i = 100; i > 0; i--){
                if (j == 50 && i == 50) {
                    biomes.add(new AntHill(usx,usy,x,y));
                } else if (rand.nextInt(0,6) == 5){
                    biomes.add(new Picnic(usx,usy,x,y));
                } else if (rand.nextInt(0,3)==2){
                    biomes.add(new FlowerField(usx,usy,x,y));
                } else {
                    biomes.add(new GrassyPatch(usx,usy,x,y));
                }
                x += 220;
            }
        }
    }

    //this is the map getting drawn
    public void draw(GraphicsContext gc){
        new Rect(usx,usy,usw,ush, Color.rgb(10,160,10)).draw(gc);
        for (int i = 0; i < biomes.size(); i++){
            //the following 2 if statements optimize the system by only drawing the places 'in frame'.
            if (biomes.get(i).getY() >= usy - 200 && biomes.get(i).getX() >= usx - 200) {
                if ( biomes.get(i).getY() <= ush + 200 && biomes.get(i).getX() <= usw + 200) {
                    biomes.get(i).draw(gc);
                }
            }
        }
        new Rect(usx+5,usy+5,225,80,Color.WHITE).draw(gc);
        new RectS(usx+5,usy+5,225,80,Color.BLACK).draw(gc);
        //ensuring the screen is just black at the edges. (temp solution to not having any scaling from one computer to another)
        for (Shape blackSide : blackSides) {
            blackSide.draw(gc);
        }
    }

    /**
     * This will draw the map, and save its new values (concerning adjacency)
     * @param gc: graphics context, the screen we are drawing to.
     */
    public void updateDraw(GraphicsContext gc){
        new Rect(usx,usy,usw,ush, Color.rgb(10,160,10)).draw(gc);
        for (int i = 0; i < biomes.size(); i++){
            //checking if its adjacency should be set to true (needs to be fixed at some point to be better...
            if (!biomes.get(i).isAdjacent()) {
                if (i == 0) {//first area/biome
                    if (biomes.get(i + 1).getFound() || biomes.get(i + 100).getFound()) {
                        biomes.get(i).setAdjacent(true);
                    }
                }else if (i < 100){//top row (first row)
                    if (biomes.get(i - 1).getFound() || biomes.get(i + 1).getFound() || biomes.get(i + 100).getFound()){
                        biomes.get(i).setAdjacent(true);
                    }
                } else if (i == biomes.size() - 1) {//last area/biome
                    if (biomes.get(i - 1).getFound() || biomes.get(i - 100).getFound()) {
                        biomes.get(i).setAdjacent(true);
                    }
                }else if (i >= 9900){//bottom row (last row)
                    if (biomes.get(i - 1).getFound() || biomes.get(i - 100).getFound() || biomes.get(i + 1).getFound()){
                        biomes.get(i).setAdjacent(true);
                    }
                } else if (biomes.get(i - 1).getFound() || biomes.get(i + 1).getFound() || //all the rest of the areas/biomes
                        biomes.get(i + 100).getFound() || biomes.get(i - 100).getFound()) {
                    biomes.get(i).setAdjacent(true);
                }
            }
        }
        draw(gc);
    }

    /**
     * checks to see if a biome has been clicked on in the map.
     * @param x mouse position
     * @param y mouse position
     * @return int, the 'number'/ID/placement within the array of a biome/area/tile.
     */
    public int selected(double x, double y){
        //only searching through the visible biomes on screen
        for (int i = 0; i < biomes.size(); i++){
            if (biomes.get(i).getY() >= usy - 200 && biomes.get(i).getX() >= usx - 200) {
                if (biomes.get(i).getY() <= ush + 200 && biomes.get(i).getX() <= usw + 200) {
                    double[] location = biomes.get(i).location();
                    //checking if the biome was tapped
                    if (x > location[0] && x < location[1]){
                        if (y > location[2] && y < location[3]){
                            return biomes.get(i).getThisNumber();
                        }
                    }
                }
            }
        }
        return -1;
    }

    public void right(GraphicsContext gc, int num){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).right(num);
        }
        draw(gc);
    }
    public void left(GraphicsContext gc, int num){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).left(num);
        }
        draw(gc);
    }
    public void up(GraphicsContext gc, int num){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).up(num);
        }
        draw(gc);
    }
    public void down(GraphicsContext gc, int num){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).down(num);
        }
        draw(gc);
    }

    public Biomes biome(int num){
        return biomes.get(num);
    }

    /// this method enables cheat mode, which causes clouds to disappear, and every biome to be found.
    public void cheatMode() {
        for (Biomes biome : biomes){
            biome.setFound(true);
        }
    }
}
