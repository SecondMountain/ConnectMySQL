package MySQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static MySQL.OftenUse.run;

/**
 * Created by zyf on 2016/6/3.
 */
public class AdminLogin extends JFrame{
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
    public AdminLogin() {
        logon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getid = forid.getText().toString().trim();
                getps = forpw.getText().toString().trim();
                if (getid.equals("") || getps.equals("") || !getid.equals(getps))
                {
                    new alter(null).setVisible(true);
                    forid.setText("");
                    forpw.setText("");
                    return;
                }
                else {
                    if (getid.equals("admin")){
                        dispose();
                        run(new Testconnection(),500,500);
                    }

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
        run(new AdminLogin(),500,500);

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
}
