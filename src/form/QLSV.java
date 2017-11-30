/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.toedter.calendar.JDateChooser;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class QLSV extends JFrame {

    JPanel jp = new JPanel();
    JLabel lbtieude, lbidsv, lbmalop, lbname, lbgioitinh, lbtrinhdo, lbngaysinh, lbback;
    JTextField txtidsv, txtmalop, txtname, txtgioitinh, txttrinhdo, txttimkiem;
    JButton btnthem, btnsua, btnxoa, btnluu, btnreset, btnthoat, btntimkiem, btnback;
    JComboBox<String> cbgioitinh, cblop, cbtrinhdo;
    JDateChooser cngaysinh;
    JTable table;
    public String[] s = {"Mã số", "Họ Tên", "Ngày sinh", "Mã KQ", "Giới tính", "Trình độ"};
    public String data[][] = new String[1][6];
    public Connection conn;
    public ArrayList<connect> ds = new ArrayList<connect>();
    public Statement st = null;
    public ResultSet rs = null;
    public int count = 0;
//    database dt = new database();

    public String sql;
    public String servername, database, user, pass;
    public DefaultTableModel model = new DefaultTableModel();
    public String chuoi = "";

    public int index = 0;
    public int dong = 0;

    public QLSV() {

        jp = new JPanel();
        jp.setLayout(null);
        jp.setBounds(10, 10, 834, 700);
        jp.setBackground(new Color(185, 209, 234));
        setLayout(null);
        setResizable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setTitle("Quản lý sinh viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(860, 700);
        add(jp);
        component();
        setVisible(true);
    }

    public void getdatabase() {

        try {

            conn = new getdata().Connect();

//            System.out.println("Connect thanh cong");
        } catch (Exception ex) {
            System.out.println("Error connect database: " + ex);
        }
        try {

            sql = "Select IDSV,MSSV,[Mã Lớp],[Họ Tên],[Ngày Sinh],[Giới Tính],[Trình Độ] from SinhVien";

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

                for (int i = 1; i <= 7; i++) {
                    record.addElement(rs.getString(i));

                }

                model.addRow(record);

            }

        } catch (Exception ex) {
//            System.out.println("Error: " + ex);
        }
    }

    public void component() {

        getdatabase();

        lbtieude = new JLabel("THÔNG TIN SINH VIÊN");
        lbtieude.setBounds(220, 10, 500, 50);
        lbtieude.setFont(new Font("Arial", Font.BOLD, 38));
        lbtieude.setForeground(Color.red);
        jp.add(lbtieude);
        //ID Sinh vien
        lbidsv = new JLabel("Mã SV");
        txtidsv = new JTextField();

        lbidsv.setBounds(50, 70, 50, 30);
        txtidsv.setBounds(100, 70, 90, 30);
        jp.add(lbidsv);
        jp.add(txtidsv);
        //Ho Ten
        lbname = new JLabel("Họ tên");
        lbname.setBounds(280, 70, 60, 30);
        txtname = new JTextField();
        txtname.setBounds(344, 70, 160, 30);
        jp.add(txtname);
        jp.add(lbname);
        //Trinh do
        lbtrinhdo = new JLabel("Trình độ");
        lbtrinhdo.setBounds(624, 70, 60, 30);
        cbtrinhdo = new JComboBox<String>();
        cbtrinhdo.addItem("Đại Học");
        cbtrinhdo.addItem("Cao Đẳng");
        cbtrinhdo.addItem("Trung Cấp");
        cbtrinhdo.setBounds(684, 70, 100, 30);
        jp.add(lbtrinhdo);
        jp.add(cbtrinhdo);
        //ID Ket Qua
        lbmalop = new JLabel("Mã Lớp");
        lbmalop.setBounds(50, 110, 60, 30);
        cblop = new JComboBox<String>();
        cblop.addItem("PT12201");
        cblop.addItem("PT12202");
        cblop.addItem("PT12203");
        cblop.setBounds(100, 110, 90, 30);
        jp.add(lbmalop);
        jp.add(cblop);
        //Ngay sinh
        lbngaysinh = new JLabel("Ngày sinh");
        lbngaysinh.setBounds(280, 110, 60, 30);
        cngaysinh = new JDateChooser();
        cngaysinh.setBounds(344, 110, 160, 30);
        cngaysinh.setDateFormatString("MM/dd/yyyy");
        jp.add(cngaysinh);
        jp.add(lbngaysinh);
        //Gioi tinh

        lbgioitinh = new JLabel("Gioi Tinh");
        lbgioitinh.setBounds(624, 110, 60, 30);
        cbgioitinh = new JComboBox<String>();
        cbgioitinh.addItem("Nam");
        cbgioitinh.addItem("Nữ");

        cbgioitinh.setBounds(684, 110, 100, 30);
        jp.add(lbgioitinh);
        jp.add(cbgioitinh);

        //--------
        //Table xuat du lieu
        table = new JTable();
//        table.setBounds(20, 160, 794, 320);
        table.setRowHeight(50);
        table.setModel(model);
//        table.setEnabled(false);
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getSource() == table) {
                    int so = table.getSelectedRow();
                    SimpleDateFormat dfm = new SimpleDateFormat("dd/MM/yyyy");
                    Date d1 = new Date(String.valueOf(table.getValueAt(so, 4)));

                    if (so != -1) {
//                        d = (Date) table.getValueAt(so, 3);
                        txtidsv.setText(String.valueOf(table.getValueAt(so, 1)));
                        cblop.setSelectedItem(String.valueOf(table.getValueAt(so, 2)));

                        cngaysinh.setDate(d1);
                        txtname.setText(String.valueOf(table.getValueAt(so, 3)));
                        cbtrinhdo.setSelectedItem(String.valueOf(table.getValueAt(so, 6)));

                        cbgioitinh.setSelectedItem(String.valueOf(table.getValueAt(so, 5)));
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

//        jp.add(table);
        JScrollPane jsp = new JScrollPane(table);

        jsp.setBounds(20, 220, 794, 320);

        jp.add(jsp);
        //Tim kiem
        txttimkiem = new JTextField();
        txttimkiem.setBounds(340, 160,120, 30);
        btntimkiem = new JButton("Search");
        btntimkiem.setBounds(460, 160, 70, 30);
        jp.add(btntimkiem);
        btntimkiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                count = 1;
                int sodong = table.getRowCount();
                ArrayList<Integer> ds = new ArrayList<>();
                String mssv1, mssv2;
                int dem = 0;
                mssv1 = txttimkiem.getText();
                if (mssv1.length() > 0) {
                    try {
                        while (sodong > 0) {

                            Vector vdong = new Vector();

                            mssv2 = String.valueOf(table.getValueAt(sodong - 1, 1));
                            sodong--;
                            if (mssv2.equalsIgnoreCase(mssv1)) {
                                dem = 1;
                                continue;
                            }
                            ds.add(sodong);

                        }
                        if (dem == 1) {
                            JOptionPane.showMessageDialog(jp, "Tim thay");
                            for (int i = 0; i < ds.size(); i++) {

                                model.removeRow(ds.get(i));
                            }
                            table.setModel(model);

                        }

                    } catch (Exception ex) {
                    }
                } else {
                    JOptionPane.showMessageDialog(jp, "Ban chua nhap MSSV can tim!");
                    txttimkiem.requestFocus();
                }
            }
        });
        jp.add(txttimkiem);
        //Back
        btnback = new JButton("Back",new ImageIcon("Images/Back.png"));
        btnback.setBounds(690,580,120,50);
        btnback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (count == 1) {
                    model.removeRow(0);
                    txttimkiem.setText("");
                    txtname.setText("");
                    txtidsv.setText("");

                    cngaysinh.setDate(new Date());
                    count = 0;
                    try {
                        sql = "Select IDSV,MSSV,[Mã Lớp],[Họ Tên],[Ngày Sinh],[Giới Tính],[Trình Độ] from SinhVien";
                        st = conn.createStatement();
                        rs = st.executeQuery(sql);
                        while (rs.next()) {
                            Vector record = new Vector();
                            for (int i = 1; i <= 7; i++) {
                                record.addElement(rs.getString(i));
                            }
                            model.addRow(record);
                            table.setModel(model);
                        }
                    } catch (Exception ex) {
                    }
                }
            }
        });
        jp.add(btnback);
        //------
        //Button Them
        btnthem = new JButton("Thêm", new ImageIcon("Images/update.png"));
        btnthem.setBounds(20, 580, 120, 50);
        btnthem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dong = table.getSelectedRow();
                try {
                    if (dong == -1) {
                        JOptionPane.showMessageDialog(jp, "ban chua tuong tac voi bang");
                    }

                    if (dong >= 0) {
                        int sodong = table.getRowCount();
                        SimpleDateFormat dfm = new SimpleDateFormat("MM/dd/yyyy");
                        Date d = new Date();
                        String mssv, name, gioitinh, trinhdo, ngaysinh, idlop, stt, mssvgoc;
                        mssv = txtidsv.getText();
                        name = txtname.getText();
                        idlop = (String) cblop.getSelectedItem();
                        gioitinh = (String) cbgioitinh.getSelectedItem();
                        trinhdo = (String) cbtrinhdo.getSelectedItem();
                        mssvgoc = String.valueOf(table.getValueAt(dong, 1));
                        stt = String.valueOf(table.getValueAt(sodong - 1, 0));
                        int maso = Integer.parseInt(stt) + 1;
                        String id = String.valueOf(maso);
                        int check = 0;
                        for (int i = 0; i < sodong; i++) {

                            if (txtidsv.getText().equalsIgnoreCase(String.valueOf(table.getValueAt(i, 1)))) {
                                check = 1;
                                break;
                            }
                        }
                        if (check == 1) {
                            JOptionPane.showMessageDialog(jp, "MSSV này đã tồn tại!");
                            txtidsv.setText("");
                        }

                        if (check == 0) {
                            if (mssv.length() > 0 && name.length() > 0) {
//                            System.out.println(id);
                                Vector vdong = new Vector();
                                vdong.addElement(String.valueOf(maso));
                                vdong.addElement(txtidsv.getText());
                                vdong.addElement(cblop.getSelectedItem());
                                vdong.addElement(txtname.getText());
                                vdong.addElement(cngaysinh.getDate());
                                vdong.addElement(cbgioitinh.getSelectedItem());
                                vdong.addElement(cbtrinhdo.getSelectedItem());

                                model.addRow(vdong);
                                table.setModel(model);
                                sodong = table.getRowCount();
                                d = (Date) table.getValueAt(sodong - 1, 4);
                                table.setValueAt(dfm.format(d), sodong - 1, 4);
                                ngaysinh = String.valueOf(table.getValueAt(sodong - 1, 4));

                                sql = "Insert into SinhVien values ('" + id + "','" + mssv + "',N'" + name + "','"
                                        + ngaysinh + "','" + String.valueOf(maso) + "','" + idlop + "',N'" + gioitinh + "',N'" + trinhdo + "')";
                                rs = st.executeQuery(sql);
                            } else {
                                JOptionPane.showMessageDialog(jp, "Vui long nhap day du thong tin");
                            }
                        }
                    }
                } catch (Exception ex) {
//                    System.out.println(ex);
                }

            }
        }
        );
        jp.add(btnthem);
        //Button Sua
        btnsua = new JButton("Sửa", new ImageIcon("Images/repair.png"));

        btnsua.setBounds(160, 580, 120, 50);
        btnsua.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                dong = table.getSelectedRow();
                int sodong = table.getRowCount();
                if (dong == -1) {
                    JOptionPane.showMessageDialog(jp, "Bạn chưa chọn dòng cần sửa thông tin!");
                } else {
                    SimpleDateFormat dfm = new SimpleDateFormat("MM/dd/yyyy");
                    Date d = new Date();
                    String idsv, name, ngaysinh, idlop, gioitinh, trinhdo, mssv, mssv2, mssvgoc;
                    mssv = txtidsv.getText();
                    mssvgoc = String.valueOf(table.getValueAt(dong, 1));
                    idlop = (String) cblop.getSelectedItem();
                    idsv = String.valueOf(table.getValueAt(dong, 0));
                    name = txtname.getText();
                    gioitinh = String.valueOf(cbgioitinh.getSelectedItem());
                    trinhdo = (String) cbtrinhdo.getSelectedItem();
                    d = cngaysinh.getDate();
//                    System.out.println(dong);
                    try {
                        int check = 0;
                        for (int i = 0; i < sodong; i++) {
                            mssv2 = String.valueOf(table.getValueAt(i, 1));
                            if (txtidsv.getText().equalsIgnoreCase(String.valueOf(table.getValueAt(dong, 1)))) {
                                check = 0;
                                break;
                            }
                            if (txtidsv.getText().equalsIgnoreCase(String.valueOf(table.getValueAt(i, 1)))) {
                                check = 1;
                                break;
                            } else {

                                check = 0;

                            }
                        }
                        if (check == 1) {
                            JOptionPane.showMessageDialog(jp, "MSSV này đã tồn tại!");
                            txtidsv.setText(mssvgoc);
                        }

                        if (check == 0) {
                            table.setValueAt(mssv, dong, 1);
                            table.setValueAt(name, dong, 3);
                            table.setValueAt(dfm.format(d), dong, 4);
                            table.setValueAt(idlop, dong, 2);
                            table.setValueAt(gioitinh, dong, 5);
                            table.setValueAt(trinhdo, dong, 6);
                            ngaysinh = String.valueOf(table.getValueAt(dong, 4));
                            rs = null;
                            st = conn.createStatement();
                            sql = "Update SinhVien set MSSV='" + mssv + "',[Mã Lớp]='" + idlop + "',[Ngày Sinh]='" + ngaysinh + "', [Họ Tên]=N'" + name + "',[Giới Tính]=N'" + gioitinh + "',[Trình Độ]=N'" + trinhdo + "' where IDSV='" + idsv + "'";
                            rs = st.executeQuery(sql);
                        }
                    } catch (Exception ex) {
//                        System.out.println(ex);
                    }

                }
            }
        }
        );
        jp.add(btnsua);
        //Button Xoa
        btnxoa = new JButton("Xóa", new ImageIcon("Images/clear.png"));

        btnxoa.setBounds(290, 580, 120, 50);
        btnxoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                dong = table.getSelectedRow();
                try {
                    if (table.getSelectedRow() == -1) {
                        JOptionPane.showMessageDialog(jp, "Bạn chưa chọn dòng cần xóa!");
                    } else {
                        String gt = String.valueOf(model.getValueAt(dong, 0));

//                        System.out.println(dong);
                        model.removeRow(dong);
                        table.setModel(model);
                        sql = "Delete from SinhVien where IDSV='" + gt + "'";

                        rs = conn.createStatement().executeQuery(sql);

                    }

                } catch (Exception ex) {

                }
            }
        }
        );
        jp.add(btnxoa);
        //Button Luu

        //Button Reset
        btnreset = new JButton("Hủy");

        btnreset.setBounds(420, 580, 120, 50);
        btnreset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                txtname.setText("");
                txtidsv.setText("");
                
                cngaysinh.setDate(new Date());

            }
        }
        );

        jp.add(btnreset);
        //Button Thoat
        btnthoat = new JButton("Thoát",new ImageIcon("Images/Exit2.png"));

        btnthoat.setBounds(560, 580, 120, 50);
        btnthoat.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                dispose();
            }
        }
        );
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
        QLSV ql = new QLSV();
    }
}
