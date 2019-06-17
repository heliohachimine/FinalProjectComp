/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador.lexico;

/**
 *
 * @author Alunos
 */
public class TabelaSimbolos {
    int memoria;
    Registro registro;
    TabelaSimbolos tabelaPai;
}

class Registro {
    String nome;
    String categoria;
    int nivel;
    String tipo;
    int offset;
    int numeroParametros;
    String rotulo;
    TabelaSimbolos tabelaSimbolos;
}

