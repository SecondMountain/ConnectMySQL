package MySQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by zyf on 2016/6/3.
 */
class EnrollTable extends JFrame {
    //提示框
    JButton JBT = new JButton("退出");
    JPanel JS=new JPanel();
    JPanel JC=new JPanel();
    DefaultTableModel TM=new DefaultTableModel();
    JTable jadd=new JTable(TM);
    JScrollPane jscroll;
    public EnrollTable(ResultSet resultSet)throws Exception{
        Object[] title={"准考证号","身份证号","姓名","score1","score2","score3","score4","score5","score6","总分"};
        TM.setColumnIdentifiers(title);
        Object[] get={resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5)
                ,resultSet.getString(6),resultSet.getString(7),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10)};
        //将获得的一组数据加载到table中（即表中）
        TM.addRow(get);
        JBT.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
//                run(new login(),500,500);
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
