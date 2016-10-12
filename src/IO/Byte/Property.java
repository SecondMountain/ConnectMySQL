package IO.Byte;

import java.util.Date;
import java.util.Properties;

/**
 * 查看系统各种属性
 * Created by zyf on 2016/2/22.
 * @author zyf
 */
public class Property {
    /**
     * 程序入口
     * @param args 多个字符串
     */
    public static void main(String []args){
        System.out.println(new Date());
        Properties pre=System.getProperties();
        pre.list(System.out);
        System.out.println("--Memory Usage:");
        Runtime runtime=Runtime.getRuntime();
        System.out.println("Total Memory:"+runtime.totalMemory()+" \nFree Memory:"+runtime.freeMemory());
        try {
            Thread.currentThread().sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
