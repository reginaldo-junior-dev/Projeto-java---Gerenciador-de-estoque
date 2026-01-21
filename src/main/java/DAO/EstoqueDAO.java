/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Factory.ConnectionFactory;
import Modelo.Alimento;
import java.sql.PreparedStatement;
import java.sql.*;
import Modelo.Estoque;
import java.time.LocalDate;
import DAO.AlimentoDAO;

/**
 *
 * @author junio
 */
public class EstoqueDAO {
    private Connection connection;
    int id;
    int quanti_usada;
    String movimentacao;
    LocalDate data_ent_sai;
    int id_alimento;
    
    public EstoqueDAO() {
    this.connection = new ConnectionFactory().getConnection();
    }
    
    public void AdicionarEstoque(int id_alimento, String movimentacao, LocalDate data_ent_sai, int quanti_usada) {
    String sql = "INSERT INTO estoque(id_alimento, movimentacao, data_ent_sai, quanti_usada) VALUES (?, ?, ?, ?)";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id_alimento);
        stmt.setString(2, movimentacao);
        stmt.setDate(3, java.sql.Date.valueOf(data_ent_sai));
        stmt.setInt(4, quanti_usada);
        stmt.execute();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
    
    public Alimento Tela(int id) {
    String sql = "SELECT nome, quantidade FROM alimento WHERE id = ?";
    
    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Alimento alimento = new Alimento();
            alimento.setId(id);
            alimento.setNome(rs.getString("nome"));
            alimento.setQuantidade(rs.getInt("quantidade"));
            return alimento;
        }

        rs.close();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return null; 
}
    
    public void AtualizarEstoque(int id_alimento, int quanti_usada, String movimentacao) {
    String sql;
    
    if (movimentacao.equalsIgnoreCase("entrada")) {
        sql = "UPDATE alimento SET quantidade = quantidade + ? WHERE id = ?";
    } else if (movimentacao.equalsIgnoreCase("saída")) {
        sql = "UPDATE alimento SET quantidade = quantidade - ? WHERE id = ?";
    } else {
        throw new IllegalArgumentException("Tipo de movimentação inválido: " + movimentacao);
    }

    try {
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, quanti_usada);
        stmt.setInt(2, id_alimento);
        stmt.executeUpdate();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}

  
    
    

         
    
}
