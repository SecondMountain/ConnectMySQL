package course;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zyf on 2016/3/1.
 */
public class JavaSocketThread extends Thread{
    public static final int PORT = 8081;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public JavaSocketThread(Socket s)throws IOException{
        socket =s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        start();
    }
    public void run(){
        try {
            while (true){
                String str= in.readLine();
//                String[]  split = str.split(" |/|\\?|&");
                //分割之后以第四个为起点进行获取参数值等，，，
                //不要获取错了咩，，，，
                System.out.println("enchoing "+str);
                out.println(str);
                out.write(str);
                out.flush();
                break;
            }
        }catch (IOException e){}
        finally {
            try{
                socket.close();
            }
            catch (IOException e){}
        }
    }

    public static void main(String[] args)throws IOException {
        ServerSocket serverSocket =  new ServerSocket(PORT);
        System.out.println("server started");
        try{
            while (true){
                Socket socket = serverSocket.accept();
                try{
                    new JavaSocketThread(socket);

                }catch (IOException e){
                    socket.close();
                }
            }
        }finally {
            serverSocket.close();
        }
    }
}
