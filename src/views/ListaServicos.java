package views;

import control.ServicoController;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Servico;
import relatorios.RelatorioController;

public class ListaServicos extends javax.swing.JInternalFrame {

    private ServicoController serCon = new ServicoController();
    private ArrayList<Servico> servicos;
    private DefaultTableModel dTable;
    private boolean flag = true;

    public ListaServicos() {
        initComponents();
        this.povoaTabela();
        tabelaServicos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private DefaultTableModel criaTabela() {

        DefaultTableModel dTable = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        ;

        };
    return dTable;
    }

    private void povoaTabela() {
        dTable = criaTabela();
        dTable.addColumn("Código");
        dTable.addColumn("Nome");
        dTable.addColumn("Valor");
        tabelaServicos.setModel(dTable);
        servicos = serCon.getAll();

        for (Servico servico : servicos) {
            dTable.addRow(new Object[]{servico.getCod(), servico.getDescricao(), String.valueOf(servico.getValor()).replace('.', ',')});
        }
        tabelaServicos.setModel(dTable);
    }

    private void limpar() {
        tfNome.setText("");
        tfValor.setText("");
    }

    private void disableButton(JButton button1, JButton button2, JButton button3) {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }

    private void enableButton(JButton button2) {
        button2.setEnabled(true);

    }

