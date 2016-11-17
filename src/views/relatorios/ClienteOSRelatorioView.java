package views.relatorios;

import control.ClienteController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cliente;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import relatorios.RelatorioController;

public class ClienteOSRelatorioView extends javax.swing.JInternalFrame {

    ClienteController cliControl = new ClienteController();
    ArrayList<Cliente> clientes;
    private int id = 0;

    public ClienteOSRelatorioView() {
        initComponents();
        this.preencheProcurar();
        AutoCompleteDecorator.decorate(comboResultados);
    }

    private void preencheProcurar() {
        clientes = cliControl.getAll();
        comboResultados.setModel(cliControl.procurar(comboTipo.getSelectedIndex()));
        comboResultados.setSelectedIndex(0);
    }

    private Boolean valida() {
        return id != 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        panelProcurar = new javax.swing.JPanel();
        labelPor = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<String>();
        comboResultados = new javax.swing.JComboBox<String>();
        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonCancelar3 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório OS/Cliente");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Usuario-25.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(767, 177));
        setMinimumSize(new java.awt.Dimension(767, 177));
        setPreferredSize(new java.awt.Dimension(767, 177));

        titulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Cliente");

        panelProcurar.setBorder(javax.swing.BorderFactory.createTitledBorder("Procurar"));

        labelPor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelPor.setText("Por:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "CPF", "RG" }));
        comboTipo.setToolTipText("");
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });

        comboResultados.setEditable(true);
        comboResultados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        comboResultados.setToolTipText("");
        comboResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboResultadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProcurarLayout = new javax.swing.GroupLayout(panelProcurar);
        panelProcurar.setLayout(panelProcurarLayout);
        panelProcurarLayout.setHorizontalGroup(
            panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcurarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboResultados, 0, 574, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        panelProcurarLayout.setVerticalGroup(
            panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcurarLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPor)
                    .addComponent(comboResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        buttonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Checado-25.png"))); // NOI18N
        buttonSalvar.setText("Gerar");
        buttonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSalvarActionPerformed(evt);
            }
        });
        panelButtons.add(buttonSalvar);

        buttonCancelar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Cancelar-25.png"))); // NOI18N
        buttonCancelar3.setText("Cancelar");
        buttonCancelar3.setMaximumSize(null);
        buttonCancelar3.setMinimumSize(null);
        buttonCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelar3ActionPerformed(evt);
            }
        });
        panelButtons.add(buttonCancelar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(titulo)
                .addGap(8, 8, 8)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (id != 0) {
            RelatorioController.geraRelatorioOs(id, this);
        } else {
            JOptionPane.showMessageDialog(this, "É necessário selecionar um cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboResultados.setModel(cliControl.procurar(comboTipo.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoActionPerformed

    private void comboResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadosActionPerformed
        if ((comboResultados.getSelectedIndex() != 0) && (comboResultados.getSelectedIndex() != -1)) {
            id = clientes.get(comboResultados.getSelectedIndex() - 1).getCodigo();
        }
    }//GEN-LAST:event_comboResultadosActionPerformed

    private void buttonCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelar3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelar3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonCancelar1;
    private javax.swing.JButton buttonCancelar2;
    private javax.swing.JButton buttonCancelar3;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JComboBox<String> comboResultados;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel labelPor;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
