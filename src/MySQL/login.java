package MySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static MySQL.OftenUse.run;
import static Print.Print.println;

/**
 * Created by zyf on 2016/5/31.
 */
public class login extends JFrame{
    JPanel JN=new JPanel();
    JPanel JS=new JPanel();
    JPanel BT=new JPanel();
    JLabel id = new JLabel("账号：");
    JLabel pw = new JLabel("密码：");
    JTextField forid=new JTextField(15);
    JTextField forpw=new JPasswordField(15);
    JButton logon = new JButton("登录");
    JButton exit = new JButton("退出");
    String getid = " ";
    String getps = " ";
    ResultSet rs;
    Connect connect;
    public login() {
        connect  = new Connect();
        logon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getid = forid.getText().toString().trim();//取得账号
                getps = forpw.getText().toString().trim();//取得密码
                //判断账号或密码是够为空 且是否相同
                if (getid.equals("") || getps.equals("") || !getid.equals(getps))
                {
                    //进行提示
                    new alter(null).setVisible(true);
                    forid.setText("");
                    forpw.setText("");
                    return;
                }
                else {
                    //封路成功，查询该生信息。
                    rs = connect.useSQLQuery("select * from student where id="+getid+" or identify="+getid);
                    try {
                        if (rs.next()){
                            dispose();
                            new enroll(rs).setVisible(true);
                            return;
                        }
                    } catch (SQLException e1) {
//                        println("数据库搜索错误");
                        println(e1);
                    }catch (Exception e2){

                    }
                    finally {
                    }
                    new alter(null).setVisible(true);
                    forid.setText("");
                    forpw.setText("");
                }


            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JN.add(id);
        JN.add(forid);
        JS.add(pw);
        JS.add(forpw);
        BT.add(logon);
        BT.add(exit);
        setLayout(new GridLayout(7,3));
        this.add(new JLabel());
        this.add(JN);
        this.add(JS);
        this.add(BT);
        setSize(500,500);
    }
    public static void main(String[] args){
        run(new login(),500,500);

    }
    class alter extends JDialog{
        //提示框
        private static final long serialVersionUID = 1L;
        JLabel label=new JLabel("账号或密码错误");
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
    class enroll extends JFrame{
        //提示框
        JButton JBT = new JButton("退出");
        JPanel JS=new JPanel();
        JPanel JC=new JPanel();
        DefaultTableModel TM=new DefaultTableModel();
        JTable jadd=new JTable(TM);
        JScrollPane jscroll;
        public enroll(ResultSet resultSet)throws Exception{
            Object[] title={"准考证号","身份证号","姓名","score1","score2","score3","score4","score5","score6"};
            TM.setColumnIdentifiers(title);
            Object[] get={resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)
            ,resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9)};
            //将获得的一组数据加载到table中（即表中）
            TM.addRow(get);
            JBT.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    dispose();
                    run(new login(),500,500);
                }
            });
            setLayout(new GridLayout(7,3));
            this.setTitle("");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JC.add(JBT);
            jadd.setModel(TM);
            jscroll=new JScrollPane(jadd);
            this.add(JS);
            this.add(jscroll);
            this.add(JC);

            setSize(800,500);
            setVisible(true);
        }

    }

}


