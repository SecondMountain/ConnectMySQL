package MySQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MySQL.OftenUse.run;
import static MySQL.Scoretest.test;


/**
 * Created by zyf on 2016/6/17.
 */
public class DataAdd extends JFrame {
    JPanel Jid = new JPanel(),
            Jidentify = new JPanel(),
        Jname = new JPanel(),
        Jscoreone = new JPanel(),
        Jscoretwo = new JPanel(),
        Jscorethree = new JPanel(),
        Jscorefour = new JPanel(),
        Jscorefive = new JPanel(),
        Jscoresix = new JPanel(),
        JBT = new JPanel();
    JLabel forid = new JLabel("学    号："),
    foridentify = new JLabel("证件号："),
    forname = new JLabel("姓    名："),
    forOne = new JLabel("科目一："),
    forTwo = new JLabel("科目二："),
    forThree = new JLabel("科目三："),
    forFour = new JLabel("科目四："),
    forFive = new JLabel("科目五："),
    forSix = new JLabel("科目六：");
    JTextField id = new JTextField(10),
    identify = new JTextField(10),
    name = new JTextField(10),
    scoreOne = new JTextField(10),
    ScoreTwo= new JTextField(10),
    ScoreThree= new JTextField(10),
    ScoreFour= new JTextField(10),
    ScoreFive= new JTextField(10),
    ScoreSix= new JTextField(10);
    JButton Ok = new JButton("确定"),
    cancle = new JButton("取消");
    Connect connect;
    public DataAdd(){
        Jid.add(forid);
        Jid.add(id);
        Jidentify.add(foridentify);
        Jidentify.add(identify);
        Jname.add(forname);
        Jname.add(name);
        Jscoreone.add(forOne);
        Jscoreone.add(scoreOne);
        Jscoretwo.add(forTwo);
        Jscoretwo.add(ScoreTwo);
        Jscorethree.add(forThree);
        Jscorethree.add(ScoreThree);
        Jscorefour.add(forFour);
        Jscorefour.add(ScoreFour);
        Jscorefive.add(forFive);
        Jscorefive.add(ScoreFive);
        Jscoresix.add(forSix);
        Jscoresix.add(ScoreSix);
        JBT.add(Ok);
        JBT.add(cancle);
        Ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = id.getText();
                String IDENTIFY = identify.getText();
                String NAME = name.getText();
                String ONE = scoreOne.getText();
                String TWO = ScoreTwo.getText();
                String THREE = ScoreThree.getText();
                String FOUR = ScoreFour.getText();
                String FIVE = ScoreFive.getText();
                String SIX = ScoreSix.getText();
                if (ID.length()<9 || IDENTIFY.length()<18)
                    new Dataalter(null).setVisible(true);
                if (ID == ""||IDENTIFY==""||NAME ==""||ONE==""||TWO==""||THREE==""||FOUR==""||FIVE==""||SIX=="")
                    new Dataalter(null).setVisible(true);
                float Score1 = Float.parseFloat(ONE);
                float Score2 = Float.parseFloat(TWO);
                float Score3 = Float.parseFloat(THREE);
                float Score4 = Float.parseFloat(FOUR);
                float Score5 = Float.parseFloat(FIVE);
                float Score6 = Float.parseFloat(SIX);
                connect = new Connect();

                String pt="\"";
                float sum = Score1+Score2+Score3+Score4+Score5+Score6;

                    connect.useSQLUpdate("insert student values("+pt+ID+pt+","+pt+IDENTIFY+pt+","+pt+NAME+pt+
                            ","+Score1+","+Score2+","+Score3+","+Score4+","+Score5+","+Score6+")");
                    connect.useSQLUpdate("insert studentscores values("+pt+ID+pt+","+pt+IDENTIFY+pt+","+pt+NAME+pt+
                            ","+sum+")");
                    if (test(Score1,Score2,Score3,Score4,Score5,Score6)){
                        connect.useSQLUpdate("insert enrolls values("+pt+ID+pt+","+pt+IDENTIFY+pt+","+pt+NAME+pt+
                                ","+sum+")");
                    }
                new alterSuccess(null).setVisible(true);

            }
        });
        cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                run(new Testconnection(),500,500);
            }
        });
        setLayout(new GridLayout(10,1));
        this.add(Jid);
        this.add(Jidentify);
        this.add(Jname);
        this.add(Jscoreone);
        add(Jscoretwo);
        add(Jscorethree);
        add(Jscorefour);
        add(Jscorefive);
        add(Jscoresix);
        add(JBT);
    }

    public static void main(String[] args){
        run(new DataAdd(),500,800);
    }
    class Dataalter extends JDialog{
        //提示框
        private static final long serialVersionUID = 1L;
        JLabel label=new JLabel("数据不合法");
        JButton JBT=new JButton("确定");
        JPanel JN=new JPanel();
        JPanel JS=new JPanel();
        JPanel JC=new JPanel();
        public Dataalter(JFrame parent){
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
    class alterSuccess extends JDialog {
        //提示框
        JLabel label = new JLabel("数据添加成功");
        JButton JBT = new JButton("确定");
        JPanel JN = new JPanel();
        JPanel JS = new JPanel();
        JPanel JC = new JPanel();

        public alterSuccess(JFrame parent) {
            super(parent, "警告", true);
            JBT.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            JN.add(label);
            JS.add(JBT);
            this.add(JC, "North");
            this.add(JN, "Center");
            this.add(JS, "South");
            setSize(300, 200);
        }
    }
}
