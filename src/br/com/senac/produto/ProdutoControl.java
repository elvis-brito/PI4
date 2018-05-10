package br.com.senac.produto;

import br.com.senac.acessobd.ConnectionUtils;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoControl {
    PreparedStatement pstm;
    ResultSet rs;
    
    String produtoInsert = "INSERT INTO produtos (nome_produto, genero_produto,"
            + "cor, tamanho_produto, descricao_produto, preco_produto, quant_estoque_produto, data_cadastro_produto) VALUES (?,?,?,?,?,?,?,?)";
    String produtoSelect = "SELECT * FROM produtos WHERE nome LIKE ?";
    
    public void CadastrarProduto(Produto produto){
        try{
           ConnectionUtils mysql = new ConnectionUtils(); 
            pstm = mysql.getConnection().prepareStatement(produtoInsert);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getGenero());
            pstm.setString(3, produto.getDescricao());
            pstm.setInt(4, produto.getQuantidade());
            pstm.setDate(5, new Date(produto.getData().getTime()));
            pstm.setDouble(6, produto.getValor());
            pstm.setBoolean(5, true);
            
  
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public List<Produto> listaProduto(String NOME_PROD){
        List<Produto> produto = new ArrayList();
        try{
            ConnectionUtils mysql = new ConnectionUtils();
            Produto produtos = new Produto();
            pstm = mysql.getConnection().prepareStatement(produtoSelect);
            
            pstm.setString(1, NOME_PROD);
            rs = pstm.executeQuery();
            Produto prod;
            while(rs.next()){
                prod = new Produto();
                prod.setCodigo(rs.getInt("COD_PROD"));
                prod.setNome(rs.getString("NOME_PROD"));
                prod.setDescricao(rs.getString("DESC_PROD"));
                prod.setValor(rs.getDouble("VL_PROD"));
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
            Produto mysql = new Produto();
            pstm = mysql.getConnection().prepareStatement(produtoUpdate);
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getValor());
            pstm.setInt(4, produto.getQuantidade());
            pstm.setInt(5, produto.getCodigo());
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
    
    
   
