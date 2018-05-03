package br.com.senac.produto;

import br.com.senac.acessobd.ConnectionUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProdutoControl {
    PreparedStatement pstm;
    
    public static void inserir(Produto produto) throws Exception {
        Connection con = null;
        try {
            con = ConnectionUtils.getConnection();

            String sql = "INSERT INTO Produto (nome_produto, genero_produto, desc_produto, quantidade_estoque_produto, data_cadastro_produto, valor_venda_produto) VALUES (?,?,?,?,?)";
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
