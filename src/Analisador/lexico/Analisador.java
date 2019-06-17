/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analisador.lexico;

import java.util.ArrayList;
import java.util.Scanner;
import utils.PalavrasReservadas;

/**
 *
 * @author Luís Fernando
 */
public class Analisador {

    ArrayList<Token> token = new ArrayList<>();
    ArrayList<Simbolos> tabelaSimbolos = new ArrayList<>();
    PalavrasReservadas reservadas = new PalavrasReservadas();
    ArrayList<String> palavrasReservadas = reservadas.getPalavrasReservadas();
    String erro = "";
    int coluna = 0;
    int linha = 1;

    public ArrayList<Token> getToken() {
        return new ArrayList<Token>(token);
    }

    public String getErro() {
        return this.erro;
    }

    public ArrayList<Simbolos> getSimbolos() {
        return new ArrayList<Simbolos>(tabelaSimbolos);
    }

    public Analisador(String codigo) {
        String[] lines = codigo.split("\n");
        for (int i = 0; i < lines.length; i++) {
            this.coluna = 0;
            this.verificar(lines[i] + " ");
            this.linha++;
        }

    }

    public void verificar(String texto) {

        String lexema = "";
        int estado = 0;
        char caracter;

        for (int i = 0; i < texto.length(); i++) {

            caracter = texto.charAt(i);

            switch (estado) {

                case 0: {
                    lexema = "";
                    if ((Character.isLetter(caracter))) { // Se iniciar com letra -> Identificador

                        lexema += caracter;
                        estado = 1;
                    } else if ((Character.isDigit(caracter))) { // Se iniciar com número -> Número

                        lexema += caracter;
                        estado = 2;

                    } else if (caracter == '"') { // Se iniciar com aspas -> Cadeira de caracteres
                        lexema += caracter;
                        estado = 8;
                    } else if (caracter == ':') {
                        lexema += caracter;
                        estado = 5;
                    } else if (caracter == '(') {
                        Token token = new Token("cLPar", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == ')') {
                        Token token = new Token("cDPar", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '[') {
                        Token token = new Token("cLCha", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == ']') {
                        Token token = new Token("cRCha", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '+') {
                        Token token = new Token("cAdd", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '-' || caracter == '–') {
                        Token token = new Token("cSub", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '*') {
                        Token token = new Token("cMul", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '/') {
                        Token token = new Token("cDiv", Character.toString(caracter), this.linha, i);
                        this.token.add(token);

                    } else if (caracter == '=') {
                        Token token = new Token("cEQ", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    } else if (caracter == '<') {
                        lexema += caracter;
                        estado = 6;
                    } else if (caracter == '>') {
                        lexema += caracter;
                        estado = 7;
                    } else if (caracter == ';') {
                        Token token = new Token("cPVir", Character.toString(caracter), this.linha, i);
                        this.token.add(token);

                    } else if (caracter == ',') {
                        Token token = new Token("cVir", Character.toString(caracter), this.linha, i);
                        this.token.add(token);

                    } else if (caracter == '.') {
                        Token token = new Token("cPto", Character.toString(caracter), this.linha, i);
                        this.token.add(token);
                    }

                    break;
                }
                case 1: {
                    if ((Character.isLetter(caracter) || Character.isDigit(caracter)) && !Character.isWhitespace(caracter)) {
                        lexema += caracter;
                    } else {
                        Simbolos id = new Simbolos(lexema.toLowerCase());
                        int endereco = 0;
                        if (!tabelaSimbolos.contains(id) && !palavrasReservadas.contains(id.getLexema().toLowerCase())) {
                            id.setEndereco(tabelaSimbolos.size());
                            endereco = tabelaSimbolos.size();
                            tabelaSimbolos.add(id);
                        } else if (tabelaSimbolos.contains(id) && !palavrasReservadas.contains(id.getLexema().toLowerCase())) {
                            endereco = tabelaSimbolos.indexOf(id);
                        }
                        if (palavrasReservadas.contains(lexema.toLowerCase())) {
                            Token token = new Token("Palavra Reservada", lexema, this.linha, i - lexema.length());
                            this.token.add(token);
                        } else {
                            Token token = new Token("cId", lexema, this.linha, i - lexema.length(), endereco);
                            this.token.add(token);
                        }

                        i--;
                        this.coluna--;
                        estado = 0;
                    }
                    break;
                }
                case 2: {
                    if ((Character.isDigit(caracter)) && !Character.isWhitespace(caracter)) {
                        lexema += caracter;
                    } else if (caracter == '.') {
                        lexema += caracter;
                        estado = 3;
                    } else {
                        Token token = new Token("cInt", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                        estado = 0;
                    }
                    break;
                }
                case 3: {
                    if ((Character.isDigit(caracter))) {
                        lexema += caracter;
                        estado = 4;
                    } else {
                        erro += "Erro" + "(" + this.linha + "," + i + ")" + " , necessário digito após ponto flutuante \n";
                        i--;
                        this.coluna--;

                        estado = 0;
                    }
                    break;
                }
                case 4: {
                    if ((Character.isDigit(caracter)) && !Character.isWhitespace(caracter)) {
                        lexema += caracter;
                    } else {
                        Token token = new Token("cReal", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                        estado = 0;
                    }
                    break;
                }
                case 5: {
                    if (caracter == '=') {
                        lexema += caracter;
                        Token token = new Token("cAtr", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        estado = 0;
                    } else {

                        Token token = new Token("cDPto", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                        estado = 0;
                    }
                    break;
                }
                case 6: {

                    if (caracter == '=') {
                        lexema += caracter;
                        Token token = new Token("cLE", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                    } else if (caracter == '>') {
                        lexema += caracter;
                        Token token = new Token("cNE", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                    } else {
                        Token token = new Token("cLT", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                    }
                    estado = 0;
                    break;
                }
                case 7: {
                    if (caracter == '=') {
                        lexema += caracter;
                        Token token = new Token("cGE", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                    } else {
                        Token token = new Token("cGT", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                    }
                    estado = 0;
                    break;
                }
                case 8: {
                    if ((Character.isLetter(caracter) || Character.isDigit(caracter)) && !Character.isWhitespace(caracter)) {
                        lexema += caracter;
                        estado = 8;
                    } else if (caracter == '"') {
                        lexema += caracter;
                        Token token = new Token("cStr", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        estado = 0;
                    } else {
                        lexema += '"';
                        this.erro += ("Atenção" + "(" + this.linha + "," + i + ")" + " , Cadeia de caracteres terminando sem aspas \n");
                        Token token = new Token("cStr", lexema, this.linha, i - lexema.length());
                        this.token.add(token);
                        i--;
                        this.coluna--;

                        estado = 0;
                    }

                    break;
                }
                default:
                    break;
            }

        }

    }

}
