/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win10
 */
public class ClsConexion {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/db_vendedores?zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PWD = "progra1";
    
    
    //CREAR METODO CONEXION
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PWD);
    }
    
    public static void close(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void close(Connection cn){
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    static void close(ResultSet rs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
