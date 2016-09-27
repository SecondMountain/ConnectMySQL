package MySQL;

import java.sql.*;

import static Print.Print.println;

/**
 * Created by zyf on 2016/3/20.
 */
public class Connect {
    //数据连接地址
    private static final String url = "jdbc:mysql://127.0.0.1/connectjava";
    //数据库连接类，属于一个JDBC包里的类
    private static final String name = "com.mysql.jdbc.Driver";
    //MySQL数据库的用户名和密码
    private static final String user = "root";
    private static final String password = "125680";
    //数据库连接需要的各个属性
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement st;
    public Connect(){
        try {
            //加载JDBC
            Class.forName(name);
            //建立连接
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
    //使用数据库查询语句，查询数据库
    public ResultSet useSQLQuery(String sql){
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
    public int useSQLUpdate(String sql){
        int what=-1;
        try {
            st = conn.createStatement();
            what=st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return what;
    }
    public void useSQL(String sql){
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ResultSet usePreSQLQuery(String sql){
        ResultSet rs=null;

        try {
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    public int usePreSQLUpdate(String param,String parama){
        int what=-1;
        String sql="insert into test values(?,?)";
        try {
            ps=conn.prepareStatement(sql);
            ps.setString(1,param);
            ps.setString(2,parama);
            what=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            println("insert failed");
        }
        return what;
    }
    public void close(){
        try {
            if (st != null)
                    st.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Connect con = new Connect();
        int what = con.usePreSQLUpdate("201315424","zyf");
        println(what);
        ResultSet resultSet = con.useSQLQuery("select * from test");
        try {
            while (resultSet.next()){
                System.out.println(resultSet.getString(1)+ " "+resultSet.getString(2));
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {

        }

    }
}
