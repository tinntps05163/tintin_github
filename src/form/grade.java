/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import giaodien.getdata;
import giaodien.login;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrator
 */
public class grade extends JFrame {

    JPanel jp;
    public JTextField txtidsv, txtidgv, txtidkq, txtdiemta, txtdiemth, txtdiemtc, txtdiemtb;
    public JLabel lbidsv, lbidgv, lbidkq, lbdiemta, lbdiemth, lbdiemtc, lbdiemtb, lbtieude;
    public JButton btnhuy, btnupdate, btnthoat;
    public Statement st = null;
    public ResultSet rs = null;
    public Connection conn = null;
    public String sql;
    public DefaultTableModel model = new DefaultTableModel();
    public JTable table;
    public double diemta, diemth, diemtc, diemtb;
    public int dong;
    public JScrollPane jsp;
    public double[] tb;
    public int dem = 0;
    public double t;

    public grade() {
        jp = new JPanel();
        jp.setLayout(null);
        jp.setBounds(10, 10, 854, 600);
        jp.setBackground(new Color(185, 209, 234));
        setLayout(null);
        setResizable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setTitle("Quản lý sinh viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 600);
        add(jp);
        component();
        setVisible(true);
    }

    public void getdatabase() {

        try {

            conn = new getdata().Connect();

            System.out.println("Connect thanh cong");
        } catch (Exception ex) {
            System.out.println("Error connect database: " + ex);
        }
        try {
            sql = "Select MSSV,IDGV,[Tiếng Anh],[Tin Học],[GDTC] from Ket_Qua";

            st = conn.createStatement();
            rs = st.executeQuery(sql);
            ResultSetMetaData rsmeta = rs.getMetaData();
            int socot = rsmeta.getColumnCount();
            Vector vcot = new Vector();
            for (int i = 1; i <= socot; i++) {
                vcot.addElement(rsmeta.getColumnName(i));
            }
            model.setColumnIdentifiers(vcot);

            Vector vdong = new Vector();
            while (rs.next()) {
                Vector record = new Vector();
                for (int i = 1; i <= 5; i++) {
                    record.addElement(rs.getString(i));

                }
                model.addRow(record);
            }

        } catch (Exception ex) {
//            System.out.println("Error: " + ex);
        }
    }
//  
    public boolean isCellEditable(int dong, int cot){
        if(cot==1){
        return false;
        }
        return true;
    }
         public void component() {
        getdatabase();
        lbtieude = new JLabel("BẢNG ĐIỂM SINH VIÊN");
        lbtieude.setBounds(220, 10, 500, 50);
        lbtieude.setFont(new Font("Arial", Font.BOLD, 38));
        lbtieude.setForeground(Color.red);
        jp.add(lbtieude);
        lbidsv = new JLabel("MSSV");
        lbidsv.setBounds(50, 90, 50, 30);

        jp.add(lbidsv);
        txtidsv = new JTextField();
        txtidsv.setBounds(100, 90, 70, 30);

        txtidsv.setEditable(false);
        jp.add(txtidsv);
        //IDGV
        lbidgv = new JLabel("MSGV");
        lbidgv.setBounds(180, 90, 50, 30);
        
        jp.add(lbidgv);
        txtidgv = new JTextField();
        txtidgv.setBounds(230, 90, 60, 30);
        txtidgv.setEditable(false);
        jp.add(txtidgv);
        //Diem TA
        lbdiemta = new JLabel("Tiếng Anh");
        lbdiemta.setBounds(310, 90, 60, 30);

        jp.add(lbdiemta);
        txtdiemta = new JTextField();
        txtdiemta.setBounds(370, 90, 50, 30);
        jp.add(txtdiemta);
        //Diem TH
        lbdiemth = new JLabel("Tin học");
        lbdiemth.setBounds(440, 90, 50, 30);
        jp.add(lbdiemth);
        txtdiemth = new JTextField();
        txtdiemth.setBounds(490, 90, 50, 30);
        jp.add(txtdiemth);
        //Diem GDTC
        lbdiemtc = new JLabel("Thể chất");
        lbdiemtc.setBounds(560, 90, 55, 30);
        jp.add(lbdiemtc);
        txtdiemtc = new JTextField();
        txtdiemtc.setBounds(615, 90, 50, 30);
        jp.add(txtdiemtc);
        //Diem TB
        lbdiemtb = new JLabel("Điểm TB");
        lbdiemtb.setBounds(685, 90, 60, 30);
        jp.add(lbdiemtb);
        txtdiemtb = new JTextField();
        txtdiemtb.setBounds(745, 90, 50, 30);
        jp.add(txtdiemtb);
        //Tao JTable
        table = new JTable();
        
        table.setModel(model);
        table.setRowHeight(50);
        
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getSource() == table) {
                    int so = table.getSelectedRow();
                    if (so != -1) {
//                        table.setEnabled(false);
                        txtidsv.setText(String.valueOf(table.getValueAt(so, 0)));
                        txtidgv.setText(String.valueOf(table.getValueAt(so, 1)));
                        txtdiemta.setText(String.valueOf(table.getValueAt(so, 2)));
                        txtdiemth.setText(String.valueOf(table.getValueAt(so, 3)));
                        txtdiemtc.setText(String.valueOf(table.getValueAt(so, 4)));
                        diemta = (Double.parseDouble(String.valueOf(table.getValueAt(so, 2))));
                        diemth = (Double.parseDouble(String.valueOf(table.getValueAt(so, 3))));
                        diemtc = (Double.parseDouble(String.valueOf(table.getValueAt(so, 4))));
                        diemtb = (double) Math.round(((diemta + diemth + diemtc) / 3) * 10) / 10;
                        txtdiemtb.setText(String.valueOf(diemtb));
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });
        // Tao JScrollPane
        jsp = new JScrollPane(table);

        jsp.setBounds(20, 160, 810, 320);
        jp.add(jsp);
        //Tao Button Update
        btnupdate = new JButton("Sửa", new ImageIcon("Images/update.png"));
        btnupdate.setBounds(160, 500, 120, 50);
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dong = table.getSelectedRow();
                String idsv1, idgv1, idsv2, idgv2;
                String idkq;
                double diemta, diemth, diemtc, diemtb;
                idsv1 = txtidsv.getText();
                idsv2 = String.valueOf(table.getValueAt(dong, 0));

                idgv1 = txtidgv.getText();
                idgv2 = String.valueOf(table.getValueAt(dong, 1));
                try {
                    if (dong < 0) {
                        JOptionPane.showMessageDialog(jp, "Bạn chưa chọn dòng cần sửa điểm!");
                    } else if (txtidsv.getText().equals("") || txtidgv.getText().equals("") || txtdiemta.getText().equals("") || txtdiemth.getText().equals("") || txtdiemtc.getText().equals("")) {
                        JOptionPane.showMessageDialog(jp, "Bạn chưa nhập dữ liệu thay thế!");
                    } else if ((idsv1.equalsIgnoreCase(idsv2)) && (idgv1.equalsIgnoreCase(idgv2))) {

                        diemta = Double.parseDouble(txtdiemta.getText());

                        diemth = Double.parseDouble(txtdiemth.getText());
                        diemtc = Double.parseDouble(txtdiemtc.getText());
                        diemtb = (double) Math.round(((diemta + diemth + diemtc) / 3) * 10) / 10;

                        if ((diemta < 0 || diemta > 10) || (diemth < 0 || diemth > 10) || (diemtc < 0 || diemtc > 10)) {
                            JOptionPane.showMessageDialog(jp, "Diem nam trong khoang 0->10");
                            txtdiemta.setText("");
                            txtdiemtc.setText("");
                            txtdiemth.setText("");
                        } else {
                            ResultSet rs = null;
                            Statement st = null;

                            idkq = String.valueOf(table.getValueAt(dong, 0));
                            try {
                                table.setValueAt(diemta, dong, 2);
                                table.setValueAt(diemth, dong, 3);
                                table.setValueAt(diemtc, dong, 4);
                                txtdiemtb.setText(String.valueOf(diemtb));
                                st = conn.createStatement();
                                sql = "Update Ket_Qua set [Tiếng Anh]='" + diemta + "', [Tin học]='" + diemth + "', GDTC='" + diemtc + "' where MSSV='" + idsv2 + "'";
                                rs = st.executeQuery(sql);

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(jp, "Đã cập nhật điểm mới");
//                            dispose();
//                            new grade();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(jp, "Bạn chỉ được sửa điểm!");
                        txtidsv.setText(idsv2);
                        txtidgv.setText(idgv2);
                    }
                } catch (Exception ex) {
                    System.out.println("Error:" + ex);
                }

            }
        });
        jp.add(btnupdate);
        //Tao button huy
        btnhuy = new JButton("Hủy", new ImageIcon("Images/clear.png"));
        btnhuy.setBounds(340, 500, 120, 50);
        btnhuy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                txtdiemta.setText("");
                txtdiemth.setText("");
                txtdiemtc.setText("");
                txtdiemtb.setText("");
            }
        });
        jp.add(btnhuy);
        //Tao button Thoat
        btnthoat = new JButton("Thoát", new ImageIcon("Images/Exit2.png"));
        btnthoat.setBounds(520, 500, 120, 50);
        btnthoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        jp.add(btnthoat);
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
        grade gr = new grade();
    }
}
