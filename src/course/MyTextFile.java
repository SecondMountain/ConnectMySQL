package course;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zyf on 2016/5/24.
 */
public class MyTextFile {
    public static String read(String name){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in= new BufferedReader(new FileReader(
                    new File(name).getAbsoluteFile()));
            try {
                String s;
                while((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
    public static String remove(String string){
        String str="";
        if (string!=null){
            Pattern pattern = Pattern.compile("\r|\n|\t");
            Matcher matcher = pattern.matcher(string);
            str = matcher.replaceAll(" ");
        }
        return str;
    }
}
