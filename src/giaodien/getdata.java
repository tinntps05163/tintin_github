/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodien;

import form.connect;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrator
 */
public class getdata {

    ArrayList<connect> ds = new ArrayList<connect>();
    public String servername,database,user,pass;
    public Connection Connect() {
        Connection conn=null;
        try (FileInputStream fis = new FileInputStream("D:\\user.dat"); ObjectInputStream ois = new ObjectInputStream(fis)) {
            ds = (ArrayList<connect>) ois.readObject();
            
            connect cn = ds.get(0);
            String host = "127.0.0.1";
            servername = cn.getServername();
            database = cn.getDatabase();
            user = cn.getUsername();
            pass = cn.getPassword();
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String url = "jdbc:sqlserver://localhost" + "\\" + servername + ":1433;databasename=" + database + ";user=" + user + ";password=" + pass;

            conn = DriverManager.getConnection(url);
            System.out.println("Mo file thanh cong");
        } catch (Exception ex) {
            
        }
        return conn;
    }
}
