package IO.Byte;
import java.io.*;

import static Print.Print.print;

/**
 * Created by zyf on 2016/10/11.
 */
public class DataReader {
    public DataReader() {

    }
    public static void readFile(String fileName){
        try {
            File file = new File(fileName);
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            int c;
            while (( c=dataInputStream.read() )!= -1){
                print((char)c);
            }
//            while (dataInputStream.available()!=0){
//                print((char)dataInputStream.readByte());
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        readFile("src/IO/Byte/DataReader.java");
    }

}