    private boolean validaCampos() {
        if (tfNome.getText().equals("") && tfValor.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo nome e valor em branco", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        } else if (tfNome.getText().equals("") || tfValor.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Campo nome ou valor em branco", "Alerta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private int retornaLinha() {
        int linha = tabelaServicos.getSelectedRow();
        return linha;
    }

    private void pegaSelecionado() {
        int linha = tabelaServicos.getSelectedRow();
        tfNome.setText(servicos.get(linha).getDescricao());
        tfValor.setText(String.valueOf(servicos.get(linha).getValor()));
        buttonEditar.setEnabled(true);
        buttonExcluir.setEnabled(true);
        buttonAdicionar.setEnabled(true);
    }

    private Servico newServico() {
        Double i;
        if (tfValor.getText().isEmpty()) {
            i = 0.0;
        } else {
            i = Double.parseDouble(tfValor.getText().replace(',', '.'));
        }
        Servico servico = new Servico(tfNome.getText(), i);

        return servico;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPrincipal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaServicos = new javax.swing.JTable();
        buttonPDF = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tfNome = new javax.swing.JTextField();
        labelAno = new javax.swing.JLabel();
        labelAno1 = new javax.swing.JLabel();
        tfValor = new javax.swing.JFormattedTextField();
        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        toolbarCrud = new javax.swing.JToolBar();
        buttonAdicionar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonEditar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        buttonExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Serviços");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Pistao-25.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(737, 598));
        setMinimumSize(new java.awt.Dimension(737, 598));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(737, 598));

        panelPrincipal.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelPrincipal.setLayout(null);

        tabelaServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Nome", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaServicos.getTableHeader().setReorderingAllowed(false);
        tabelaServicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaServicosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaServicos);
        if (tabelaServicos.getColumnModel().getColumnCount() > 0) {
            tabelaServicos.getColumnModel().getColumn(0).setMinWidth(120);
            tabelaServicos.getColumnModel().getColumn(0).setMaxWidth(120);
            tabelaServicos.getColumnModel().getColumn(1).setMinWidth(340);
            tabelaServicos.getColumnModel().getColumn(1).setMaxWidth(340);
            tabelaServicos.getColumnModel().getColumn(2).setMinWidth(120);
            tabelaServicos.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        panelPrincipal.add(jScrollPane1);
        jScrollPane1.setBounds(20, 20, 600, 280);

        buttonPDF.setText("Gerar PDF");
        buttonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPDFActionPerformed(evt);
            }
        });
        panelPrincipal.add(buttonPDF);
        buttonPDF.setBounds(270, 310, 90, 23);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviço"));

        tfNome.setEditable(false);
        tfNome.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfNome.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tfNomeInputMethodTextChanged(evt);
            }
        });
        tfNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomeActionPerformed(evt);
            }
        });
        tfNome.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tfNomePropertyChange(evt);
            }
        });
        tfNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomeKeyTyped(evt);
            }
        });

        labelAno.setText("Nome:*");

        labelAno1.setText("Valor:*");

        tfValor.setEditable(false);
        tfValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorActionPerformed(evt);
            }
        });
        tfValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfValorKeyTyped(evt);
            }
        });

        buttonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Checado-25.png"))); // NOI18N
        buttonSalvar.setText("Salvar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });
        panelButtons.add(buttonSalvar);

        buttonLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Limpar-25.png"))); // NOI18N
        buttonLimpar.setText("Limpar");
        buttonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimparActionPerformed(evt);
            }
        });
        panelButtons.add(buttonLimpar);

        buttonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Cancelar-25.png"))); // NOI18N
        buttonCancelar.setText("Cancelar");
        buttonCancelar.setMaximumSize(null);
        buttonCancelar.setMinimumSize(null);
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });
        panelButtons.add(buttonCancelar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelAno1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelAno, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNome)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAno1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        labelTitulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Lista de Serviços");
        labelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        toolbarCrud.setBackground(new java.awt.Color(204, 204, 255));
        toolbarCrud.setFloatable(false);
        toolbarCrud.setOrientation(javax.swing.SwingConstants.VERTICAL);
        toolbarCrud.setRollover(true);

        buttonAdicionar.setBackground(new java.awt.Color(204, 204, 255));
        buttonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Adicionar-50.png"))); // NOI18N
        buttonAdicionar.setToolTipText("Adicionar");
        buttonAdicionar.setFocusable(false);
        buttonAdicionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdicionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdicionarActionPerformed(evt);
            }
        });
        toolbarCrud.add(buttonAdicionar);
        toolbarCrud.add(jSeparator1);

        buttonEditar.setBackground(new java.awt.Color(204, 204, 255));
        buttonEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Editar-50.png"))); // NOI18N
        buttonEditar.setToolTipText("Editar");
        buttonEditar.setEnabled(false);
        buttonEditar.setFocusable(false);
        buttonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEditar.setMaximumSize(new java.awt.Dimension(67, 67));
        buttonEditar.setMinimumSize(new java.awt.Dimension(67, 67));
        buttonEditar.setPreferredSize(new java.awt.Dimension(67, 67));
        buttonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });
        toolbarCrud.add(buttonEditar);

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        toolbarCrud.add(jSeparator5);

        buttonExcluir.setBackground(new java.awt.Color(204, 204, 255));
        buttonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Lixeira-50.png"))); // NOI18N
        buttonExcluir.setToolTipText("Excluir");
        buttonExcluir.setEnabled(false);
        buttonExcluir.setFocusable(false);
        buttonExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonExcluir.setMaximumSize(new java.awt.Dimension(67, 67));
        buttonExcluir.setMinimumSize(new java.awt.Dimension(67, 67));
        buttonExcluir.setPreferredSize(new java.awt.Dimension(67, 67));
        buttonExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcluirActionPerformed(evt);
            }
        });
        toolbarCrud.add(buttonExcluir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 71, Short.MAX_VALUE)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomeInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tfNomeInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomeInputMethodTextChanged

    private void tfNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomeActionPerformed

    }//GEN-LAST:event_tfNomeActionPerformed

    private void tfNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomeKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_tfNomeKeyTyped

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed
        tfNome.setText("");
        tfValor.setText("");
        tfNome.setEditable(true);
        tfValor.setEditable(true);
        flag = false;
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        flag = true;
        tfNome.setEditable(true);
        tfValor.setEditable(true);
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void tabelaServicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaServicosMouseClicked
        this.pegaSelecionado();
    }//GEN-LAST:event_tabelaServicosMouseClicked

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        Servico servico = servicos.get(retornaLinha());
        serCon.remove(servico.getCod());
        this.povoaTabela();
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void buttonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPDFActionPerformed
        RelatorioController.geraRelatorioServicos(this);
    }//GEN-LAST:event_buttonPDFActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (!this.validaCampos()) {
            return;
        }
        if (flag == true) {
            Servico servico = servicos.get(retornaLinha());
            servico.setDescricao(tfNome.getText());
            servico.setValor(Double.parseDouble(tfValor.getText().replace(',', '.')));
            serCon.altera(servico);
            this.enableButton(buttonAdicionar);
            buttonEditar.setEnabled(false);
            buttonExcluir.setEnabled(false);
            tfNome.setEditable(false);
            tfValor.setEditable(false);
            this.povoaTabela();
        } else {
            serCon.add(newServico());
            this.povoaTabela();
            this.enableButton(buttonAdicionar);
            tfNome.setEditable(false);
            tfValor.setEditable(false);
            buttonEditar.setEnabled(false);
            buttonExcluir.setEnabled(false);
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        this.limpar();
        buttonEditar.setEnabled(false);
        buttonAdicionar.setEnabled(true);
        buttonExcluir.setEnabled(false);
        tfValor.setEditable(false);
        tfNome.setEditable(false);
    }//GEN-LAST:event_buttonLimparActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void tfValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfValorKeyTyped
        this.somenteNumeros(evt);
    }//GEN-LAST:event_tfValorKeyTyped

    private void tfValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorActionPerformed

    private void tfNomePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tfNomePropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomePropertyChange
    public static void somenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "0123456789.";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton buttonPDF;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelAno1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JTable tabelaServicos;
    private javax.swing.JTextField tfNome;
    private javax.swing.JFormattedTextField tfValor;
    private javax.swing.JToolBar toolbarCrud;
    // End of variables declaration//GEN-END:variables
}
