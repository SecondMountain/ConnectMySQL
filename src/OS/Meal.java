package OS;

/**
 * Created by zyf on 2016/6/8.
 */
public class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }
    @Override
    public String toString() {
        return "Meal "+orderNum;
    }
}
