package testinterface;

import java.util.Random;

/**
 * Created by zyf on 2016/2/14.
 */
public class RandomDoubles {
    private static Random random=new Random();
    public double next(){return random.nextDouble();}
}
