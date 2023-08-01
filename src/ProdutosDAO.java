/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto) throws SQLException{
        conn = new conectaDAO().connectDB();
        int status;
        prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        status = prep.executeUpdate();
        return status;
    }
    
    public List<ProdutosDTO> listarProdutos() throws SQLException{
        conn = new conectaDAO().connectDB();
        List<ProdutosDTO> listaProdutos = new ArrayList();
        String sql = "SELECT * FROM produtos";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        while(resultset.next()) {
            ProdutosDTO prodDao = new ProdutosDTO(resultset.getInt("id"), resultset.getString("nome"), resultset.getInt("valor"), resultset.getString("status"));
            listaProdutos.add(prodDao);
        }
        return listaProdutos;
    }
    
    public List<ProdutosDTO> listarVendidos() throws SQLException{
        conn = new conectaDAO().connectDB();
        List<ProdutosDTO> listaVendidos = new ArrayList();
        String sql = "SELECT * FROM produtos where status = ?";
        prep = conn.prepareStatement(sql);
        prep.setString(1, "vendido");
        resultset = prep.executeQuery();
        while(resultset.next()) {
            ProdutosDTO prodDao = new ProdutosDTO(resultset.getInt("id"), resultset.getString("nome"), resultset.getInt("valor"), resultset.getString("status"));
            listaVendidos.add(prodDao);
        }
        return listaVendidos;
    }
    
    public int venderProdutos(int idProduto) throws SQLException{
        int status;
        
        prep = conn.prepareStatement("UPDATE produtos SET status = ? where id = ?");
        prep.setString(1, "Vendido");
        prep.setInt(2, idProduto);
        status = prep.executeUpdate();
        return status;
        
    }
    
    
        
}

