package UDP;

import java.io.IOException;
import java.net.*;

/**
 * Created by zyf on 2016/3/1.
 */
public class ChatClient extends Thread{
    private DatagramSocket s;
    private InetAddress  host_Address;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp  =new DatagramPacket(buf,buf.length);
    private int id ;
    public ChatClient(int identifier){
        id = identifier;
        try{
            s = new DatagramSocket();
            host_Address = InetAddress.getByName("localhost");
        }catch (UnknownHostException e){
            System.out.println("can't find host");
            System.exit(1);
        }
        catch (SocketException e){
            System.out.println("can't open socket");
            System.exit(1);
        }
        System.out.println("server starting");
    }

    @Override
    public void run() {
        try{
            for (int i = 0;i < 25;i++){
                String message = "client #" + id +", message #" + i;
                s.send(Dgram.toDatagram(message,host_Address,ChatterServer.INPORT));
                s.receive(dp);
                String rcvd = "client #"+ id + ", rcvd from"+dp.getAddress()+":"+dp.getPort()+"+"+Dgram.toString(dp);
                System.out.println(rcvd);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        //TODO write your code here
        for (int i = 0;i< 10;i++)
            new ChatClient(i).start();
    }
}
