package IO.Byte;

import java.io.*;

/**
 * Created by zyf on 2016/10/12.
 */
public class FileToFile {
    public static void fileToFile(String source,String destation){
        try {
            BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new File(destation));
            int lineCount = 1;
            String str;
            while((str = in.readLine())!=null)
                out.println(lineCount++ +": "+str);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
