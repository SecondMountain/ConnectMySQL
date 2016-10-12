package testinterface;

import java.nio.CharBuffer;
import java.util.Scanner;

/**
 * Created by zyf on 2016/2/14.
 */
public class AdapterRandomDoubles extends RandomDoubles implements Readable {
    private int count;

    public AdapterRandomDoubles(int count) {
        this.count = count;
    }
    public int read(CharBuffer cb){
        if(count--==0)
            return -1;
        String result=Double.toString(next())+" ";
        cb.append(result);
        return result.length();
    }
    public static void main(String[] args){
        Scanner scanner=new Scanner(new AdapterRandomDoubles(7));
        while (scanner.hasNextDouble())System.out.print(scanner.nextDouble()+"\n");
    }
}
