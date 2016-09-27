package MySQL;

import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zyf on 2016/3/21.
 */
public class RemoteConnect {
    private  final String url;
    private static   final String name = "com.mysql.jdbc.Driver";
    private  final String user ;
    private  final String password;
    private Connection conn = null;
    private PreparedStatement pst = null;
    private Statement st;
    public RemoteConnect(String ip,String dataBase,String user,String password)throws InterruptedException{
        this.url = "jdbc:mysql://"+ip+"/"+dataBase;
        this.user = user;
        this.password = password;

        TimeUnit.SECONDS.sleep(5);
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");
        } catch (ClassNotFoundException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
    }
    public static void main(String[] args)throws InterruptedException{
        RemoteConnect remoteConnect = new RemoteConnect("127.0.0.1","connectjava","root","125680");
    }
}
