/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;


import giaodien.Home;
import giaodien.getdata;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class login extends JFrame {

    JPanel jp;
    private JLabel lbname, lbpass, lbcheck, lblogin, lbavatar;
    private JTextField txtid;
    private JCheckBox chkpass;
    private JButton btnlogin;
    private JRadioButton rdogv, rdocb;
    private ButtonGroup btng;
    public int dem = 0;
    private JPasswordField txtpass;
    String sname = "", spass = "";
    private DefaultTableModel model = new DefaultTableModel();

    public login() {
        setTitle("LOGIN");
        this.setResizable(false);
        btng = new ButtonGroup();
        lbname = new JLabel("ID User:");
        lbpass = new JLabel("Password:");
        lbcheck = new JLabel("Check:");
        lblogin = new JLabel("User Login");
        lbavatar = new JLabel(new ImageIcon("Images/user.png"));
        txtid = new JTextField();
        txtpass = new JPasswordField();

        chkpass = new JCheckBox("Show Password");
        btnlogin = new JButton("Login");
        rdogv = new JRadioButton("Giảng viên");
        rdogv.setSelected(true);
        rdocb = new JRadioButton("Cán bộ");
        btng.add(rdogv);
        btng.add(rdocb);
        this.setLayout(null);
        this.setSize(400, 350);
        this.setBackground(new Color(102, 203, 234));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jp = new JPanel();
        jp.setLayout(null);
        this.add(jp);
        jp.setBounds(10, 10, 372, 300);
        jp.setBackground(new Color(227, 239, 255));
        jp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        component();

        this.setVisible(true);
    }

    private void component() {
        lbname.setBounds(10, 100, 80, 30);

        jp.add(lbname);
        lbpass.setBounds(10, 140, 80, 30);
        jp.add(lbpass);
        lbcheck.setBounds(10, 200, 80, 30);
        jp.add(lbcheck);
        lblogin.setBounds(2, 20, 120, 40);
        lblogin.setBorder(BorderFactory.createBevelBorder(0, new Color(251, 171, 146), new Color(237, 27, 36)));

        lblogin.setForeground(new Color(252, 255, 255));
        lblogin.setBackground(new Color(241, 89, 50));
        lblogin.setFont(new Font("Segoe Print", Font.BOLD, 18));

        lblogin.setOpaque(true);
        jp.add(lblogin);
        lbavatar.setBounds(276, 0, 96, 96);
        jp.add(lbavatar);
        txtid.setBounds(100, 95, 180, 30);

        sname = txtid.getText();

        jp.add(txtid);
        txtpass.setBounds(100, 140, 180, 30);
        txtpass.setEchoChar('*');

        jp.add(txtpass);
        chkpass.setBounds(100, 170, 140, 30);

        chkpass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chkpass.isSelected()) {
                    txtpass.setEchoChar((char) 0);

                } else {
                    txtpass.setEchoChar('*');
                }

            }
        });

        jp.add(chkpass);
        rdogv.setBounds(100, 200, 100, 30);
        jp.add(rdogv);
        rdocb.setBounds(210, 200, 100, 30);
        jp.add(rdocb);
        btnlogin.setBounds(120, 240, 100, 30);
        btnlogin.setBorder(BorderFactory.createBevelBorder(0, Color.white, new Color(168, 169, 173)));
        btnlogin.setBackground(new Color(110, 179, 63));
        btnlogin.setForeground(Color.white);
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    getdata dt = new getdata();
                    Connection conn = dt.Connect();
                    String id, pass;
                    id = txtid.getText();
                    pass = String.valueOf(txtpass.getPassword());
                    Statement st = conn.createStatement();
                    ResultSet rs;
                    System.out.println(id);
                    System.out.println(pass);
                     

//                    t.dispose();
                    if (rdogv.isSelected()) {
                        DefaultTableModel model = new DefaultTableModel();
                        String sql = "Select * from Login where ID='" + id + "' and Password='" + pass + "' and Chucvu like N'%G%'";
                        Vector vdong = new Vector();
                        rs = st.executeQuery(sql);

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(btnlogin, "Login thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

                            Home t = new Home();
                            t.lbchucvu.setText("Giảng viên");
                            t.menuitemdiem.setEnabled(true);
                            t.btnlogin.setEnabled(true);
                            t.btnlogout.setEnabled(true);
                            t.lbname.setText(String.valueOf(rs.getObject(2)));
                            t.menuql.setEnabled(true);
                            t.menuitemlogout.setEnabled(true);
                            t.lbstatus.setText("Online");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(btnlogin, "Login không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtid.setText("");
                            txtpass.setText("");
                            txtid.requestFocus();
                        }

                    }
                    if (rdocb.isSelected()) {
                        String sql = "Select * from Login where ID='" + id + "' and Password='" + pass + "' and chucvu like N'%C%'";
                        rs = st.executeQuery(sql);

                        if (rs.next()) {



                            JOptionPane.showMessageDialog(btnlogin, "Login thành công", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                            Home t = new Home();
                            t.lbchucvu.setText("Cán bộ đào tạo");
                            t.menuitemsv.setEnabled(true);
                            t.menuitemgv.setEnabled(true);
                            t.btnlogin.setEnabled(true);
                            t.btnlogout.setEnabled(true);
                            t.lbname.setText(String.valueOf(rs.getObject(2)));
                            t.menuql.setEnabled(true);
                            t.menuitemlogout.setEnabled(true);
                            t.lbstatus.setText("Online");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(btnlogin, "Login không thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                            txtid.setText("");
                            txtpass.setText("");
                            txtid.requestFocus();
                        }
                    }

//                    t.dispose();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }

        });
        jp.add(btnlogin);
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
        login lg = new login();
    }
}
