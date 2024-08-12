/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author Mateus
 */
public class Livro {
    
    int id;
    int categoria_id;
    int editora_id;
    String titulo;
    int numero_exemplares;
    int numero_exemplares_disponiveis;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(int categoria_id) {
        this.categoria_id = categoria_id;
    }

    public int getEditora_id() {
        return editora_id;
    }

    public void setEditora_id(int editora_id) {
        this.editora_id = editora_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumero_exemplares() {
        return numero_exemplares;
    }

    public void setNumero_exemplares(int numero_exemplares) {
        this.numero_exemplares = numero_exemplares;
    }

    public int getNumero_exemplares_disponiveis() {
        return numero_exemplares_disponiveis;
    }

    public void setNumero_exemplares_disponiveis(int numero_exemplares_disponiveis) {
        this.numero_exemplares_disponiveis = numero_exemplares_disponiveis;
    }

    
    
}
