package DBConnect.ConnectPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static Print.Print.print;
import static Print.Print.println;

/**
 * 连接数据库使用数据库连接池
 * Created by zyf on 2016/7/19.
 */
public class ConToOracle {
    private static final String url = "jdbc:oracle:thin:localhost:1158/em:ORCL";
    //数据库连接类，属于一个JDBC包里的类
    private static final String drive = "oracle.jdbc.driver.OracleDriver";

//    private String dbname;  //数据库名字
    private String userName;    //数据库用户名
    private String passWord;    //登陆口令
    private String ip;      //  address ip
    private int minCon;     //最小连接数
    private int maxCon;     //最大连接数

    private ConnectionPool connectionPool=null;


    public ConToOracle( String userName, String passWord, int minCon, int maxCon) {
        this.userName = userName;
        this.passWord = passWord;
        this.minCon = minCon;
        this.maxCon = maxCon;
    }

    /**
     * 从数据库连接池中获取连接
     * @return
     */
    public Connection getConnectiion(){
        Connection connection = null;
        connectionPool = new ConnectionPool(drive,url,userName,passWord,minCon,maxCon);
        connection = connectionPool.getConnection(30);
        return connection;
    }
    public synchronized void freeConnection(Connection connection){
        connectionPool.freeConnection(connection);
    }
    public synchronized void release(){
        connectionPool.release();
    }
    public static void main(String[] args){
        try {
            ConToOracle cto = new ConToOracle("zyf","qq125680",1,10);
            Connection connection = cto.getConnectiion();
            Statement st = connection.createStatement();
            String sql = "select * from myuser";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                print(rs.getString(1)+"  "+rs.getString(2));
                println();
            }
            cto.freeConnection(connection);
            cto.release();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
