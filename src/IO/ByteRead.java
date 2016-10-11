package IO;
import java.io.*;

import static Print.Print.println;

/**
 * Created by zyf on 2016/10/11.
 */
public class ByteRead {
    public static void readByte(byte[] bytes){
//        File file = new File(fileName);
//        byte[] bytes={1,2,3,4,5,6,7,8,9};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        while (byteArrayInputStream.available()!=0){
            println(byteArrayInputStream.read());
        }
    }
    public static void main(String[] args){
        readByte(new byte[]{1,2,3,4,5,6,7,8,9});
    }
}
