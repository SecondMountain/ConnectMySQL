package course;

import java.io.*;
import java.net.Socket;
/**
 * Created by zyf on 2016/4/13.
 */
public class ConnectServer {
    private static final String URL = "localhost/127.0.0.1";
    private static final int PORT = 8081;
    private static Socket socket;
    private static InputStream in;
    private static OutputStream out;
    private static BufferedReader bufferedReader;
    public static BufferedWriter bufferedWriter;
    public static boolean requestUpdate(){
        try {
            socket =new Socket(URL,PORT);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));//取得输入流
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));//取得输出流
            //bufferedWriter.write(new Student().getCurrent_courseTable_time());
            bufferedWriter.write("hello");
            String s = bufferedReader.readLine();
            if (s != "123") {
                socket.close();
                return true;
            }
            else {
                socket.close();
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (in!=null)
                    in.close();
                if (out != null)
                    out.close();
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    public static void main(String[] args){
        requestUpdate();
    }
}
