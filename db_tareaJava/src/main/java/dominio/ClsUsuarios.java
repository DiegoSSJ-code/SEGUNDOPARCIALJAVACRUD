/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Win10
 */
public class ClsUsuarios {
    private int id_usuario;
    private String username;
    private String password;
    
    public ClsUsuarios(){
        
    }
    
    public ClsUsuarios(int id){
        this.id_usuario = id;
    }
    
    public ClsUsuarios(String usu, String pass){
        this.username = usu;
        this.password = pass;
    }
    
    public ClsUsuarios(int id, String usu, String pass){
        this.id_usuario = id;
        this.username = usu;
        this.password = pass;
    }

    @Override
    public String toString() {
        return "ClsUsuarios{" + "id_usuario=" + id_usuario + ", username=" + username + ", password=" + password + '}';
    }
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
