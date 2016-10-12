package Log;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Print.Print.println;

/**
 * Created by zyf on 2016/9/27.
 */
public class Log4j {
    public static Logger logger;

    public Log4j(Class clazz) {
        logger = Logger.getLogger(Log4j.class);
    }
    public void debug(String info){
        logger.debug(info);
    }
    public void info(String info){logger.info(info);}
    public void error(String info){logger.error(info);}
    public void warn(String info){logger.warn(info);}
    public static void main(String[] args) {
        //TODO write your code here
        Pattern pattern = Pattern.compile("[0-9]");
        String str="zhofjdioa464654";
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()){
            println(matcher.group());
//            matcher.replaceAll(" ");全部替换为。。。
            logger.debug("This is debug message.");
            // 记录info级别的信息
            logger.info("This is info message.");
            // 记录error级别的信息
            logger.error("This is error message.");
            logger.warn("This is warn message");

        }
    }
}
