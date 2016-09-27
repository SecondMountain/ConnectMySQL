package course;

import java.text.SimpleDateFormat;
import java.util.Date;
import static Print.Print.print;

/**
 * Created by zyf on 2016/4/21.
 */
public class Time {
    public static void main(String[] args){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH-MM");
        String time=simpleDateFormat.format(new Date());
        System.out.print(time);
        print(time);
    }
}
