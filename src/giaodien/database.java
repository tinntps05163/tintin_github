/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import form.QLSV;
import form.connect;
import giaodien.Home;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Administrator
 */
public class database extends JFrame {

    JPanel jp;
    public JLabel lb1, lb2, lb3, lb4, lbdata;
    public JTextField txtdb, txtsv, txtuser;
    public JPasswordField txtpass;
    public JButton btnconnect;
    public Connection connect = null;
    public Connection conn = null;
    public ArrayList<connect> ds = new ArrayList<connect>();
    public Statement st = null;
    public ResultSet rs = null;
    public String sql;
    public String[] s;
    public Vector vcolumn = new Vector();
    public Vector vrow = new Vector();
    public Vector vserver;
    public String servername = "";
    public String database = "", user = "", pass = "";
    public int check = 0;

    public database() {
//        fr = new JInternalFrame("Connect Database");
        setTitle("Connect Database");
        setResizable(false);
        setSize(400, 370);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        jp = new JPanel();
        add(jp);
        jp.setLayout(null);

        jp.setBounds(10, 10, 372, 320);
        jp.setBackground(new Color(242, 242, 244));
        jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        component();

        setVisible(true);

    }

    public void component() {
        lb1 = new JLabel("Database:");
        lb2 = new JLabel("Server:");
        lb3 = new JLabel("User Name:");
        lb4 = new JLabel("Password:");
        lbdata = new JLabel("Connect Database");
        lbdata.setBounds(2, 30, 180, 40);
        lbdata.setOpaque(true);
        lbdata.setBorder(BorderFactory.createBevelBorder(0, new Color(252, 190, 184), new Color(184, 54, 40)));
        lbdata.setFont(new Font("Segoe Print", Font.BOLD, 18));
        lbdata.setForeground(Color.white);
        lbdata.setBackground(new Color(224, 69, 53));
        jp.add(lbdata);
        txtdb = new JTextField();
        txtsv = new JTextField();
        txtuser = new JTextField();
        txtpass = new JPasswordField();
        btnconnect = new JButton("Connect", new ImageIcon("Images/connect.png"));
        lb1.setBounds(40, 100, 100, 30);
        txtdb.setBounds(120, 100, 200, 30);
        jp.add(lb1);
        jp.add(txtdb);
        lb2.setBounds(55, 140, 100, 30);
        txtsv.setBounds(120, 140, 200, 30);
        jp.add(lb2);
        jp.add(txtsv);
        lb3.setBounds(30, 180, 100, 30);
        txtuser.setBounds(120, 180, 200, 30);
        jp.add(lb3);
        jp.add(txtuser);
        lb4.setBounds(35, 220, 100, 30);
        txtpass.setBounds(120, 220, 200, 30);
        jp.add(lb4);
        jp.add(txtpass);
        btnconnect.setBounds(120, 270, 140, 40);
        btnconnect.setBorder(BorderFactory.createBevelBorder(0, Color.white, new Color(18, 27, 9)));
        btnconnect.setBackground(new Color(115, 163, 65));
        btnconnect.setForeground(Color.white);
        btnconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                conn = getConnectDB();
                if (check == 1) {
                    JOptionPane.showMessageDialog(null, "Khong tim thay Database");
                    check = 0;
                } else if (check == 0) {
                    JOptionPane.showMessageDialog(btnconnect, "Connect thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    connect cn = new connect();
                    cn.setDatabase(database);
                    cn.setServername(servername);
                    cn.setUsername(user);
                    cn.setPassword(pass);
                    ds.add(cn);
                    try (FileOutputStream fos = new FileOutputStream("D:\\user.dat"); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                        oos.writeObject(ds);
                        System.out.println("Save thanh cong");
                    } catch (Exception ex) {
                        System.out.println("Loi save file");
                    }
                    dispose();
                    login lg = new login();
                    lg.setLocationRelativeTo(null);
                }

            }
        });

        jp.add(btnconnect);
    }

    public Connection getConnectDB() {

        try {
            String host = "127.0.0.1";
            servername = txtsv.getText();
            database = txtdb.getText();
            user = txtuser.getText();

            pass = String.valueOf(txtpass.getPassword());
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:sqlserver://localhost" + "\\" + servername + ":1433;databasename=" + database + ";user=" + user + ";password=" + pass;

            connect = DriverManager.getConnection(url);

        } catch (Exception ex) {
            System.out.println(ex);
            check = 1;

        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (Exception ex) {
                }
            }
        }
        return connect;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        database data = new database();

    }
}
