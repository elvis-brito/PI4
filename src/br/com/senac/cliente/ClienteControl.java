
package br.com.senac.cliente;


import br.com.senac.acessobd.AcessoMySql;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClienteControl {
    
    PreparedStatement pstm;
    ResultSet rs;
    
    String ClienteInsert = "INSERT INTO cliente (nome_cliente, sexo_cliente, rg_cliente, cpf_cliente, data_nasc_cliente, email_cliente, celular_cliente, telefone_cliente, data_cadastro_cliente, enable) VALUES (?,?,?,?,?,?,?,?,?,?)";
    String ClienteSelect = "SELECT * FROM cliente WHERE nome_cliente LIKE ? AND enable = 1";
    String ClienteUpdate = "UPDATE cliente SET nome_cliente = ?, sexo_cliente = ?, rg_cliente = ?, cpf_cliente = ?, data_nasc_cliente = ?, email_cliente = ?, celular_cliente = ?, telefone_cliente = ? WHERE id_cliente = ?";
    
    public void CadastrarCliente(ClienteCadastro cliente){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm= mysql.conectar().prepareStatement(ClienteInsert);
            pstm.setString(1, cliente.getNomeCliente());
            pstm.setString(2, cliente.getSexoCliente());
            pstm.setString(3, cliente.getRgCliente());
            pstm.setString(4, cliente.getCpfCliente());
            pstm.setDate(5, new Date(cliente.getDataNascimentoCliente().getTime()));
            pstm.setString(6, cliente.getEmailCliente());
            pstm.setString(7, cliente.getCelularCliente());
            pstm.setString(8, cliente.getTelefoneCliente());
            pstm.setDate(9, new Date(cliente.getDataCadastroCliente().getTime()));
            pstm.setBoolean(10, true);
            pstm.execute();
        }catch(Exception ex){
            ex.printStackTrace();
        }
   
}
 public List<ClienteCadastro> listaCliente(String nome_cliente){
        List<ClienteCadastro> consulta = new ArrayList();
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(ClienteSelect);
            pstm.setString(1, nome_cliente);
            rs = pstm.executeQuery();
            ClienteCadastro cli;
            while(rs.next()){
                cli = new ClienteCadastro();
                cli.setNomeCliente(rs.getString("nome_cliente"));
                cli.setSexoCliente(rs.getString("sexo_cliente"));
                cli.setRgCliente(rs.getString("rg_cliente"));
                cli.setCpfCliente(rs.getString("cpf_cliente"));
                cli.setDataNascimentoCliente(rs.getDate("data_cadastro_cliente"));
                cli.setEmailCliente(rs.getString("email_cliente"));
                cli.setCelularCliente(rs.getString("celular_cliente"));
                cli.setTelefoneCliente(rs.getString("telefone_cliente"));
                cli.setDataCadastroCliente(rs.getDate("data_cadastro_cliente"));
                     
                consulta.add(cli);
                
               
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        return consulta;
        
    }
 
    public void alterarCliente(ClienteCadastro cliente){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(ClienteUpdate);
            pstm.setString(1, cliente.getNomeCliente());
            pstm.setString(2, cliente.getSexoCliente());
            pstm.setString(3, cliente.getRgCliente());
            pstm.setString(4, cliente.getCpfCliente());
            pstm.setDate(5, new Date(cliente.getDataNascimentoCliente().getTime()));
            pstm.setString(6, cliente.getEmailCliente());
            pstm.setString(7, cliente.getCelularCliente());
            pstm.setString(8, cliente.getTelefoneCliente());
            pstm.setDate(9, new Date(cliente.getDataCadastroCliente().getTime()));
            pstm.setBoolean(10, true);
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    }

