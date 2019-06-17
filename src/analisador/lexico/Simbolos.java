/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador.lexico;

import java.util.ArrayList;

/**
 *
 * @author helio
 */
public class Simbolos {

    private String lexema;
    private String categoria;
    private String tipo;
    private int endereco;
    
    private ArrayList<Integer> linhas;

    public Simbolos(String lexema) {
        this.lexema = lexema;
    }

    public Simbolos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Simbolos)) {
            return false;
        }
        final Simbolos other = (Simbolos) obj;
        return this.getLexema().equals(other.getLexema());
    }

    public String getLexema() {
        return lexema;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

}
