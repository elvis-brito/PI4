package br.com.senac.produto;

import br.com.senac.acessobd.ConnectionUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoControl {
    PreparedStatement pstm;
    ResultSet rs;
    
    String produtoInsert = "INSERT INTO produtos (nome, genero, descricao, estoque, data_produto, valor) VALUES (?,?,?,?,?)";
    String produtoSelect = "SELECT * FROM produto WHERE nome LIKE ?";
    
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
    public List<ProdutoBean> listaProduto(String NOME_PROD){
        List<ProdutoBean> produto = new ArrayList();
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoSelect);
            pstm.setString(1, NOME_PROD);
            rs = pstm.executeQuery();
            ProdutoBean prod;
            while(rs.next()){
                prod = new ProdutoBean();
                prod.setCOD_PROD(rs.getInt("COD_PROD"));
                prod.setNOME_PROD(rs.getString("NOME_PROD"));
                prod.setTIPO_PROD(rs.getString("TIPO_PROD"));
                prod.setVL_PROD(rs.getDouble("VL_PROD"));
                prod.setQTD_PROD(rs.getInt("QTD_PROD"));
                produto.add(prod);
            }
        }catch(Exception ex){
                ex.printStackTrace();
            }
        return produto;
        }
    String produtoUpdate = "UPDATE produto SET NOME_PROD = ?, TIPO_PROD = ?, VL_PROD = ?, QTD_PROD = ? WHERE COD_PROD = ?";
    public void alterarProduto(ProdutoBean produto){
        try{
            AcessoMySql mysql = new AcessoMySql();
            pstm = mysql.conectar().prepareStatement(produtoUpdate);
            pstm.setString(1, produto.getNOME_PROD());
            pstm.setString(2, produto.getTIPO_PROD());
            pstm.setDouble(3, produto.getVL_PROD());
            pstm.setInt(4, produto.getQTD_PROD());
            pstm.setInt(5, produto.getCOD_PROD());
            pstm.executeUpdate();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    
    
    
    //public static void inserir(Produto produto) throws Exception {
        Connection con = null;
        try {
            con = ConnectionUtils.getConnection();

            String sql = "";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getGenero());
            ps.setString(3, produto.getDescricao());
            ps.setInt(4, produto.getQuantidade());
            ps.setDate(5, new Date(produto.getData().getTime()));
            ps.setDouble(6, produto.getValor());
            ps.setBoolean(5, true);

            ps.execute();
        } finally {
          if (con != null && !con.isClosed()){
              con.close();
          }
        }

    }
}
