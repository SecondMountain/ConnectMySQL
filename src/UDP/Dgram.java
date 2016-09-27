package UDP;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 * Created by zyf on 2016/5/18.
 */
public class Dgram {
    public static DatagramPacket toDatagram(String s, InetAddress dest,int destPort){
        byte[] buf = new byte[s.length()+1];
        s.getBytes(0,s.length(),buf,0);
        return new DatagramPacket(buf,buf.length,dest,destPort);
    }
    public static String toString(DatagramPacket packet){
        return new String(packet.getData(),0,packet.getLength());
    }
}
