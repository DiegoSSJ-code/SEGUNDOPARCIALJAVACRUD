/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.ClsUsuarios;
import dominio.ClsVendedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Win10
 */
public class ClsUsuarioJDBC implements InterfaceUsuariosJDBC <ClsUsuarios>{
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private static final String SQL_SELECT = "select * from tb_usuarios";
    private static final String SQL_INSERT = "insert into tb_usuarios (username, password) values(?, ?)";
    private static final String SQL_UPDATE = "update tb_usuarios set username = ?, password = ? where  = " + "id_usuario = ?";
    private static final String SQL_SEARCH = "select * from tb_usuarios where id_usuario = ?";
    private static final String SQL_DELETE = "delete from tb_usuarios where id_usuario = ?"; 
    
    @Override
    public int SQLCreate(ClsUsuarios x) {
        int crear = 0;
       try{
           con = ClsConexion.getConnection();
           stmt = con.prepareStatement(SQL_INSERT);
           stmt.setString(1, x.getUsername());
           stmt.setString(2, x.getPassword());
           crear = stmt.executeUpdate();
       } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
       }
       return crear;
    }

    @Override
    public int SQLUpdate(ClsUsuarios x) {
        int actualizar = 0;
       try{
           con = ClsConexion.getConnection();
           stmt = con.prepareStatement(SQL_INSERT);
           stmt.setString(1, x.getUsername());
           stmt.setString(2, x.getPassword());
           actualizar = stmt.executeUpdate();
       } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
       }
       return actualizar;
    }

    @Override
    public int SQLDelete(int key) {
    int eliminar = 0;
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setInt(1, key);
            eliminar = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return eliminar;    
    }

    @Override
    public ClsUsuarios SQLSelectOnlyOne(int key) {
    ClsUsuarios us = new ClsUsuarios();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SEARCH);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while(rs.next()){
                us.setId_usuario(rs.getInt("id_usuario"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return us;   
    }

    @Override
    public List<ClsUsuarios> SQLSelect() {
        List<ClsUsuarios> usuarios = new ArrayList<ClsUsuarios>();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            ClsUsuarios usuario = null;
            
            while(rs.next()){
                int id_usuario = rs.getInt("id_usuaio");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                usuario = new ClsUsuarios(id_usuario, user, pass);
                usuario.setId_usuario(id_usuario);
                usuario.setUsername(user);
                usuario.setPassword(pass);
                usuarios.add(usuario);      
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            //ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return usuarios;
    }

    @Override
    public int SQLValidate(ClsUsuarios x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
