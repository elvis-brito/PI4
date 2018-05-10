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
    
    String produtoInsert = "INSERT INTO produto (nome_produto, genero_produto, cor, tamanho_produto, descricao_produto, preco_produto, quant_estoque_produto, data_cadastro_produto) VALUES (?,?,?,?,?,?,?,?)";
    String produtoSelect = "SELECT * FROM produto WHERE nome LIKE ?";
    
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
            
  
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public List<Produto> listaProduto(String NOME_PROD){
        List<Produto> produto = new ArrayList();
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoSelect);
            pstm.setString(1, NOME_PROD);
            rs = pstm.executeQuery();
            Produto prod;
            while(rs.next()){
                prod = new Produto();
                prod.setCodigo(rs.getInt("COD_PROD"));
                prod.setNome(rs.getString("NOME_PROD"));
                prod.setDescricao(rs.getString("DESC_PROD"));
                prod.setPreco(rs.getDouble("VL_PROD"));
                prod.setQuantidade(rs.getInt("QTD_PROD"));
                produto.add(prod);
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        return produto;
        }
    String produtoUpdate = "UPDATE produto SET nome_produto = ?, genero_produto = ?, cor = ?, tamanho_produto = ?, descricao_produto = ?, preco_produto = ?, quant_estoque_produto = ? WHERE COD_PROD = ?";
    public void alterarProduto(Produto produto){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoUpdate);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getPreco());
            pstm.setInt(4, produto.getQuantidade());
            pstm.setInt(5, produto.getCodigo());
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
    
    
   
