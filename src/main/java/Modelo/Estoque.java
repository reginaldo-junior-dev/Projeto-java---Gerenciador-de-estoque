/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import java.time.LocalDate;
/**
 *
 * @author junio
 */
public class Estoque {
    int id;
    int quanti_usada;
    String movimentacao;
    LocalDate data_ent_sai;
    int id_alimento;

    public int getQuanti_usada() {
        return quanti_usada;
    }

    public void setQuanti_usada(int quanti_usada) {
        this.quanti_usada = quanti_usada;
    }

    public String getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(String movimentacao) {
        this.movimentacao = movimentacao;
    }

    public LocalDate getData_ent_sai() {
        return data_ent_sai;
    }

    public void setData_ent_sai(LocalDate data_ent_sai) {
        this.data_ent_sai = data_ent_sai;
    }

    public int getId_alimento() {
        return id_alimento;
    }

    public void setId_alimento(int id_alimento) {
        this.id_alimento = id_alimento;
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
