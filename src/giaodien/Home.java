/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import form.QLSV;
import form.grade;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Administrator
 */
public class Home extends JFrame {

    JButton btnconnect;
    JPanel jp;
    JDesktopPane desk;
    public JLabel lb1, lbstatus, lbchucvu, lbname, lbuser, lbcv, lbtt;
    public JMenuBar menubar;
    public JMenu menu, menuql;
    public JMenuItem menuitem, menuitemgv, menuitemsv, menuitemlogin, menuitemlogout, menuitemdiem;
    public JButton btnlogout, btnlogin;
    public JToolBar toolbar;
    public int dem = 0;
    public JPopupMenu popupmenu;

    public Home() {
        setTitle("Trang chủ");

        this.setLayout(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 740);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(createpanel());
        this.setJMenuBar(createmenubar());
        jp.add(createtoolbar(), BorderLayout.PAGE_START);
        createpopup();
//        createmenubar();
        this.setVisible(true);

    }
//Maximize

    private void maximize() {
        if (dem == 0) {
            setExtendedState(MAXIMIZED_BOTH | getExtendedState());
            dem = 1;
        } else if (dem == 1) {
            dem = 0;
            setSize(900, 700);
            setLocationRelativeTo(null);
        }
    }
//Minimize

    private void minimize() {
        setExtendedState(getExtendedState() | ICONIFIED);
    }

    private JPanel createpanel() {
        jp = new JPanel();
        jp.setLayout(new BorderLayout());
//        jp.setSize(450, 450);
        jp.setBackground(new Color(227, 239, 255));

        return jp;
    }

    //Popup 
    private void createpopup() {
        popupmenu = new JPopupMenu();
        //Minimize
        menuitem = new JMenuItem("Minimize", new ImageIcon("Images/Minimize2.png"));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                minimize();
            }
        });

        popupmenu.add(menuitem);
        //Maximize
        menuitem = new JMenuItem("Maximize", new ImageIcon("Images/Maximize2.png"));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                maximize();
                if (dem == 1) {

                    lbuser.setBounds(740, 0, 60, 32);
                    lbname.setBounds(800, 0, 160, 32);
                    lbcv.setBounds(980, 0, 60, 32);

                    lbchucvu.setBounds(1040, 0, 120, 32);
                    lbtt.setBounds(1200, 0, 70, 32);
                    lbstatus.setBounds(1270, 0, 60, 32);
                } else {

                    lbuser.setBounds(420, 0, 60, 32);
                    lbname.setBounds(480, 0, 160, 32);
                    lbcv.setBounds(600, 0, 60, 32);

                    lbchucvu.setBounds(660, 0, 120, 32);
                    lbtt.setBounds(770, 0, 70, 32);
                    lbstatus.setBounds(840, 0, 60, 32);
                }
            }
        });

        popupmenu.add(menuitem);
        //Close
        popupmenu.addSeparator();
        menuitem = new JMenuItem("Close", new ImageIcon("Images/Close.png"));
        menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        popupmenu.add(menuitem);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON3) {
                    popupmenu.show(me.getComponent(), me.getX(), me.getY());
                }
            }

        });

    }
//Tao Jtoolbar

    private JToolBar createtoolbar() {
        toolbar = new JToolBar();
        //Logout
        btnlogout = new JButton(new ImageIcon("Images/logout.png"));
        btnlogout.setBounds(0, 0, 40, 32);
        btnlogout.setBackground(new ColorUIResource(250, 250, 250));
        btnlogout.setBorder(BorderFactory.createEmptyBorder());
        btnlogout.setToolTipText("Logout");
        btnlogout.setEnabled(false);
        toolbar.add(btnlogout);
        btnlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbstatus.setText("Offline");
                lbchucvu.setText("");
                lbname.setText("");
                menuql.setEnabled(false);
                menuitemlogin.setEnabled(true);
            }
        });

        //-------Logout
        //-------Login
        toolbar.addSeparator(new Dimension(10, 32));
        btnlogin = new JButton(new ImageIcon("Images/Enter.png"));

        btnlogin.setBackground(new ColorUIResource(251, 251, 251));
        btnlogin.setBounds(40, 0, 40, 32);

        btnlogin.setBorder(BorderFactory.createEmptyBorder());
        btnlogin.setToolTipText("Login");
        btnlogin.setEnabled(false);
        toolbar.add(btnlogin);
        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    login l=new login();
                    l.setLocationRelativeTo(null);
                    jp.add(l);

                } catch (Exception ex) {
                    dispose();
                }
            }
        });
        //-------Login
        toolbar.addSeparator(new Dimension(10, 32));
        btnconnect = new JButton(new ImageIcon("Images/connect.png"));
        btnconnect.setBackground(new ColorUIResource(250, 250, 250));
        btnconnect.setBounds(80, 0, 40, 32);
        btnconnect.setBackground(new Color(238, 238, 238));
        btnconnect.setBorder(BorderFactory.createEmptyBorder());
        btnconnect.setToolTipText("Connect Database");

        toolbar.add(btnconnect);

        btnconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    giaodien.database d = new giaodien.database();
                    d.setLocationRelativeTo(null);
                    jp.add(d);

                } catch (Exception ex) {
                    dispose();
                }
            }
        });
        return toolbar;
    }
