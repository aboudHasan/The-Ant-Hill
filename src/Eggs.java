import java.util.Random;

public class Eggs {
    private int daysAlive = 0;
    private Boolean mustHatch = false;
    private Random rand = new Random();

    public Eggs(){
        //takes 2 to 3 turns for an egg to hatch.
    }

    public int getDaysAlive(){
        return daysAlive;
    }
    public void addDaysAlive(){
        this.daysAlive++;

        //using randomizer to check if egg will hatch (remain at days alive 2);
        if (!this.mustHatch) {
            if (daysAlive == 2) {
                int num = rand.nextInt(2);
                if (num == 1) {
                    this.daysAlive--;
                    this.mustHatch = true;
                }
            }
        }

    }
}
