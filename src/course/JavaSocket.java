package course;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import static Print.Print.println;

/**
 * Created by zyf on 2016/3/1.
 */
public class JavaSocket {
    public static final int PORT = 8081;
    public void client()throws IOException{
        InetAddress a = InetAddress.getLocalHost();

        Socket socket = new Socket(a,PORT);
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())),true);
            for (int i =0; i<10;i++){
                out.println("hello"+i);
                out.flush();
                String str = in .readLine();
                System.out.println("get "+str);
            }
            out.println("END");
        }finally {
            System.out.println("closing...");
            socket.close();
        }
    }
    public static void main(String[] args) throws Exception{
        //TODO write your code here
        new JavaSocket().client();
        println("dfafasd");
        /*
        InetAddress a = InetAddress.getByName(null);
        System.out.println("my address is :"+a);
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("started "+serverSocket);
        try{
            Socket socket =serverSocket.accept();
            try{
                System.out.println("connect on accepted"+socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                while (true){

                    String str = in.readLine();
                    println(str);
                    if (str == null) break;
                    System.out.println("encoding "+ str);
                    out.println(str);
                }

            }finally {
                System.out.println("closing...");
                socket.close();
            }
        }
        finally {
            serverSocket.close();
        }
        */
    }
}
