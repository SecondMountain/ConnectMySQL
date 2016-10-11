package DBConnect;

/**
 * Created by zyf on 2016/3/1.
 */
public class ConnectOracle extends ConnectMySql{
//    private static final String url = "137.32.136.21:2632/edwtest";//除去/em:ORCL也是可行，只需要数据库连接的ip地址及端口号即可
    //数据库连接类，属于一个JDBC包里的类
    private static final String name = "oracle.jdbc.driver.OracleDriver";
    //MySQL数据库的用户名和密码
    private final String url;
    private  final String user;
    private  final String password;
    public ConnectOracle(String ip, String dataBase, String user, String password){
        this.url = "jdbc:oracle:thin:@"+ip+"/"+dataBase;
        this.user = user;
        this.password = password;
        getConnnect(url,user,password);
    }
    public static void main(String[] args) {
        //TODO write your code here
//        new ConnectOracle();
        ConnectOracle connectOracle = new ConnectOracle("137.32.136.21:2632","edwtest","db_app","db_app_123");
        connectOracle.getTableColumns("zyf_temp_data".toUpperCase());//oracle 表名需要大写才可以
    }
}
