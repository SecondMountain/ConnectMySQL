package OS;

import static Print.Print.println;

/**
 * Created by zyf on 2016/6/8.
 */
public class WaitPerson implements Runnable {
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal == null)
                        wait();
                }
                println("WaitPerson get "+restaurant.meal);
                synchronized (restaurant.chef){
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
            println("waitperson interrupted");
        } finally {
        }
    }
}
