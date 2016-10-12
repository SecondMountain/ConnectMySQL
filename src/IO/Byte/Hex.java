package IO.Byte;


import java.io.File;

/**
 * Created by zyf on 2016/2/22.
 */
public class Hex {
    public static String format(byte[] data){
        StringBuilder stringBuilder=new StringBuilder();
        int n=0;
        for(byte b:data){
            if(n%16 == 0)
                stringBuilder.append(String.format("%05X: ",n));
            stringBuilder.append(String.format("%02X ",b));
            n++;
            if(n % 16 == 0)stringBuilder.append("\n");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
    public static void main(String[] args)throws Exception{
        if(args.length == 0)
            System.out.println(format(BinaryFile.read("F:\\IntellijProject\\Frame\\src\\com\\zyf\\com\\zyf\\systemProperty\\Hex.java")));
        else
            System.out.println(format(BinaryFile.read(new File(args[0]))));
    }
}
