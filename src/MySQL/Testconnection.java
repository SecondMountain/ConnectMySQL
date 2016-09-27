package MySQL;

/**
 * Created by zyf on 2016/5/27.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static MySQL.OftenUse.run;
import static MySQL.Scoretest.test;

public class Testconnection  extends JFrame{
    /**
     *
     */
    private static final long serialVersionUID = -2831567183035030814L;
    private static final String url = "jdbc:mysql://127.0.0.1/connectjava";
    private static final String name = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "125680";
    //建立连接桥
    JPanel JN=new JPanel(),
         JW=new JPanel(),
        JE= new JPanel(),
         JS=new JPanel();
    //一种缺省的二维表，在这不介绍，详细请看API
    DefaultTableModel TM=new DefaultTableModel();
    JTable jadd=new JTable(TM);
    //表头（每列的列明）
    String[] title={"学号","姓名"};
    JScrollPane jscroll;
    JLabel search=new JLabel("请输入证件号：");
    JLabel delete=new JLabel("或");
    private JTextField text=new JTextField(15);
    private JButton
            Jstart=new JButton("查询"),
            Joutput=new JButton("刷新"),
            Jadds=new JButton("添加空行"),
            JaddData = new JButton("添加数据"),
            Jmakesure=new JButton("确定添加"),
            Jchange=new JButton("确定修改"),
            Jdelete=new JButton("删除"),
            Jprint = new JButton("打印录取名单");
    //private JTextArea text=new JTextArea(20,40);
    JPanel Jid=new JPanel();
    JPanel Jps=new JPanel();
    JPanel Jbt=new JPanel();
    JLabel label1=new JLabel("账号:");
    JLabel label2=new JLabel("密码:");
    JTextField JTid=new JTextField(10);
    JTextField JTps=new JTextField(10);
    JButton JBtdl=new JButton("登陆");
    JButton JBtzc=new JButton("注册");
    public Testconnection(){
        super();
        try
        {

            try
            {
                //检测是否能连接到数据库
                Class.forName(name);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            //使用用户名和密码取得与数据库的正式连接
            Connection Con=DriverManager.getConnection(url,user,password);
            //text.setText("连接数据库成功");
            Statement stmt=Con.createStatement();
            String s=new String("SELECT * FROM student");
            //ResultSet为SQL连接里的一个结果集，声明一个结果集变量rs2
            ResultSet resultSet;
            //rs2将得到从数据库返回的一个结果集（一组数据）
            //executeQuery可以从数据库得到结果集，executeUpdate向数据库返回更新
            //详细用法参考API
            resultSet = stmt.executeQuery(s);
            Object[] title={"准考证号","身份证号","姓名","score1","score2","score3","score4","score5","score6"};
            TM.setColumnIdentifiers(title);
            //TM.setDataVector(Vector1,Vector2);Vector1位数据，Vector2为列名
            //得到结果集
            while(resultSet.next()){
                //将得到的结果集加入到一个向量中
                Object[] get={resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)
                        ,resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)};
                //将获得的一组数据加载到table中（即表中）
                TM.addRow(get);
            }
            Jstart.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    int number=TM.getRowCount();
                    for(int i=0;i<number;i++)
                    {
                        TM.removeRow(number-i-1);
                    }
                    ResultSet rs2;
                    try {
                        String ID=text.getText();
                        String sadd="'";
                        ID=sadd+ID+sadd;
                        //String s=new String("SELECT * FROM test where id="+ID);
                        rs2 = stmt.executeQuery("SELECT * FROM student where id="+ID+" or identify="+ID);
                        if(rs2.next()){
                            //将得到的结果集加入到一个向量中
                            Object[] get={rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5)
                                    ,rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9)};
                            //将获得的一组数据加载到table中（即表中）
                            TM.addRow(get);

                        }
                        else{
                            new alter(null).setVisible(true);
                        }
                        text.setText("");
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    //Object[] news={"666","sss"};
                    //TM.addRow(news);
                }
            });
            Jdelete.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    String ID=text.getText();
                    try {
                        stmt.executeUpdate("delete from test where id="+ID);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    int number=TM.getRowCount();
                    for(int i=0;i<number;i++)
                    {
                        TM.removeRow(number-i-1);
                    }
                    ResultSet rs2;
                    try {
                        rs2 = stmt.executeQuery("SELECT * FROM test ");
                        while(rs2.next()){
                            //将得到的结果集加入到一个向量中
                            Object[] get={rs2.getString(1),rs2.getString(2)};
                            //将获得的一组数据加载到table中（即表中）
                            TM.addRow(get);
                        }
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            });
            Joutput.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    //重写Table
                    int number=TM.getRowCount();
                    for(int i=0;i<number;i++)
                    {
                        TM.removeRow(number-i-1);
                    }
                    ResultSet rs2;
                    try {
                        rs2 = stmt.executeQuery("SELECT * FROM student ");
                        while(rs2.next()){
                            //将得到的结果集加入到一个向量中
                            Object[] get={rs2.getString(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5)
                                    ,rs2.getString(6),rs2.getString(7),rs2.getString(8),rs2.getString(9)};
                            //将获得的一组数据加载到table中（即表中）
                            TM.addRow(get);
                        }
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            Jadds.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Object[] add={};
                    TM.addRow(add);
                }
            });
            Jmakesure.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int row=TM.getRowCount()-1;
                    Object dataid=TM.getValueAt(row, 0);
                    Object dataidentify=TM.getValueAt(row, 1);
                    Object dataname = TM.getValueAt(row,2);
                    float Score1 = Float.parseFloat(TM.getValueAt(row,3).toString());
                    float Score2 = Float.parseFloat(TM.getValueAt(row,4).toString());
                    float Score3 = Float.parseFloat(TM.getValueAt(row,5).toString());
                    float Score4 = Float.parseFloat(TM.getValueAt(row,6).toString());
                    float Score5 = Float.parseFloat(TM.getValueAt(row,7).toString());
                    float Score6 = Float.parseFloat(TM.getValueAt(row,8).toString());

                    String pt="\"";
                    float sum = Score1+Score2+Score3+Score4+Score5+Score6;
                    try {
                        stmt.executeUpdate("insert student values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                ","+Score1+","+Score2+","+Score3+","+Score4+","+Score5+","+Score6+")");
                        stmt.executeUpdate("insert studentscores values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                ","+sum+")");
                        if (test(Score1,Score2,Score3,Score4,Score5,Score6)){
                            stmt.executeUpdate("insert enrolls values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                    ","+sum+")");
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }


                }
            });
            Jchange.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    int row=jadd.getSelectedRow();
                    int column=jadd.getSelectedColumn();
                    CellEditor ce = jadd.getCellEditor(row, column);
                    ce.stopCellEditing();
                    DefaultTableModel dtm = (DefaultTableModel) jadd.getModel();
                    Object getdata=dtm.getValueAt(row, column);

                    String columnName=dtm.getColumnName(column);
                    ResultSet rs2;


                    Object dataid=TM.getValueAt(row, 0);
                    Object dataidentify=TM.getValueAt(row, 1);
                    Object dataname = TM.getValueAt(row,2);
                    float Score1 = Float.parseFloat(TM.getValueAt(row,3).toString());
                    float Score2 = Float.parseFloat(TM.getValueAt(row,4).toString());
                    float Score3 = Float.parseFloat(TM.getValueAt(row,5).toString());
                    float Score4 = Float.parseFloat(TM.getValueAt(row,6).toString());
                    float Score5 = Float.parseFloat(TM.getValueAt(row,7).toString());
                    float Score6 = Float.parseFloat(TM.getValueAt(row,8).toString());
                    String pt="\"";
                    float sum = Score1+Score2+Score3+Score4+Score5+Score6;
                    try {
                        rs2 = stmt.executeQuery("SELECT * FROM student ");
                        String get1="";
                        String name1="'";
                        int i=0;
                        while(rs2.next()){
                            //将得到的结果集加入到一个向量中
                            //得到属性改变之前的值
                            String get=rs2.getString(1);
                            if(i==row)
                            {
                                get1=get;
                            }
                            i++;

                        }
                        stmt.executeUpdate("delete from student where id="+get1);
                        stmt.executeUpdate("delete from studentscores where id="+get1);
                        stmt.executeUpdate("delete from enrolls where id="+get1);
                        stmt.executeUpdate("insert student values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                ","+Score1+","+Score2+","+Score3+","+Score4+","+Score5+","+Score6+")");
                        stmt.executeUpdate("insert studentscores values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                ","+sum+")");
                        if (test(Score1,Score2,Score3,Score4,Score5,Score6)){
                            stmt.executeUpdate("insert enrolls values("+pt+dataid+pt+","+pt+dataidentify+pt+","+pt+dataname+pt+
                                    ","+sum+")");
                        }
                    }
                    catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }

            });
            Jprint.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ResultSet rs;
                    try {
                        rs = stmt.executeQuery("SELECT student.id,student.identify,enrolls.`name` ,student.firstScore, student.secondScore\n" +
                                ",student.thirdScore, student.fourthScore, student.fiveScore, student.sixScore, enrolls.Scores\n" +
                                " FROM student,enrolls WHERE student.id=enrolls.id ORDER BY firstScore DESC");
                        if (rs.next())
                            new EnrollTable(rs).setVisible(true);
                        else
                            new alter(null).setVisible(true);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    } finally {
                    }
                }
            });
            JaddData.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    run(new DataAdd(),500,500);
                }
            });
        }
        catch(SQLException s)
        {
            s.printStackTrace();
            //System.out.println("数据库连接错误");
            System.exit(0);
        }
        jadd.setModel(TM);
        jscroll=new JScrollPane(jadd);
        JN.add(search);
        JN.add(text);
        JN.add(Jstart);
//        JN.add(delete);
        JN.add(Jprint);
        JS.add(Joutput);
        JS.add(JaddData);
//        JS.add(Jadds);
//        JS.add(Jmakesure);
        JS.add(Jchange);
        this.add(JN,"North");
        this.add(jscroll);
        this.add(JS,"South");
        setSize(500,500);

    }

    public static void main(String[] args){
        run(new Testconnection(),500,500);
    }
    class alter extends JDialog{
        //提示框
        private static final long serialVersionUID = 1L;
        JLabel label=new JLabel("报表为空");
        JButton JBT=new JButton("确定");
        JPanel JN=new JPanel();
        JPanel JS=new JPanel();
        JPanel JC=new JPanel();
        public alter(JFrame parent){
            super(parent,"警告",true);
            JBT.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    dispose();
                }
            });
            JN.add(label);
            JS.add(JBT);
            this.add(JC,"North");
            this.add(JN,"Center");
            this.add(JS, "South");
            setSize(300,200);
        }

    }

}


