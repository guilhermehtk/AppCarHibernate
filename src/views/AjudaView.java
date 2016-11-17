package views;

public class AjudaView extends javax.swing.JInternalFrame {
    
    public AjudaView() {
        initComponents();
    }
    private void nulaTela(){
        TelaAjuda.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        buttonServico = new javax.swing.JButton();
        buttoOs = new javax.swing.JButton();
        buttonMecanico = new javax.swing.JButton();
        buttonUsuario = new javax.swing.JButton();
        buttonCarro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TelaAjuda = new javax.swing.JTextArea();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ajuda");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Help-25.png"))); // NOI18N

        buttonServico.setBackground(new java.awt.Color(204, 204, 255));
        buttonServico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Pistao-35.png"))); // NOI18N
        buttonServico.setText("Serviço");
        buttonServico.setToolTipText("Lista de Serviço");
        buttonServico.setFocusable(false);
        buttonServico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonServico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonServico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonServicoActionPerformed(evt);
            }
        });

        buttoOs.setBackground(new java.awt.Color(204, 204, 255));
        buttoOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/OS-35.png"))); // NOI18N
        buttoOs.setText("Ordem Serviço");
        buttoOs.setToolTipText("Ordem de Serviço");
        buttoOs.setFocusable(false);
        buttoOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttoOs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttoOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoOsActionPerformed(evt);
            }
        });

        buttonMecanico.setBackground(new java.awt.Color(204, 204, 255));
        buttonMecanico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Mecanico-35.png"))); // NOI18N
        buttonMecanico.setText("Funcionário");
        buttonMecanico.setToolTipText("Mecânico");
        buttonMecanico.setFocusable(false);
        buttonMecanico.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonMecanico.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMecanicoActionPerformed(evt);
            }
        });

        buttonUsuario.setBackground(new java.awt.Color(204, 204, 255));
        buttonUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Usuario-35.png"))); // NOI18N
        buttonUsuario.setText("Cliente");
        buttonUsuario.setToolTipText("Usuários");
        buttonUsuario.setFocusable(false);
        buttonUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonUsuario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUsuarioActionPerformed(evt);
            }
        });

        buttonCarro.setBackground(new java.awt.Color(204, 204, 255));
        buttonCarro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Carro-35.png"))); // NOI18N
        buttonCarro.setText("Carro");
        buttonCarro.setToolTipText("Carro");
        buttonCarro.setFocusable(false);
        buttonCarro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonCarro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCarroActionPerformed(evt);
            }
        });

        TelaAjuda.setEditable(false);
        TelaAjuda.setColumns(20);
        TelaAjuda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TelaAjuda.setLineWrap(true);
        TelaAjuda.setRows(5);
        jScrollPane1.setViewportView(TelaAjuda);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonServico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttoOs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonCarro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonMecanico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonCarro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttoOs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonServico, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonMecanico)
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUsuarioActionPerformed
        nulaTela();
        TelaAjuda.setText("Procurar: \nPara procurar um Cliente, você deve selecionar pelo o que você deseja procurar seja Código, CPF, RG, Nome."
                + "\n\n"
                + "Adicionar:\nPara adicionar um cliente, você deve pressionar o botão verde(Cruz), apos isso você deve preencher os campos requisitados e pressionar o salvar "
                + "\n\n"
                + "Editar:\nPara editar, você precisa selecionar um cliente na tabela procurar.Logo após, você deve pressionar o botão de editar(Lápis), fazer as alterações desejadas, e pressionar o salvar"
                + "\n\n"
                + "Deletar\nPara excluir, você precisa selecionar um cliente na tabela procurar.Logo após, você deve pressionar o botão de excluir(Lixeira)");
    }//GEN-LAST:event_buttonUsuarioActionPerformed

    private void buttonCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCarroActionPerformed
        nulaTela();
        TelaAjuda.setText("Procurar: \nPara procurar um Carro, você deve selecionar pelo o que você deseja procurar seja Código, Placa, Chassi, Dono."
                + "\n\n"
                + "Adicionar:\nPara adicionar um carro, você deve pressionar o botão verde(Cruz), apos isso você deve preencher os campos requisitados e pressionar o salvar "
                + "\n\n"
                + "Editar:\nPara editar, você precisa selecionar um carro na tabela procurar.Logo após, você deve pressionar o botão de editar(Lápis), fazer as alterações desejadas, e pressionar o salvar"
                + "\n\n"
                + "Deletar\nPara excluir, você precisa selecionar um carro na tabela procurar.Logo após, você deve pressionar o botão de excluir(Lixeira)");
    }//GEN-LAST:event_buttonCarroActionPerformed

    private void buttonServicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonServicoActionPerformed
        nulaTela();
        TelaAjuda.setText( "Adicionar:\nPara adicionar um serviço você deve preencher os campos requisitados e pressionar o salvar, após isso você deve pressionar o botão verde(Cruz) "
                + "\n\n"
                + "Editar:\nPara editar, você precisa selecionar um serviço na tabela.Logo após, você deve pressionar o botão de editar(Lápis), fazer as alterações desejadas, e pressionar o salvar"
                + "\n\n"
                + "Deletar\nPara excluir, você precisa selecionar um serviço na tabela.Logo após, você deve pressionar o botão de excluir(Lixeira)");
    }//GEN-LAST:event_buttonServicoActionPerformed

    private void buttoOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoOsActionPerformed
        nulaTela();
        TelaAjuda.setText("Procurar: \nPara procurar uma Ordem de Serviço, você deve selecionar pelo o que você deseja procurar seja Código, Cliente, Carro, Data."
                + "\n\n"
                + "Adicionar:\nPara adicionar uma ordem de serviço, você deve pressionar o botão verde(Cruz).Você também deve preencher os dados requisitados, e após isso deve pressionar o botão de adicionar, selecionar os serviços desejados, pressionar o salvar, e por fim pressionar o salvar para salvar sua ordem de serviço "
                + "\n\n"
                + "Editar:\nPara editar, você precisa selecionar uma ordem de serviço na tabela procurar.Logo após, você deve pressionar o botão de editar(Lápis), fazer as alterações desejadas, e pressionar o salvar"
                + "\n\n"
                + "Deletar\nPara excluir, você precisa selecionar uma ordem de serviço na tabela procurar.Logo após, você deve pressionar o botão de excluir(Lixeira)");
    }//GEN-LAST:event_buttoOsActionPerformed

    private void buttonMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMecanicoActionPerformed
        nulaTela();
        TelaAjuda.setText("Procurar: \nPara procurar um Funcionário, você deve selecionar pelo o que você deseja procurar seja Código, CPF, RG, Nome."
                + "\n\n"
                + "Adicionar:\nPara adicionar um funcionário, você deve pressionar o botão verde(Cruz), apos isso você deve preencher os campos requisitados e pressionar o salvar "
                + "\n\n"
                + "Editar:\nPara editar, você precisa selecionar um funcionário na tabela procurar.Logo após, você deve pressionar o botão de editar(Lápis), fazer as alterações desejadas, e pressionar o salvar"
                + "\n\n"
                + "Deletar\nPara excluir, você precisa selecionar um funcionário na tabela procurar.Logo após, você deve pressionar o botão de excluir(Lixeira)");
    }//GEN-LAST:event_buttonMecanicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea TelaAjuda;
    private javax.swing.JButton buttoOs;
    private javax.swing.JButton buttonCarro;
    private javax.swing.JButton buttonMecanico;
    private javax.swing.JButton buttonServico;
    private javax.swing.JButton buttonUsuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
