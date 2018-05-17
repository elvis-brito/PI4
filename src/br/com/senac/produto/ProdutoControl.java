package br.com.senac.produto;

import br.com.senac.acessobd.AcessoMySql;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoControl {
    PreparedStatement pstm;
    ResultSet rs;
    
    String produtoInsert = "INSERT INTO produto (nome_produto, departamento_produto, cor, tamanho_produto, descricao_produto, preco_produto, quant_estoque_produto, data_cadastro_produto, enable) VALUES (?,?,?,?,?,?,?,?,?)";
    String produtoSelect = "SELECT * FROM produto WHERE nome_produto LIKE ? AND enable = 1";
    
    public void CadastrarProduto(Produto produto){
        try{
           AcessoMySql mysql = new AcessoMySql(); 
            pstm = mysql.conectar().prepareStatement(produtoInsert);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getGenero());
            pstm.setString(3, produto.getCor());
            pstm.setString(4, produto.getTamanho());
            pstm.setString(5, produto.getDescricao());
            pstm.setDouble(6, produto.getPreco());
            pstm.setInt(7, produto.getQuantidade());
            pstm.setDate(8, new Date(produto.getData().getTime()));
            pstm.setBoolean(9, true);
            pstm.execute();
  
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public List<Produto> listaProduto(String nome_produto){
        List<Produto> produto = new ArrayList();
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoSelect);
            pstm.setString(1, nome_produto);
            rs = pstm.executeQuery();
            Produto prod;
            while(rs.next()){
                prod = new Produto();
                prod.setCodigo(rs.getInt("id_produto"));
                prod.setNome(rs.getString("nome_produto"));
                prod.setGenero(rs.getString("departamento_produto"));
                prod.setCor(rs.getString("cor"));
                prod.setTamanho(rs.getString("tamanho_produto"));
                prod.setDescricao(rs.getString("descricao_produto"));
                prod.setPreco(rs.getDouble("preco_produto"));
                prod.setQuantidade(rs.getInt("quant_estoque_produto"));
                prod.setData(rs.getDate("data_cadastro_produto"));
                produto.add(prod);
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        return produto;
        }
    String produtoUpdate = "UPDATE produto SET nome_produto = ?, departamento_produto = ?, cor = ?, tamanho_produto = ?, descricao_produto = ?, preco_produto = ?, quant_estoque_produto = ? WHERE id_produto = ?";
    public void alterarProduto(Produto produto){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoUpdate);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getGenero());
            pstm.setString(3, produto.getCor());
            pstm.setString(4, produto.getTamanho());
            pstm.setString(5, produto.getDescricao());
            pstm.setDouble(6, produto.getPreco());
            pstm.setInt(7, produto.getQuantidade());
            pstm.setInt(8, produto.getCodigo());
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
    
    
   
