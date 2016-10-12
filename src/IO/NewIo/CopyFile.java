package IO.NewIo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zyf on 2016/10/12.
 */
public class CopyFile {
    /**
     * BSiZE 设置ByteBuffer 缓冲的大小
     */
    private static final int BSiZE =1024;
    public static void copy(String source,String destation)throws Exception{

            FileChannel
                    in = new FileInputStream(source).getChannel(),
                    out = new FileOutputStream(destation).getChannel();
            ByteBuffer bf = ByteBuffer.allocate(BSiZE);
            while ( in.read(bf) != -1){
         /*
            bf.flip();
            out.write(bf);
            bf.clear();
            */
                //方式二
                in.transferTo(0,in.size(),out);
                //or
//            out.transferFrom(in,0,in.size());
            }
            in.close();
            out.close();
        }

}
