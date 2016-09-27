import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Print.Print.println;

/**
 * Created by zyf on 2016/3/1.
 */
public class Regex {
    public static void main(String[] args) {
        //TODO write your code here
        Pattern pattern = Pattern.compile("[0-9]");
        String str="zhofjdioa464654";
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            println(matcher.group());
//            matcher.replaceAll(" ");全部替换为。。。
        }
    }
}
