
package br.com.senac.cliente;


import br.com.senac.acessobd.AcessoMySql;
import java.sql.Date;
import java.sql.PreparedStatement;


public class ClienteControl {
    
    PreparedStatement pstm;
    
    String ClienteInsert = "INSERT INTO cliente (nome_cliente, sexo_cliente, rg_cliente, cpf_cliente, data_nasc_cliente, email_cliente, celular_cliente, telefone_cliente, data_cadastro_cliente, enable) VALUES (?,?,?,?,?,?,?,?,?,?)";
    
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
            pstm.setDate(3, new Date(cliente.getDataCadastroCliente().getTime()));
            pstm.setBoolean(10, true);
            pstm.execute();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