//Tao Jmenubar

    private JMenuBar createmenubar() {

        lbchucvu = new JLabel();
        lbchucvu.setBounds(660, 0, 150, 32);
        lbchucvu.setForeground(new Color(220, 35, 40));

        lbstatus = new JLabel();
        lbstatus.setBounds(840, 0, 60, 32);
        lbstatus.setForeground(Color.red);
        //Create Menubar

        menubar = new JMenuBar();
        menubar.setLayout(null);
        menubar.setPreferredSize(new Dimension(900, 32));
        menubar.setBackground(new Color(238, 234, 234));
        menubar.add(lbstatus);
        menubar.add(lbchucvu);
        lbuser = new JLabel("Tên User:");

        lbuser.setBounds(420, 0, 60, 32);
        menubar.add(lbuser);
        //
        lbname = new JLabel();

        lbname.setBounds(480, 0, 160, 32);
        lbname.setForeground(new Color(220, 35, 40));
        menubar.add(lbname);
        //Menu chuc nang
        menu = new JMenu("Chức năng");
        menu.setBorder(BorderFactory.createEmptyBorder());
        menu.setBounds(4, 0, 70, 32);
        menuitemlogin = new JMenuItem("Login", new ImageIcon("Images/Enter.png"));
        menuitemlogin.setEnabled(false);
        menuitemlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    login l=new login();
                    l.setLocationRelativeTo(null);
                    jp.add(l);
                    dispose();
                } catch (Exception ex) {

                }
            }
        });
        menu.add(menuitemlogin);
        menuitemlogout = new JMenuItem("Logout", new ImageIcon("Images/logout.png"));
        menuitemlogout.setEnabled(false);
        menuitemlogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                lbstatus.setText("Offline");
                lbchucvu.setText("");
                lbname.setText("");
                menuql.setEnabled(false);
                menuitemlogin.setEnabled(true);
            }
        });
        menu.add(menuitemlogout);
        menubar.add(menu);
        menu.addSeparator();
        menuitem = new JMenuItem("Minimize", new ImageIcon("Images/Minimize.png"));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                minimize();
            }
        });
        menu.add(menuitem);
        menuitem = new JMenuItem("Maximize", new ImageIcon("Images/Maximize.png"));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                maximize();
                if (dem == 1) {

                    lbuser.setBounds(740, 0, 60, 32);
                    lbname.setBounds(800, 0, 160, 32);
                    lbcv.setBounds(980, 0, 60, 32);

                    lbchucvu.setBounds(1040, 0, 120, 32);
                    lbtt.setBounds(1200, 0, 70, 32);
                    lbstatus.setBounds(1270, 0, 60, 32);
                } else {

                    lbuser.setBounds(420, 0, 60, 32);
                    lbname.setBounds(480, 0, 160, 32);
                    lbcv.setBounds(600, 0, 60, 32);

                    lbchucvu.setBounds(660, 0, 120, 32);
                    lbtt.setBounds(770, 0, 70, 32);
                    lbstatus.setBounds(840, 0, 60, 32);
                }
            }
        });

        menu.add(menuitem);
        menu.addSeparator();
        menuitem = new JMenuItem("Exit", new ImageIcon("Images/Exit.png"));
        menuitem.setMnemonic(KeyEvent.VK_E);
        menuitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        menuitem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        menu.add(menuitem);
        //First Menu
        menuql = new JMenu("Quản lý");
        menuql.setEnabled(false);
        menuql.setBorder(BorderFactory.createEmptyBorder());

        menuql.setBounds(80, 0, 60, 32);
        menuitemsv = new JMenuItem("Sinh viên");
        menuitemsv.setEnabled(false);
        menuitemsv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    QLSV ql = new QLSV();
                    ql.setLocationRelativeTo(null);
                    jp.add(ql);
                } catch (Exception ex) {
                }
            }
        });

        menuql.add(menuitemsv);

        menuql.addSeparator();
        //Giang vien
        menuitemgv = new JMenuItem("Giảng viên");
        menuitemgv.setEnabled(false);
        menuql.add(menuitemgv);
        menubar.add(menuql);
        //Diem
        menuitemdiem = new JMenuItem("Điểm");
        menuitemdiem.setEnabled(false);
        menuitemdiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new grade();
            }
        });
        menuql.add(menuitemdiem);
        menubar.add(menuql);
        //
        lbtt = new JLabel("Trạng thái:");
        lbtt.setBounds(770, 0, 70, 32);

        menubar.add(lbtt);
        lbcv = new JLabel("Chức vụ:");

        lbcv.setBounds(600, 0, 60, 32);
        menubar.add(lbcv);

        return menubar;
    }

    public static void main(String[] args) {

        Home t = new Home();

    }
}
