package IO.Character;

import java.io.*;

import static Print.Print.print;
import static Print.Print.println;

/**对文件进行缓冲，提高速度
 * Created by zyf on 2016/10/11.
 */
public class CharacterReader {
    public CharacterReader() {
    }
    public static void readFile(String fileName){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
            int c;
            while (( c=bufferedReader.read() )!= -1){
                print((char)c);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String readFileLine(String fileName){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(fileName)));
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String c;
            while (( c=bufferedReader.readLine() )!= null){
//                println(c);
                stringBuilder.append(c+"\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    public static void main(String[] args){
        println(readFileLine("src/IO/Character/CharacterReader.java"));
    }
}
