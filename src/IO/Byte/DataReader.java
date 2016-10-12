package IO.Byte;
import java.io.*;

import static Print.Print.print;

/**
 * Created by zyf on 2016/10/11.
 */
public class DataReader {
    public DataReader() {

    }
    public static byte[] readFile(String fileName){
        try {
            File file = new File(fileName);
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            byte[] data =new byte[dataInputStream.available()];
            dataInputStream.read(data);
            return data;
//            int c;
//            while (( c=dataInputStream.read() )!= -1){
//                print((char)c);
//            }
//            while (dataInputStream.available()!=0){
//                print((char)dataInputStream.readByte());
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        byte[] data=readFile("src/IO/Byte/DataReader.java");
        for (byte c:data)
            print((char)c);
    }

}
