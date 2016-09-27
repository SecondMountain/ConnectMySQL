package ConnectPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import static Print.Print.println;

/**
 * Created by zyf on 2016/7/19.
 */
public class ConnectionPool {
    private Vector freeConnections = new Vector();
    private int minCon = 0;//最小连接数
    private int curCon = 0;//已经使用的链接数
    private int maxCon; //连接池中最大的连接数
    private String drive;   //数据库驱动程序类
    private String url; //数据库的jdbcUrl
    private String user;    //数据库登陆用户
    private String passWord;    //数据库用户密码，登陆口令

    public ConnectionPool( String drive, String url, String user, String passWord,int minCon,  int maxCon) {
        this.minCon = minCon;
        this.maxCon = maxCon;
        this.drive = drive;
        this.url = url;
        this.user = user;
        this.passWord = passWord;
        initConnection();
    }

    public ConnectionPool(String drive, String url, String user, String passWord,  int maxCon) {
        this(drive, url, user, passWord, 0,maxCon);
    }
    private void initConnection(){
        Connection con = null;
        try {
            Class.forName(drive);

            for (int i = 0;i<minCon;i++){
                try {
                    if (user == null)
                        con = DriverManager.getConnection(url);
                    else
                        con = DriverManager.getConnection(url,user,passWord);
                } catch (SQLException e) {
                    con = null;
                    println(e.toString());
                }
                if (con!=null)
                    freeConnections.addElement(con);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取新的连接
     * @param timeOut 超出时长
     * @return 数据库连接
     */
    public synchronized Connection getConnection(long timeOut){
        Connection connection = null;
        //如果数据库空闲连接的数量大于零个，则取出该连接，并且从容器里面拿出来
        if (freeConnections.size()>0){
            connection = (Connection)freeConnections.firstElement();
            freeConnections.removeElementAt(0);

            //若该数据库连接已经关闭了，则从新再取一个连接
            try {
                if (connection.isClosed())
                    connection = getConnection(timeOut);
            } catch (SQLException e) {
                connection = getConnection(timeOut);
            }
        }
        //假如数据库空闲连接没了，那么久新创建一个连接
        else if (curCon<maxCon){
            connection = newConnection();
        }
        else{
            try {
                wait(timeOut);
            } catch (InterruptedException e) {
                return null;
            }
        }
        //取得连接了，将当前正在使用的数据库数量加一
        if (connection!=null)
            curCon++;

        //  OK，最后返回取得的数据库连接
        return connection;
    }

    /**
     * 动态获取新的数据库连接
     * @return
     */
    private Connection newConnection(){
        Connection connection = null;
        try {
            if (user == null)
                connection = DriverManager.getConnection(url);
            else
                connection = DriverManager.getConnection(url,user,passWord);
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    /**
     * 将使用过的数据库连接放回到容器里，回收
     * @param connection
     */
    public synchronized void freeConnection(Connection connection){
        freeConnections.addElement(connection);
        curCon--;
        notifyAll();
    }

    /**
     *  释放所有的数据库连接，这些连接都不用了，全部关闭
     */
    public synchronized void release(){
        Enumeration allConnection = freeConnections.elements();     //一个枚举，取得所有的数据库连接引用
        int i=0;
        while (allConnection.hasMoreElements()){
            Connection connection = (Connection)allConnection.nextElement();    //取得每一个连接
            try {
                connection.close();     //关闭连接
                println("连接"+i+" 已关闭");
                i++;
            } catch (SQLException e) {

            }
        }
        freeConnections.removeAllElements();    //清空容器。。。。关闭
    }

}
