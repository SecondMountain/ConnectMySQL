package OS;

import java.util.concurrent.TimeUnit;

import static Print.Print.print;
import static Print.Print.println;

/**
 * Created by zyf on 2016/6/8.
 */
public class Chef implements Runnable {
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal != null)
                        wait();
                }
                if (++count == 10){
                    println("out of food,closing");
                    restaurant.exec.shutdownNow();
                }
                print("order up ");
                synchronized (restaurant.waitPerson){
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            println("chef interrupted");
        }
    }
}
