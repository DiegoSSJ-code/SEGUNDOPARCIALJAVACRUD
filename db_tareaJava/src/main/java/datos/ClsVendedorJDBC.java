/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.ClsVendedores;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.UIManager.getInt;

/**
 *
 * @author Win10
 */
public class ClsVendedorJDBC implements InterfaceVendedorJDBC <ClsVendedores>{     
    
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private static final int TRES = 3;
    private static final String SQL_SELECT = "select * from tb_vendedores";
    private static final String SQL_INSERT = "insert into tb_vendedores (codigo_v, nombre, enero, febrero, marzo, total, promedio) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update tb_vendedores set nombre = ?, enero = ?, febrero = ?, marzo = ?, total = ?, promedio = ? where codigo_v = ?";
    private static final String SQL_SEARCH = "select * from tb_vendedores where codigo_v = ?";
    private static final String SQL_DELETE = "delete from tb_vendedores where codigo_v = ?"; 

    @Override
    public int SQLCreate(ClsVendedores x) {
        int crear = 0;
        try{
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setInt(1, x.getCodigo_v());
            stmt.setString(2, x.getNombre());
            stmt.setDouble(3, x.getEnero());
            stmt.setDouble(4, x.getFebrero());
            stmt.setDouble(5, x.getMarzo());
            stmt.setDouble(6, (x.getEnero() + x.getFebrero() + x.getMarzo()));
            stmt.setDouble(7, (x.getEnero() + x.getFebrero() + x.getMarzo() / TRES));
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
    public int SQLUpdate(ClsVendedores x) {
        int actualizar = 0;
        try{
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_UPDATE);
            stmt.setString(1, x.getNombre());
            stmt.setDouble(2, x.getEnero());
            stmt.setDouble(3, x.getFebrero());
            stmt.setDouble(4, x.getMarzo());
            stmt.setDouble(5, (x.getEnero() + x.getFebrero() + x.getMarzo()));
            stmt.setDouble(6, (x.getEnero() + x.getFebrero() + x.getMarzo() / TRES));
            stmt.setInt(7, x.getCodigo_v());
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
    public ClsVendedores SQLSelectOnlyOne(int key) {
        ClsVendedores al = new ClsVendedores();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SEARCH);
            stmt.setInt(1, key);
            rs = stmt.executeQuery();
            while(rs.next()){
                al.setCodigo_v(rs.getInt("codigo_v"));
                al.setNombre(rs.getString("nombre"));
                al.setEnero(rs.getDouble("enero"));
                al.setFebrero(rs.getDouble("febrero"));
                al.setMarzo(rs.getDouble("marzo"));
                al.setTotal(rs.getDouble("total"));
                al.setPromedio(rs.getDouble("promedio"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
           // ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return al;
    }

    @Override
    public List<ClsVendedores> SQLSelect() {
        List<ClsVendedores> vendedores = new ArrayList<ClsVendedores>();
        try {
            con = ClsConexion.getConnection();
            stmt = con.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            ClsVendedores vendedor = null;
            
            while(rs.next()){
                int codigo_v = rs.getInt("codigo_v");
                String nombre = rs.getString("nombre");
                Double enero = rs.getDouble("enero");
                Double febrero = rs.getDouble("febrero");
                Double marzo = rs.getDouble("marzo");
                Double total = rs.getDouble("total");
                Double promedio = rs.getDouble("promedio");
                vendedor = new ClsVendedores(codigo_v, nombre, enero, febrero, marzo, total, promedio);
                vendedor.setCodigo_v(codigo_v);
                vendedor.setNombre(nombre);
                vendedor.setEnero(enero);
                vendedor.setFebrero(febrero);
                vendedor.setTotal(total);
                vendedor.setPromedio(promedio);
                vendedores.add(vendedor);      
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            //ClsConexion.close(rs);
            ClsConexion.close(stmt);
            ClsConexion.close(con);
        }
        return vendedores;
    }
    
}
