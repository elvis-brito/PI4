/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.cliente;

import br.com.senac.acessobd.AcessoMySql;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jussara Andrade
 */
public class ClienteEnderecoControl {
    
    PreparedStatement pstm;
    ResultSet rs;
    
   String ClienteInsert = "INSERT INTO end_cliente (rua_cliente, numero_cliente, bairro_cliente, cidade_cliente, uf_cliente, cep_cliente, complemento_cliente) VALUES (?,?,?,?,?,?,?)";
   String ClienteSelect = "SELECT * FROM end_cliente WHERE endereco_cliente LIKE ? AND enable = 1";
   String ClienteUpdate = "UPDATE cliente SET rua_cliente = ?, numero_cliente = ?, bairro_cliente = ?, cidade_cliente = ?, uf_cliente = ?,cep_cliente = ?, complemento_cliente = ? WHERE cliente_id_cliente = ?";
    public void CadastrarEndereco(ClienteEndereco clienteEndereco){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm= mysql.conectar().prepareStatement(ClienteInsert);
            pstm.setString(1, clienteEndereco.getRuaCliente());
            pstm.setString(2, clienteEndereco.getNumeroCliente());
            pstm.setString(3, clienteEndereco.getBairroCliente());
            pstm.setString(4, clienteEndereco.getCidadeCliente());
            pstm.setString(5, clienteEndereco.getUfCliente());
            pstm.setString(6, clienteEndereco.getCepCliente());
            pstm.setString(7, clienteEndereco.getComplementoCliente());          
            
            pstm.execute();
        }catch(Exception ex){
            ex.printStackTrace();
        }
       
    }
public List<ClienteEndereco> listaEndereco(String end_cliente){
        List<ClienteEndereco> endereco = new ArrayList();
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(ClienteSelect);
            pstm.setString(1, end_cliente);
            rs = pstm.executeQuery();
            ClienteEndereco cli;
            while(rs.next()){
                cli = new ClienteEndereco();
                cli.setRuaCliente(rs.getString("rua_cliente"));
                cli.setNumeroCliente(rs.getString("numero_cliente"));
                cli.setBairroCliente(rs.getString("bairo_cliente"));
                cli.setCidadeCliente(rs.getString("cidade_cliente"));
                cli.setUfCliente(rs.getString("uf_cliente"));
                cli.setCepCliente(rs.getString("cep_cliente"));
                cli.setComplementoCliente(rs.getString("complemento_cliente"));
                
                     
                endereco.add(cli);
                
               
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        return endereco;
     

}
public void alterarEnderco(ClienteEndereco endereco){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(ClienteUpdate);
            pstm.setString(1, endereco.getRuaCliente());
            pstm.setString(2, endereco.getNumeroCliente());
            pstm.setString(3, endereco.getBairroCliente());
            pstm.setString(4, endereco.getCidadeCliente());
            pstm.setString(5, endereco.getUfCliente());
            pstm.setString(6, endereco.getCepCliente());
            pstm.setString(7, endereco.getComplementoCliente());
            
              
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
   }

