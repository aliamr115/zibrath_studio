    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zibrathstudio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class koneksi {
    public static Connection mysqlconfig;
    
    public static Connection konek(){
        try {
        String url = "jdbc:mysql://localhost:3306/zibrath_studio";
        String user = "root";
        String pass = "";
        
        mysqlconfig = DriverManager.getConnection(url, user, pass);
        
    } catch (SQLException sQLException){
            System.out.println(sQLException.getMessage());
    }
    
        return mysqlconfig;
}
}
    

