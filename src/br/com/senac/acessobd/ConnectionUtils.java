/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.acessobd;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author elvis.bsantos
 */
public class ConnectionUtils {
    public static Connection getConnection(){
        Connection con = null;
        String urlConnection = 
                "jdbc:mysql://localhost:3306/usuario_teste";
        
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "adminadmin");
        try{
        con = DriverManager.getConnection(urlConnection, properties);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
