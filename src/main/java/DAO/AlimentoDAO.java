/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.*;
import Modelo.Alimento;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author junio
 */
public class AlimentoDAO {
 private Connection connection;
   
 public AlimentoDAO() {
  this.connection = new ConnectionFactory().getConnection();
 }
 
 public void adicionar(Alimento alimento) {
     String sql = "INSERT INTO alimento(id,nome, categoria, quantidade, data_val, fornecedor, "
             + "preco) VALUES (?,?, ?, ?, ?, ?, ?)";
             
     
     try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alimento.getId());
        stmt.setString(2, alimento.getNome());
        stmt.setString(3, alimento.getCategoria());
        stmt.setInt(4, alimento.getQuantidade());
        stmt.setDate(5, java.sql.Date.valueOf(alimento.getData_val()));
        stmt.setString(6, alimento.getFornecedor());
        stmt.setDouble(7, alimento.getPreco());
        stmt.execute();
        stmt.close();
     
     } catch (SQLException u) {
         throw new RuntimeException(u);
     
     }
 
 }
 
 public List<Alimento> recupera(Alimento alimento) {
    List<Alimento> alimentos = new ArrayList<>();
    String sql = "SELECT id, nome, categoria, quantidade, data_val, fornecedor, preco FROM alimento WHERE id = ?";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, alimento.getId());
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            Alimento a = new Alimento();
            a.setId(rs.getInt("id"));
            a.setNome(rs.getString("nome"));
            a.setCategoria(rs.getString("categoria"));
            a.setQuantidade(rs.getInt("quantidade"));
            a.setData_val(rs.getDate("data_val").toLocalDate());
            a.setFornecedor(rs.getString("fornecedor"));
            a.setPreco(rs.getDouble("preco"));
            alimentos.add(a);
        }
        rs.close();
        stmt.close();
        
    } catch (SQLException u) {
        throw new RuntimeException(u);
    }
    
    return alimentos; 
}
 
 public boolean atualizar(Alimento alimento) {
    String sql = "UPDATE alimento SET nome = ?, categoria = ?, preco = ?, quantidade = ?, data_val = ?, fornecedor = ? WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, alimento.getNome());
        stmt.setString(2, alimento.getCategoria());
        stmt.setDouble(3, alimento.getPreco());
        stmt.setInt(4, alimento.getQuantidade());
        stmt.setDate(5, java.sql.Date.valueOf(alimento.getData_val()));
        stmt.setString(6, alimento.getFornecedor());
        stmt.setInt(7, alimento.getId());
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao atualizar o alimento", e);
    }
}



 
public boolean excluir(int id) {
    
    String sqlEstoque = "DELETE FROM estoque WHERE id_alimento = ?";
    try (PreparedStatement stmtEstoque = connection.prepareStatement(sqlEstoque)) {
        stmtEstoque.setInt(1, id);
        int rowsAffectedEstoque = stmtEstoque.executeUpdate();
        
        if (rowsAffectedEstoque == 0) {
            return false; 
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir do estoque: ", e);
    }
    
    
    String sqlAlimento = "DELETE FROM alimento WHERE id = ?";
    try (PreparedStatement stmtAlimento = connection.prepareStatement(sqlAlimento)) {
        stmtAlimento.setInt(1, id);
        int rowsAffectedAlimento = stmtAlimento.executeUpdate();
        
        return rowsAffectedAlimento > 0; 
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao excluir alimento: ", e);
    }
}



public Alimento busca(int id) {
    String sql = "SELECT * FROM alimento WHERE id = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setInt(1, id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String nome = rs.getString("nome");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");
                Date dataValidade = rs.getDate("data_val");
                String fornecedor = rs.getString("fornecedor");
                double preco = rs.getDouble("preco");

                
                Alimento alimento = new Alimento();
                alimento.setId(id);
                alimento.setNome(nome);
                alimento.setCategoria(categoria);
                alimento.setQuantidade(quantidade);
                alimento.setFornecedor(fornecedor);
                alimento.setPreco(preco);

                if (dataValidade != null) {
                    alimento.setData_val(dataValidade.toLocalDate());
                }

                return alimento;
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar alimento", e);
    }
    return null;
}





 
 }

    
 



