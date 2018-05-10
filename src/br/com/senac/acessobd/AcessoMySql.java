/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.acessobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Administrador
 */
public class AcessoMySql {
    
    Connection con;
    
    public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password=adminadmin");
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return con;
    }
    
    public void desconectar(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
    

