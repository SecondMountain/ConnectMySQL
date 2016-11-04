package DBConnect;

import java.sql.*;

import static Print.Print.print;
import static Print.Print.println;

/**
 * Created by zyf on 2016/3/20.
 */
public class ConnectMySql {
    //数据连接地址
    private  final String url;
    //数据库连接类，属于一个JDBC包里的类
    private static final String name = "com.mysql.jdbc.Driver";
    //MySQL数据库的用户名和密码
    private  final String user;
    private  final String password;
    //数据库连接需要的各个属性
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement st;
    private String updateSql;
    private ResultSet rs;

    public ConnectMySql() {
        this.url = "jdbc:mysql://localhost/zyf?characterEncoding=utf8";
        this.user = "root";
        this.password = "qq125680";
        getConnnect(url,user,password);
    }

    public ConnectMySql(String ip, String dataBase, String user, String password){
        this.url = "jdbc:mysql://"+ip+"/"+dataBase+"?characterEncoding=utf8";
        this.user = user;
        this.password = password;
        getConnnect(url,user,password);
    }
    public void getConnnect(String url,String user,String password){
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("连接成功");
        } catch (Exception e) {
            System.out.println("连接失败");
        }
    }
    //使用数据库查询语句，查询数据库
    public ResultSet query(String sql){
        ResultSet rs = null;
        try {
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            println("Query(String sql) 该方法查询数据失败");
        }
        return rs;
    }
    public int update(String sql){
        int what=-1;
        try {
            what=st.executeUpdate(sql);
        } catch (SQLException e) {
            println("Update(String sql) 该方法更新数据失败");
        }
        return what;
    }
    public void sql(String sql){
        try {
            st.execute(sql);
        } catch (SQLException e) {
            println("SQL(String sql) 该方法更新数据失败");
        }
    }
    public ResultSet preQuery(Object...param){
        ResultSet rs=null;
        try {
            int index=0;
            for (Object obj:param)
                ps.setObject(++index,obj);
            rs=ps.executeQuery();
        } catch (Exception e) {
            println("sql 执行失败，请检查参数是否正确");
        }
        return rs;
    }
    public int preUpdate(Object...param){
        int what=-1;
        try {
            int index=0;
            for (Object obj:param)
                ps.setObject(++index,obj);
            what=ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            println("sql 执行失败，请检查参数是否正确");
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
            println("数据库连接失败");
        }
    }
    public void createStatement(){
        try {
            st = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void prepareStatement(){
        if (updateSql ==null)
            throw new NullPointerException("updatesql 为空，请设置预定义字符串");
        try {
            ps=conn.prepareStatement(updateSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }
    public void getTableColumns(String tableName){
        try {
            DatabaseMetaData dm = conn.getMetaData();
            ResultSet rs = dm.getColumns(conn.getCatalog(),"%",tableName,"%");
            while (rs.next()){
                print(rs.getString("COLUMN_NAME")+" ");
                print(rs.getString("TYPE_NAME")+" ");
                print(rs.getInt("COLUMN_SIZE")+" " );
//                print(rs.getInt("DECIMAL_DIGITS")+" ");
//                print(rs.getInt("NULLABLE")+" ");//是否可为空
                println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getAllTables(){
        try {
            DatabaseMetaData dm = conn.getMetaData();
            rs = dm.getTables(conn.getCatalog(),null,null,new String[]{"TABLE"});
            while (rs.next()) {
                String user = rs.getString("TABLE_SCHEM").toString();
                if (user.equals("DB_APP"))
                    println(rs.getString("TABLE_NAME")+" "+user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        ConnectMySql con = new ConnectMySql("127.0.0.1","zyf","root","qq125680");
        String sql="insert into eployee values(?,?)";
        String update="update eployee  set age=?  where name= ?";
//        int what=con.update(update);
        con.setUpdateSql(update);
        con.prepareStatement();
        int what = con.preUpdate(10,"ccm");
        println(what);
        con.getTableColumns("eployee");
    }
}
