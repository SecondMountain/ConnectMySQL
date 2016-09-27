package ConOracle;

import java.sql.*;

import static Print.Print.print;
import static Print.Print.println;

/**
 * Created by zyf on 2016/3/1.
 */
public class COracle {
    private static final String url = "jdbc:oracle:thin:@137.32.136.21:2632/edwtest";//除去/em:ORCL也是可行，只需要数据库连接的ip地址及端口号即可
    //数据库连接类，属于一个JDBC包里的类
    private static final String name = "oracle.jdbc.driver.OracleDriver";
    //MySQL数据库的用户名和密码
    private static final String user = "db_app";
    private static final String password = "db_app_123";
    //数据库连接需要的各个属性
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement st;
    private ResultSet rs;
    public COracle(){
        try {
            //加载JDBC类
            Class.forName(name);
            //建立数据库连接
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");
            //创建sql执行语句
            st=conn.createStatement();
            //执行sql语句的查询
            /*
            rs = st.executeQuery("select * from ZYF_TEMP_DATA");
            println("   学号      姓名");
            while (rs.next()){
                println(" "+rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
            }
            */
            //获取列及大小
            DatabaseMetaData dm = conn.getMetaData();
            rs = dm.getColumns(conn.getCatalog(),"%","ZYF_TEMP_DATA","%");
            while (rs.next()){
                print(rs.getString("COLUMN_NAME")+" ");
                print(rs.getString("TYPE_NAME")+" ");
                print(rs.getInt("COLUMN_SIZE")+" " );
                print(rs.getInt("DECIMAL_DIGITS")+" ");
                print(rs.getInt("NULLABLE")+" ");
                println();
            }
            rs = dm.getTables(conn.getCatalog(),null,null,new String[]{"TABLE"});
            while (rs.next()) {
                String user = rs.getString("TABLE_SCHEM").toString();
                if (user.equals("DB_APP"))
                    println(rs.getString("TABLE_NAME")+" "+user);

            }
        } catch (ClassNotFoundException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("连接失败");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //TODO write your code here
        new COracle();
    }
}
