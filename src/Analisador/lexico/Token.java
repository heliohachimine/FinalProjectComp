/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisador.lexico;

/**
 *
 * @author Luís Fernando
 */
public class Token {

    private String classe;
    private String lexema = null;
    private int coluna;
    private int linha;
    private int endereco;

    public Token(String classe, String lexema, int linha, int coluna) {
        this.classe = classe;
        this.lexema = lexema;
        this.coluna = coluna;
        this.linha = linha;

    }

    public Token(String classe, String lexema, int linha, int coluna, int endereco) {
        this.classe = classe;
        this.lexema = lexema;
        this.coluna = coluna;
        this.linha = linha;
        this.endereco = endereco;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

}
