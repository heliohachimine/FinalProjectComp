/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisador.sintatico;

import analisador.lexico.Simbolos;
import analisador.lexico.Token;
import java.util.ArrayList;

/**
 *
 * @author Luís Fernando
 */
public class Sintatico {

    ArrayList<Simbolos> simbolosList;
    ArrayList<Token> tokenList;
    Token token;
    String mensagem;
    Token funcao;
    ArrayList<Token> aux = new ArrayList<Token>();
    int i = 0;

    public Sintatico(ArrayList<Token> tokenList, ArrayList<Simbolos> simbolosList, String mensagem) {
        this.tokenList = tokenList;
        this.simbolosList = simbolosList;
        this.mensagem = mensagem;
        this.programa();
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public ArrayList<Simbolos> getSimbolos() {
        return new ArrayList<Simbolos>(this.simbolosList);
    }

    public void lexico() {
        if (this.i < this.tokenList.size()) {
            this.i++;
            this.token = this.tokenList.get(i - 1);
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Não compilado, indentificadores insuficientes \n";
            return;

        }

    }

    public void programa() {
        this.lexico();
        if (token.getLexema().toLowerCase().equals("program")) {
            this.lexico();
            if (token.getClasse().equals("cId")) {
                this.simbolosList.get(this.token.getEndereco()).setCategoria("program");
                this.lexico();
                if (token.getLexema().equals(";")) {
                    this.lexico();
                    this.corpo();
                    if (token.getLexema().equals(".")) {

                    } else {
                        this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ponto final não encontrado \n";

                    }
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração do identificador do programa faltando ';' \n";

                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração do identificador do programa não localizada \n";

            }

        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração do identificador do programa não localizado \n";
        }
        this.mensagem += "Compilação Finalizada \n";

    }

    public void corpo() {
        this.declara();
        this.rotina();
        this.bloco();

    }

    public void declara() {
        if (this.token.getLexema().toLowerCase().equals("var")) {
            this.lexico();
            this.dvar();
        }

    }

    public void rotina() {

        if (this.token.getLexema().toLowerCase().equals("procedure")) {
            this.procedimento();
        } else if (this.token.getLexema().toLowerCase().equals("function")) {
            this.funcao();
        }

    }

    public void bloco() {
        if (this.token.getLexema().toLowerCase().equals("begin")) {
            this.lexico();
            this.sentencas();
            if (this.token.getLexema().toLowerCase().equals("end")) {
                this.lexico();
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de bloco inválida - 'end' \n";
            }

        }

    }
// Declaração de variáveis

    public void dvar() {
        this.variaveis();
        if (this.token.getLexema().equals(":")) {
            if (this.tokenList.get(this.i - 2).getLexema().equals(",")) {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de variáveis inválida \n";
                return;
            }
            this.tipo();
            this.lexico();
            if (this.token.getLexema().equals(";")) {
                this.lexico();
                this.dvar();
            }
        }

    }

    public void dvarAux() {
        if (this.token.getClasse().equals("cId")) {
            this.variaveis();
        }

    }

    public void variaveis() {
        if (this.token.getClasse().equals("cId")) {
            this.variaveisAux();
        }

    }

    public void variaveisAux() {
        this.aux.add(this.token);
        this.simbolosList.get(this.token.getEndereco()).setCategoria("Variável");
        this.lexico();
        if (this.token.getLexema().equals(":")) {

        } else if (this.token.getLexema().equals(",")) {
            this.lexico();
            this.variaveis();
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de variáveis inválida \n";

        }

    }

    public void tipo() {
        this.lexico();
        if (this.token.getLexema().toLowerCase().equals("array")) {

            this.tipoArray();
        } else if (this.token.getLexema().toLowerCase().equals("integer") || (this.token.getLexema().toLowerCase().equals("real"))) {
            this.tipoSimples(false);
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de variável \n";
        }

    }

    public void tipoArray() {
        this.lexico();
        if (this.token.getLexema().equals("[")) {
            this.lexico();
            if (this.token.getClasse().equals("cInt")) {
                this.lexico();
                if (this.token.getLexema().equals("]")) {
                    this.lexico();
                    if (this.token.getLexema().toLowerCase().equals("of")) {
                        this.lexico();
                        if (this.token.getLexema().toLowerCase().equals("integer") || (this.token.getLexema().toLowerCase().equals("real"))) {
                            this.tipoSimples(true);
                        } else {
                            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de variável \n";

                        }
                    } else {
                        this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de array  \n";
                    }
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de array  \n";
                }

            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de array  \n";
            }

        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Tipo inválido de array  \n";
        }

    }

    public void tipoSimples(boolean array) {

        if (this.token.getLexema().toLowerCase().equals("integer")) {
            for (int j = 0; j < this.aux.size(); j++) {
                if (array) {
                    this.simbolosList.get(this.aux.get(j).getEndereco()).setTipo("[Inteiro]");
                } else {
                    this.simbolosList.get(this.aux.get(j).getEndereco()).setTipo("Inteiro");
                }
            }
        } else {
            for (int j = 0; j < this.aux.size(); j++) {
                if (array) {
                    this.simbolosList.get(this.aux.get(j).getEndereco()).setTipo("[Real]");
                } else {
                    this.simbolosList.get(this.aux.get(j).getEndereco()).setTipo("Real");
                }

            }
        }

        this.aux.clear();

    }

    //Rotinas
    // Procedimento
    public void procedimento() {
        this.lexico();
        if (this.token.getClasse().equals("cId")) {
            this.simbolosList.get(this.token.getEndereco()).setCategoria("Procedimento");
            this.parametros();
            this.lexico();
            if (!this.token.getLexema().equals(";")) {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da procedure  \n";
                return;
            }
            this.corpo();
            if (!this.token.getLexema().equals(";")) {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da procedure  \n";
                return;
            }
            this.rotina();

        }

    }

    // Funções
    public void funcao() {
        this.lexico();
        if (this.token.getClasse().equals("cId")) {
            this.simbolosList.get(this.token.getEndereco()).setCategoria("Função");
            this.funcao = this.token;
            this.parametros();
            this.lexico();
            if (this.token.getLexema().equals(":")) {
                this.lexico();
                if (this.token.getLexema().toLowerCase().equals("real")) {
                    this.simbolosList.get(this.funcao.getEndereco()).setTipo("real");
                } else if (this.token.getLexema().toLowerCase().equals("integer")) {
                    this.simbolosList.get(this.funcao.getEndereco()).setTipo("Inteiro");
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função - tipo indefinido  \n";
                    return;
                }
                this.lexico();
                if (!this.token.getLexema().equals(";")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função - faltando ';' \n";
                    return;
                } else {
                    this.lexico();
                    this.corpo();

                    if (!this.token.getLexema().equals(";")) {
                        this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função  \n";
                        return;
                    } else {
                        this.lexico();
                        this.rotina();

                    }
                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função  \n";
            }
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de função  \n";
            return;
        }
    }

    public void parametros() {
        this.lexico();
        if (this.token.getLexema().equals("(")) {
            this.listaParametros();
            if (this.token.getLexema().equals(")")) {
                if (this.tokenList.get(this.i - 2).getLexema().equals(";")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função  \n";

                }
            } else {

                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida de parametros da função  \n";
                return;
            }
        }
    }

    public void listaParametros() {
        this.listaId();
        if (this.token.getLexema().equals(":")) {
            if (this.tokenList.get(this.i - 2).getLexema().equals(",")) {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de Parâmetros inválida \n";
                return;
            }
            this.tipo();
            this.lexico();
            if (this.token.getLexema().equals(";")) {
                this.listaParametros();
            }
        }

    }

    public void listaId() {
        this.lexico();
        if (this.token.getClasse().equals("cId")) {
            this.listaIdAux();
        } else if (this.token.getLexema().equals(")")) {

        }

    }

    public void listaIdAux() {
        this.aux.add(this.token);
        if (this.simbolosList.get(this.token.getEndereco()).getCategoria() != null) { // Verificando se possuem dois identificadores com mesmo lexema, caso existam um é variavel e outro parametro
            Simbolos id = new Simbolos(this.token.getLexema().toLowerCase());
            for (int j = 0; j < this.simbolosList.size(); j++) {
                if (this.simbolosList.get(j).getLexema().equals(this.token.getLexema()) && this.simbolosList.get(j).getCategoria().equals("Parâmetro")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Parâmetro já declarado  \n";
                    return;
                }
            }
            id.setEndereco(this.simbolosList.size());
            id.setCategoria("Parâmetro");
            this.simbolosList.add(id);
            this.token.setEndereco(id.getEndereco());
        } else {
            this.simbolosList.get(this.token.getEndereco()).setCategoria("Parâmetro");
        }
        this.lexico();
        if (this.token.getLexema().equals(":")) {
        } else if (this.token.getLexema().equals(",")) {
            this.listaId();
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de parametros inválida \n";

        }
    }

    // Sentenças
    public void sentencas() {
        this.sentecasAux();
    }

    public void sentecasAux() {
        this.comando();
        if (!this.token.getLexema().equals(";")) {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , falta ; \n";
        }
        this.lexico();
        try {
            if (this.token.getLexema().toLowerCase().equals("read") || this.token.getLexema().toLowerCase().equals("write") || this.token.getLexema().toLowerCase().equals("for") || this.token.getLexema().toLowerCase().equals("repeat") || this.token.getLexema().toLowerCase().equals("while") || this.token.getLexema().toLowerCase().equals("if") || this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável")) {
                this.sentencas();
            }
        } catch (Exception e) {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Expressão não reconhecida \n";
        }

    }

    public void comando() {
        try {

            if (this.token.getLexema().toLowerCase().equals("read")) {
                this.lexico();
                if (this.token.getLexema().equals("(")) {
                    this.var_read();
                    if (this.token.getLexema().equals(")")) {
                        this.lexico();
                    } else {
                        this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'read'  - ')' não identificado \n";
                    }
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração de inválida no comando 'read' - '(' não identificado \n";

                }

            } else if (this.token.getLexema().toLowerCase().equals("write")) {
                this.lexico();
                if (this.token.getLexema().equals("(")) {
                    this.var_write();
                    if (this.token.getLexema().equals(")")) {
                        this.lexico();
                    } else {
                        this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'write'  - ')' não identificado \n";

                    }
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'write' - '(' não identificado \n";

                }
            } else if (this.token.getLexema().toLowerCase().equals("for")) {
                this.lexico();
                try {
                    if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável")) {
                        this.lexico();
                        if (this.token.getLexema().equals(":=")) {
                            this.lexico();

                            this.expressao();
                            if (this.token.getLexema().toLowerCase().equals("to")) {
                                this.lexico();
                                this.expressao();
                                if (this.token.getLexema().toLowerCase().equals("do")) {
                                    this.lexico();
                                    this.bloco();
                                } else {
                                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'for' faltando 'do' \n";

                                }
                            } else {
                                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'for' faltando 'to' \n";

                            }
                        } else {
                            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'for' \n";

                        }
                    }
                } catch (Exception e) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'for' \n";
                }

            } else if (this.token.getLexema().toLowerCase().equals("repeat")) {
                this.lexico();
                this.sentencas();
                if (!this.token.getLexema().toLowerCase().equals("until")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'repeat' - 'until' não encontrado \n";
                    return;
                }
                this.lexico();
                this.expressao_logica();
            } else if (this.token.getLexema().toLowerCase().equals("while")) {
                this.lexico();
                this.expressao_logica();
                if (!this.token.getLexema().toLowerCase().equals("do")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'while' - 'do' não encontrado \n";
                    return;
                }
                this.lexico();
                this.bloco();
            } else if (this.token.getLexema().toLowerCase().equals("if")) {
                this.lexico();
                this.expressao_logica();

                if (!this.token.getLexema().toLowerCase().equals("then")) {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro no comando 'if' - 'then' não encontrado \n";
                    return;
                }
                this.lexico();
                this.bloco();
                this.pFalsa();

            } else if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável") || this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Função")) {
                this.lexico();

                if (this.token.getLexema().equals(":=")) {
                    this.lexico();
                    if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Procedimento")) {

                        this.lexico();
                        this.argumentos();
                    } else {
                        this.expressao();

                    }
                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro , ':=' na expressão não identificado \n";
                    return;
                }
            }
        } catch (Exception e) {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro , Expressão não identificada, ou variável não declarada \n";

        }
    }

    public void pFalsa() {
        if (this.token.getLexema().toLowerCase().equals("else")) {
            this.lexico();
            this.bloco();
        }
    }

    public void argumentos() {
        if (this.token.getLexema().equals("(")) {
            this.lexico();
            this.lista_arg();
            if (this.token.getLexema().equals(")")) {
                this.lexico();
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " ,  Erro , ')' não encontrado na lista de argumentos \n";
                return;
            }
        }
    }

    public void lista_arg() {
        this.lista_argAux();
    }

    public void lista_argAux() {
        this.expressao();

        if (this.token.getLexema().equals(",")) {
            this.lexico();
            this.lista_arg();
        }
    }

    public void var_read() {
        this.lexico();
        if (this.simbolosList.get(this.token.getEndereco()).getCategoria() != null) {
            if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável")) {
                this.lexico();
                if (this.token.getLexema().equals(",")) {
                    this.var_read();
                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'read' \n";

            }

        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'read' \n";

        }

    }

    public void var_write() {
        this.lexico();
        if (this.simbolosList.get(this.token.getEndereco()).getCategoria() != null) {
            if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável")) {
                this.lexico();
                if (this.token.getLexema().equals(",")) {
                    this.var_write();
                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'write' \n";

            }

        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Declaração inválida no comando 'write' \n";

        }

    }

    public void expressao() {
        if (this.token.getLexema().equals("+") || this.token.getLexema().equals("-")) {
            this.expressaoAux();
        } else {
            this.termo();
            if (this.token.getLexema().equals("+") || this.token.getClasse().equals("cSub") || this.token.getLexema().equals("*") || this.token.getLexema().equals("/") || this.token.getLexema().toLowerCase().equals("and") || this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Função") || this.token.getLexema().equals("(")) {
                this.lexico();
                this.expressaoAux();
            }

        }

    }

    public void expressaoAux() {
        this.termo();

        if (this.token.getLexema().equals("+") || this.token.getClasse().equals("cSub") || this.token.getLexema().equals("*") || this.token.getLexema().equals("/") || this.token.getLexema().toLowerCase().equals("and") || this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Função") || this.token.getLexema().equals("(")) {

            this.expressao();
        }

    }

    public void expressao_logica() {
        this.expressao_logicaAux();

    }

    public void expressao_logicaAux() {
        this.termo_logico();

        if (this.token.getLexema().toLowerCase().equals("or")) {
            this.lexico();
            this.expressao_logica();
        }

    }

    public void termo() {
        if (this.token.getLexema().equals("*") || this.token.getLexema().equals("/") || this.token.getLexema().toLowerCase().equals("end")) {
            this.lexico();
            this.termoAux();
        } else {
            this.fator();
        }

    }

    public void termoAux() {
        this.fator();
        if (this.token.getLexema().equals("*") || this.token.getLexema().equals("/") || this.token.getLexema().toLowerCase().equals("end")) {

            this.termo();
        }
    }

    public void termo_logico() {
        if (this.token.getLexema().toLowerCase().equals("and")) {
            this.lexico();
            this.termo_LogicoAux();
        } else {
            this.fator_logico();
        }
    }

    public void termo_LogicoAux() {
        this.fator_logico();
        if (this.token.getLexema().toLowerCase().equals("and")) {
            this.termo_logico();
        }
    }

    public void fator() {
        try {
            if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Variável")) {
                this.lexico();
            } else if (this.simbolosList.get(this.token.getEndereco()).getCategoria().equals("Função")) {
                this.lexico();
                this.argumentos();
            } else if (this.token.getClasse().equals("cInt") || this.token.getClasse().equals("cReal")) {
                this.lexico();
            } else if (this.token.getClasse().equals("(")) {
                this.lexico();
                this.expressao();
                if (this.token.getClasse().equals(")")) {

                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro no fator da expressão, faltando ')' \n";
                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro no fator da expressão, símbolo inválido  \n";
            }
        } catch (Exception e) {

            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro no fator da expressão, símbolo inválido  \n";
        }
    }

    public void fator_logico() {
        if (this.token.getLexema().toLowerCase().equals("not")) {
            this.lexico();
            this.fator_logico();
        } else if (this.token.getLexema().toLowerCase().equals("true")) {

        } else if (this.token.getLexema().toLowerCase().equals("false")) {

        } else if (this.token.getLexema().equals("(")) {
            this.lexico();
            this.expressao_logica();
            if (!this.token.getLexema().equals(")")) {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro no fator logico - faltando ')' \n";
            }
            this.lexico();
        } else {
            this.relacional();
        }
    }

    public void relacional() {
        this.expressao();
        this.relacao();
        this.expressao();
    }

    public void relacao() {

        if (this.token.getLexema().equals("=") || this.token.getLexema().equals(">") || this.token.getLexema().equals("<") || this.token.getLexema().equals(">=") || this.token.getLexema().equals("<=") || this.token.getLexema().equals("<>")) {
            this.lexico();
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro envolvendo os simbolos relacionais \n";

        }
    }

    public void variavel() {

        if (this.simbolosList.get(this.token.getEndereco()).getTipo().equals("Inteiro") || (this.simbolosList.get(this.token.getEndereco()).getTipo().equals("real"))) {
        } else if (this.simbolosList.get(this.token.getEndereco()).getTipo().toLowerCase().equals("[Inteiro]") || (this.simbolosList.get(this.token.getEndereco()).getTipo().toLowerCase().equals("[Real]"))) {
            this.lexico();
            if (this.token.getLexema().equals("[")) {
                this.lexico();
                this.expressao();
                if (this.token.getLexema().equals("]")) {

                } else {
                    this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro na variável da expressão \n";

                }
            } else {
                this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro na variável da expressão \n";

            }
        } else {
            this.mensagem += "Erro: " + "(" + token.getLinha() + "," + token.getColuna() + ")" + " , Erro na variável da expressão \n";

        }

    }

}
