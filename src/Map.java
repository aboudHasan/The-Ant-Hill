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
        //this is where all biomes get added

        //this is for like "story mode" (a map with set biomes everytime (un-efficient)

        //row 1
        biomes.add(new Picnic(usx,usy,60,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,280,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,500,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,720,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,940,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,1160,(ush/2-440)));
        biomes.add(new GrassyPatch(usx,usy,1380,(ush/2-440)));
        //row 2
        biomes.add(new GrassyPatch(usx,usy,60,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,280,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,500,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,720,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,940,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,1160,(ush/2-220)));
        biomes.add(new GrassyPatch(usx,usy,1380,(ush/2-220)));
        //row 3
        biomes.add(new GrassyPatch(usx,usy,60,ush/2));
        biomes.add(new GrassyPatch(usx,usy,280,ush/2));
        biomes.add(new Picnic(usx,usy,500,ush/2));
        biomes.add(new AntHill(usx,usy,usw,ush));
        biomes.add(new GrassyPatch(usx,usy,940,ush/2));
        biomes.add(new GrassyPatch(usx,usy,1160,ush/2));
        biomes.add(new GrassyPatch(usx,usy,1380,ush/2));
        //row 4
        biomes.add(new GrassyPatch(usx,usy,60,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,280,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,500,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,720,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,940,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,1160,(ush/2+220)));
        biomes.add(new GrassyPatch(usx,usy,1380,(ush/2+220)));
        //row 5
        biomes.add(new GrassyPatch(usx,usy,60,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,280,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,500,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,720,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,940,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,1160,(ush/2+440)));
        biomes.add(new GrassyPatch(usx,usy,1380,(ush/2+440)));
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

    public void right(GraphicsContext gc){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).right();
        }
        draw(gc);
    }
    public void left(GraphicsContext gc){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).left();
        }
        draw(gc);
    }
    public void up(GraphicsContext gc){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).up();
        }
        draw(gc);
    }
    public void down(GraphicsContext gc){
        for (int i = 0; i < biomes.size(); i++){
            biome(i).down();
        }
        draw(gc);
    }

    public Biomes biome(int num){
        return biomes.get(num);
    }

}
