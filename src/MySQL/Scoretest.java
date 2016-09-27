package MySQL;

/**
 * Created by zyf on 2016/3/1.
 */
public class Scoretest {
    public static boolean test(float...args){
        int sum = 0;
        for (float item:args){
                sum+=item;
        }
        int number=0;
        for (float item:args){
            if (item<60)
                number++;
        }
        if (sum>=300&&number<=1)
            return true;
        return false;
    }
    public static void main(String[] args) {
        //TODO write your code here
        test(60,50,50,60,70,80,90);
    }
}
