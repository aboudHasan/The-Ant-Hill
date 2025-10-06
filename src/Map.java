import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Map {
    Random rand = new Random();

    //universal variables
    private final double usx;
    private final double usy;
    private final double usw;
    private final double ush;

    ArrayList<Biomes> biomes = new ArrayList<Biomes>(); //this will be a list to contain the maps biomes


    public Map(double usx,double usy,double usw,double ush){
        this.usx = usx;
        this.usy = usy;
        this.usw = usw;
        this.ush = ush;

        //the below can be put all into one for loop that contains a for loop, but it may be easier to keep them separate for now.
        //row 1
        double x = -10280; //first column
        double y = (ush/2-660); //first row
        for (int j = 50; j > 0; j--){
            x = -10280;
            y += 220;
            for (int i = 100; i > 0; i--){
                if (j == 25 && i == 50) {
                    biomes.add(new AntHill(usx,usy,x,y));
                } else if (rand.nextInt(0,3) <= 1){
                    biomes.add(new Picnic(usx,usy,x,y));
                } else {
                    biomes.add(new GrassyPatch(usx,usy,x,y));
                }
                x += 220;
            }
        }
        /*
        //row 2
        x = -10280;
        y += 220;
        for (int i = 100; i > 0; i--){
            if (rand.nextInt(0,3) <= 1){
                biomes.add(new Picnic(usx,usy,x,y));
            } else {
                biomes.add(new GrassyPatch(usx,usy,x,y));
            }
            x += 220;
        }
        //row 3
        x = -10280;
        y += 220;
        for (int i = 100; i > 0; i--){
            if (i == 50) {
                biomes.add(new AntHill(usx,usy,x,y));
            } else if (rand.nextInt(0,3) <= 1){
                biomes.add(new Picnic(usx,usy,x,y));
            } else {
                biomes.add(new GrassyPatch(usx,usy,x,y));
            }
            x += 220;
        }
        //row 4
        x = -10280;
        y += 220;
        for (int i = 100; i > 0; i--){
            if (rand.nextInt(0,3) <= 1){
                biomes.add(new Picnic(usx,usy,x,y));
            } else {
                biomes.add(new GrassyPatch(usx,usy,x,y));
            }
            x += 220;
        }
        //row 5
        x = -10280;
        y += 220;
        for (int i = 100; i > 0; i--){
            if (rand.nextInt(0,3) <= 1){
                biomes.add(new Picnic(usx,usy,x,y));
            } else {
                biomes.add(new GrassyPatch(usx,usy,x,y));
            }
            x += 220;
        }
         */
    }

    //this is simply the map getting drawn... I wouldn't touch this.
    public void draw(GraphicsContext gc){
        new Rect(usx,usy,usw,ush, Color.rgb(10,160,10)).draw(gc);
        for (int i = 0; i < biomes.size(); i++){
            biomes.get(i).draw(gc);
        }
        new Rect(usx+5,usy+5,225,80,Color.WHITE).draw(gc);
        new RectS(usx+5,usy+5,225,80,Color.BLACK).draw(gc);
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

}
