package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by zyf on 2016/3/1.
 */
public class ChatterServer {
    static final int INPORT = 1711;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf,buf.length);
    private DatagramSocket socket;

    public ChatterServer() {
        try{
            socket = new DatagramSocket(INPORT);
            System.out.println("server started");
            while (true){
                socket.receive(dp);
                String revd = Dgram.toString(dp)+", from address: "+dp.getAddress()+", port:"+dp.getPort();
                System.out.println(revd);
                String echoing = "enchoed: " + revd;
                DatagramPacket echo = Dgram.toDatagram(echoing,dp.getAddress(),dp.getPort());
                socket.send(echo);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //TODO write your code here
        new ChatterServer();
    }
}
