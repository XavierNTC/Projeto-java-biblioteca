/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.util.Calendar;

/**
 *
 * @author Mateus
 */
public class Emprestimo {
    int id;
    int exemplar_id;
    int bibliotecaria_id;
    int cliente_id;
    Calendar data_emprestimo;
    Calendar data_devolucao;
    double valor_multa_diaria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExemplar_id() {
        return exemplar_id;
    }

    public void setExemplar_id(int exemplar_id) {
        this.exemplar_id = exemplar_id;
    }

    public int getBibliotecaria_id() {
        return bibliotecaria_id;
    }

    public void setBibliotecaria_id(int bibliotecaria_id) {
        this.bibliotecaria_id = bibliotecaria_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Calendar getData_emprestimo() {
        return data_emprestimo;
    }

    public void setData_emprestimo(Calendar data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }

    public Calendar getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(Calendar data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public double getValor_multa_diaria() {
        return valor_multa_diaria;
    }

    public void setValor_multa_diaria(double valor_multa_diaria) {
        this.valor_multa_diaria = valor_multa_diaria;
    }
}
