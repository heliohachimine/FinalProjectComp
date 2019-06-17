/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Luís Fernando
 */
public class PalavrasReservadas {
     ArrayList<String> palavarasReservadas = new ArrayList<>( 
            Arrays.asList("and", "array", "begin", "case", "const", "div", "do", "downto", "else", "end", "file", "for", "function", "goto",
     "if", "in", "label", "mod", "nil", "not", "of", "or", "packed", "procedure","program", "record", "repeat", "set", "then", "to", "type", "until", "var",
     "while", "with" ,"write", "read" , "integer", "real"));

    public ArrayList<String> getPalavrasReservadas() {
         return palavarasReservadas;
    }


    
    
}
