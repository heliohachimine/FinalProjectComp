/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import Analisador.lexico.Analisador;
import Analisador.lexico.Simbolos;
import Analisador.lexico.Token;
import Analisador.sintatico.Sintatico;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author Luís Fernando
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.setLocationRelativeTo(null);

        txtFonte = TextEditor();

    }

    public JTextArea TextEditor() {
        JPanel cp = new JPanel(new BorderLayout());
        RSyntaxTextArea textArea = new RSyntaxTextArea(18, 40);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        jScrollPane2.setViewportView(sp);
        return textArea;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtFonte = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensagens = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNovo = new javax.swing.JMenuItem();
        menuAbrir = new javax.swing.JMenuItem();
        menuFechar = new javax.swing.JMenuItem();
        menuSalvar = new javax.swing.JMenuItem();
        menuSalvarComo = new javax.swing.JMenuItem();
        menuImprimir = new javax.swing.JMenuItem();
        menuSair = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuRecortar = new javax.swing.JMenuItem();
        menuCopiar = new javax.swing.JMenuItem();
        menuColar = new javax.swing.JMenuItem();
        menuLimpar = new javax.swing.JMenuItem();
        menuSelecionarTudo = new javax.swing.JMenuItem();
        menuLocalizar = new javax.swing.JMenuItem();
        menuSubstituir = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuCompilar = new javax.swing.JMenuItem();
        menuExecutar = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        menuCascata = new javax.swing.JMenuItem();
        menuLadoALado = new javax.swing.JMenuItem();
        menuOrganizarTudo = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        menuSobre = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtFonte.setColumns(20);
        txtFonte.setRows(5);
        jScrollPane2.setViewportView(txtFonte);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(333, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(210, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Fonte", jPanel1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Localização", "Classe", "Lexema"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Itens Léxicos", jPanel2);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Lexema", "Categoria", "Tipo", "Endereço"
            }
        ));
        jTable2.setEnabled(false);
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Tabela de Símbolos", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 549, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Código Objeto", jPanel4);

        txtMensagens.setColumns(20);
        txtMensagens.setRows(5);
        jScrollPane1.setViewportView(txtMensagens);

        jLabel1.setText("Mensagens");

        jMenu1.setText("Arquivo");

        menuNovo.setText("Novo");
        menuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoActionPerformed(evt);
            }
        });
        jMenu1.add(menuNovo);

        menuAbrir.setText("Abrir");
        menuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(menuAbrir);

        menuFechar.setText("Fechar");
        jMenu1.add(menuFechar);

        menuSalvar.setText("Salva");
        menuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalvar);

        menuSalvarComo.setText("Salvar Como");
        jMenu1.add(menuSalvarComo);

        menuImprimir.setText("Imprimir");
        jMenu1.add(menuImprimir);

        menuSair.setText("Sair");
        menuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSairActionPerformed(evt);
            }
        });
        jMenu1.add(menuSair);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Editar");

        menuRecortar.setText("Recortar");
        jMenu2.add(menuRecortar);

        menuCopiar.setText("Copiar");
        jMenu2.add(menuCopiar);

        menuColar.setText("Colar");
        jMenu2.add(menuColar);

        menuLimpar.setText("Limpar");
        jMenu2.add(menuLimpar);

        menuSelecionarTudo.setText("Selecionar Tudo");
        menuSelecionarTudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSelecionarTudoActionPerformed(evt);
            }
        });
        jMenu2.add(menuSelecionarTudo);

        menuLocalizar.setText("Localizar");
        jMenu2.add(menuLocalizar);

        menuSubstituir.setText("Substituir");
        jMenu2.add(menuSubstituir);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Executar");

        menuCompilar.setText("Compilar");
        menuCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompilarActionPerformed(evt);
            }
        });
        jMenu3.add(menuCompilar);

        menuExecutar.setText("Executar");
        jMenu3.add(menuExecutar);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Janela");

        menuCascata.setText("Cascata");
        jMenu4.add(menuCascata);

        menuLadoALado.setText("Lado a Lado");
        jMenu4.add(menuLadoALado);

        menuOrganizarTudo.setText("Organizar Tudo");
        jMenu4.add(menuOrganizarTudo);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Ajuda");

        menuSobre.setText("Sobre");
        menuSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSobreActionPerformed(evt);
            }
        });
        jMenu5.add(menuSobre);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNovoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuNovoActionPerformed

    private void menuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalvarActionPerformed
        final JFileChooser SaveAs = new JFileChooser();
        SaveAs.setApproveButtonText("Salvar");
        int actionDialog = SaveAs.showOpenDialog(this);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileName = new File(SaveAs.getSelectedFile() + ".txt");
        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));

            txtFonte.write(outFile);   // *** here: ***

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {
                    // one of the few times that I think that it's OK
                    // to leave this blank
                }
            }
        }
    }//GEN-LAST:event_menuSalvarActionPerformed

    private void menuSelecionarTudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSelecionarTudoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuSelecionarTudoActionPerformed

    private void menuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSairActionPerformed
        dispose();
    }//GEN-LAST:event_menuSairActionPerformed

    private void menuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAbrirActionPerformed
        try {
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int resultado = fc.showOpenDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                System.exit(1);
            }
            File fileName = fc.getSelectedFile();
            FileReader input = null;
            try {
                input = new FileReader(fileName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedReader bufRead = new BufferedReader(input);
            String linha = null;
            linha = (String) bufRead.readLine();
            txtFonte.setText("");
            while (linha != null) {
                txtFonte.append(linha + "\n");
                linha = bufRead.readLine();
            }
            bufRead.close();
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_menuAbrirActionPerformed

    private void menuCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompilarActionPerformed
       if(!txtFonte.getText().isEmpty()){
           txtMensagens.setText("");
               Analisador analisador = new Analisador(txtFonte.getText() + "\n");
        ArrayList<Token> tokenList = new ArrayList<>(analisador.getToken());
        ArrayList<Simbolos> simbolos = analisador.getSimbolos();
        DefaultTableModel itensLexicos = (DefaultTableModel) jTable1.getModel();
        DefaultTableModel tabelaSimbolos = (DefaultTableModel) jTable2.getModel();
        txtMensagens.setText(analisador.getErro());
        itensLexicos.setRowCount(0);
        tabelaSimbolos.setRowCount(0);
        for (int i = 0; i < tokenList.size(); i++) {
            Object[] dados = {"Linha: " + tokenList.get(i).getLinha() + " Coluna: " + tokenList.get(i).getColuna(), tokenList.get(i).getClasse(), tokenList.get(i).getLexema()};
            itensLexicos.addRow(dados);
        }
  
        Sintatico sintatico = new Sintatico(tokenList, simbolos,analisador.getErro() );
        txtMensagens.setText(sintatico.getMensagem());
   for (int i = 0; i < sintatico.getSimbolos().size(); i++) {
            Object[] dados = { sintatico.getSimbolos().get(i).getLexema(),sintatico.getSimbolos().get(i).getCategoria(),sintatico.getSimbolos().get(i).getTipo(), sintatico.getSimbolos().get(i).getEndereco()};
            tabelaSimbolos.addRow(dados);
        }
       }else{
                 txtMensagens.setText("Código vazio, por favor adicione um arquivo .txt através do menu ou digite o código desejado");

       }


    }//GEN-LAST:event_menuCompilarActionPerformed

    private void menuSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSobreActionPerformed
        Sobre nova = new  Sobre();
        nova.setTitle("Sobre");
        nova.setLocationRelativeTo(null);
        nova.setResizable(false);
        nova.setVisible(true);
        nova.setVisible(true);


    }//GEN-LAST:event_menuSobreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuItem menuAbrir;
    private javax.swing.JMenuItem menuCascata;
    private javax.swing.JMenuItem menuColar;
    private javax.swing.JMenuItem menuCompilar;
    private javax.swing.JMenuItem menuCopiar;
    private javax.swing.JMenuItem menuExecutar;
    private javax.swing.JMenuItem menuFechar;
    private javax.swing.JMenuItem menuImprimir;
    private javax.swing.JMenuItem menuLadoALado;
    private javax.swing.JMenuItem menuLimpar;
    private javax.swing.JMenuItem menuLocalizar;
    private javax.swing.JMenuItem menuNovo;
    private javax.swing.JMenuItem menuOrganizarTudo;
    private javax.swing.JMenuItem menuRecortar;
    private javax.swing.JMenuItem menuSair;
    private javax.swing.JMenuItem menuSalvar;
    private javax.swing.JMenuItem menuSalvarComo;
    private javax.swing.JMenuItem menuSelecionarTudo;
    private javax.swing.JMenuItem menuSobre;
    private javax.swing.JMenuItem menuSubstituir;
    private javax.swing.JTextArea txtFonte;
    private javax.swing.JTextArea txtMensagens;
    // End of variables declaration//GEN-END:variables
}
