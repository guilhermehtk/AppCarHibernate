package views;

import control.ClienteController;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Endereco;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class ClienteView extends javax.swing.JInternalFrame {

    private ClienteController cliControl = new ClienteController();
    private ArrayList<Cliente> clientes;

    public ClienteView() {
        initComponents();
        this.preencheProcurar();
        AutoCompleteDecorator.decorate(comboResultados);
    }

    private void preencheProcurar() {
        clientes = cliControl.getAll();
        comboResultados.setModel(cliControl.procurar(comboTipo.getSelectedIndex()));
        comboResultados.setSelectedIndex(0);
    }

    private boolean valida() {
        return Mensagens.errosValidacao(cliControl.valida(newCliente()));
    }

    private boolean validaUnique() {
        return Mensagens.errosUnique(cliControl.validaUniques(newCliente()));
    }

    private void disableButton(JButton button1, JButton button2, JButton button3) {
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);
    }

    private void enableButton(JButton button1, JButton button2, JButton button3) {
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
    }

    private void setClientesResultado(int id) {
        this.preencheProcurar();
        for (Cliente cli : clientes) {
            if (cli.getCodigo() == id) {
                comboResultados.setSelectedIndex(clientes.indexOf(cli) + 1);
            }
        }
    }

    private void preencher(Cliente cliente) {
        campoNome.setText(cliente.getNome());
        campoCpf.setText(cliente.getCpf());
        campoRg.setText(cliente.getRg());
        campoEmail.setText(cliente.getEmail());
        campoTelefone.setText(cliente.getTelefoneM());
        campoTelefoneFixo.setText(cliente.getTelefoneF());
        comboSexo.setSelectedItem(cliente.getSexo());
        // Endereço
        campoNumero.setText(cliente.getEndereco().getNumero());
        campoRua.setText(cliente.getEndereco().getRua());
        campoBairro.setText(cliente.getEndereco().getBairro());
        campoCidade.setText(cliente.getEndereco().getCidade());
        campoCep.setText(cliente.getEndereco().getCep());
        campoComplemento.setText(cliente.getEndereco().getComplemento());
    }

    private void limpar() {
        campoNome.setText("");
        campoCpf.setText("");
        campoRg.setText("");
        campoEmail.setText("");
        campoTelefoneFixo.setText("");
        campoTelefone.setText("");
        comboSexo.setSelectedItem("");
        // Endereço
        campoRua.setText("");
        campoNumero.setText("");
        campoBairro.setText("");
        campoCidade.setText("");
        campoCep.setText("");
        campoComplemento.setText("");
        // Procurar
        comboTipo.setSelectedIndex(0);
        comboResultados.setSelectedIndex(0);
    }

    private void editable(boolean flag) {
        campoNome.setEditable(flag);
        campoCpf.setEditable(flag);
        campoRg.setEditable(flag);
        campoEmail.setEditable(flag);
        campoTelefone.setEditable(flag);
        campoTelefoneFixo.setEditable(flag);
        comboSexo.setEnabled(flag);
        // Endereço
        campoRua.setEditable(flag);
        campoNumero.setEditable(flag);
        campoBairro.setEditable(flag);
        campoCidade.setEditable(flag);
        campoCep.setEditable(flag);
        campoComplemento.setEditable(flag);
    }

    private Cliente newCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome(campoNome.getText());
        cliente.setCpf(campoCpf.getText());
        cliente.setRg(campoRg.getText().toUpperCase());
        cliente.setEmail(campoEmail.getText().toLowerCase());
        cliente.setTelefoneF(campoTelefoneFixo.getText());
        cliente.setTelefoneM(campoTelefone.getText());
        cliente.setSexo(comboSexo.getSelectedItem().toString());
        // Endereço
        Endereco endereco = new Endereco();
        endereco.setRua(campoRua.getText());
        endereco.setNumero(campoNumero.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setCidade(campoCidade.getText());
        endereco.setComplemento(campoComplemento.getText());
        endereco.setCep(campoCep.getText());
        cliente.setEndereco(endereco);
        return cliente;
    }

    private Cliente alteraCliente(Cliente cliente) {
        cliente.setNome(campoNome.getText());
        cliente.setCpf(campoCpf.getText());
        cliente.setRg(campoRg.getText());
        cliente.setEmail(campoEmail.getText());
        cliente.setTelefoneF(campoTelefoneFixo.getText());
        cliente.setTelefoneM(campoTelefone.getText());
        cliente.setSexo(comboSexo.getSelectedItem().toString());
        // Endereço
        Endereco endereco = cliente.getEndereco();
        endereco.setRua(campoRua.getText());
        endereco.setNumero(campoNumero.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setCidade(campoCidade.getText());
        endereco.setComplemento(campoComplemento.getText());
        endereco.setCep(campoCep.getText());
        cliente.setEndereco(endereco);
        return cliente;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        panelProcurar = new javax.swing.JPanel();
        labelPor = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<String>();
        comboResultados = new javax.swing.JComboBox<String>();
        panelDados = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoRg = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<String>();
        campoTelefoneFixo = new javax.swing.JFormattedTextField();
        campoCpf = new javax.swing.JFormattedTextField();
        campoTelefone = new javax.swing.JFormattedTextField();
        panelEndereco = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        campoBairro = new javax.swing.JTextField();
        campoNumero = new javax.swing.JTextField();
        campoCidade = new javax.swing.JTextField();
        campoComplemento = new javax.swing.JTextField();
        campoCep = new javax.swing.JFormattedTextField();
        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        toolbarCrud = new javax.swing.JToolBar();
        buttonAdicionar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonEditar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        buttonExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cliente");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Usuario-25.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(826, 450));
        setMinimumSize(new java.awt.Dimension(826, 450));
        setPreferredSize(new java.awt.Dimension(826, 450));

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

        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        panelDados.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Nome*:");
        panelDados.add(jLabel7);
        jLabel7.setBounds(37, 30, 50, 20);

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Sexo*:");
        panelDados.add(jLabel8);
        jLabel8.setBounds(50, 210, 35, 20);

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setText("Telefone*:");
        panelDados.add(jLabel10);
        jLabel10.setBounds(30, 150, 60, 20);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Telefone Fixo:");
        panelDados.add(jLabel11);
        jLabel11.setBounds(10, 180, 76, 20);

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("CPF*:");
        panelDados.add(jLabel12);
        jLabel12.setBounds(47, 60, 40, 20);

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("RG*:");
        panelDados.add(jLabel13);
        jLabel13.setBounds(60, 90, 30, 20);

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setText("Email:");
        panelDados.add(jLabel14);
        jLabel14.setBounds(50, 120, 35, 20);

        campoNome.setEditable(false);
        campoNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNomeKeyTyped(evt);
            }
        });
        panelDados.add(campoNome);
        campoNome.setBounds(90, 30, 240, 20);

        campoRg.setEditable(false);
        campoRg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoRgKeyTyped(evt);
            }
        });
        panelDados.add(campoRg);
        campoRg.setBounds(90, 90, 240, 20);

        campoEmail.setEditable(false);
        campoEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoEmailKeyTyped(evt);
            }
        });
        panelDados.add(campoEmail);
        campoEmail.setBounds(90, 120, 240, 20);

        comboSexo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboSexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino", "Outro" }));
        comboSexo.setEnabled(false);
        comboSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboSexoActionPerformed(evt);
            }
        });
        panelDados.add(comboSexo);
        comboSexo.setBounds(90, 210, 112, 20);

        campoTelefoneFixo.setEditable(false);
        try {
            campoTelefoneFixo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefoneFixo.setToolTipText("");
        campoTelefoneFixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneFixoActionPerformed(evt);
            }
        });
        panelDados.add(campoTelefoneFixo);
        campoTelefoneFixo.setBounds(90, 180, 240, 20);

        campoCpf.setEditable(false);
        try {
            campoCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCpfActionPerformed(evt);
            }
        });
        panelDados.add(campoCpf);
        campoCpf.setBounds(90, 60, 240, 20);

        campoTelefone.setEditable(false);
        try {
            campoTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoTelefone.setToolTipText("");
        campoTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTelefoneActionPerformed(evt);
            }
        });
        panelDados.add(campoTelefone);
        campoTelefone.setBounds(90, 150, 240, 20);

        panelEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));
        panelEndereco.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setText("Número*:");
        panelEndereco.add(jLabel9);
        jLabel9.setBounds(40, 60, 60, 20);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Rua*:");
        panelEndereco.add(jLabel18);
        jLabel18.setBounds(60, 30, 40, 20);

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setText("Bairro*:");
        panelEndereco.add(jLabel19);
        jLabel19.setBounds(50, 120, 41, 20);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Cidade*:");
        panelEndereco.add(jLabel20);
        jLabel20.setBounds(40, 150, 48, 20);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setText("CEP:");
        panelEndereco.add(jLabel21);
        jLabel21.setBounds(60, 180, 40, 20);

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("Complemento:");
        panelEndereco.add(jLabel22);
        jLabel22.setBounds(10, 90, 82, 20);

        campoRua.setEditable(false);
        campoRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoRuaKeyTyped(evt);
            }
        });
        panelEndereco.add(campoRua);
        campoRua.setBounds(100, 30, 227, 20);

        campoBairro.setEditable(false);
        campoBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoBairroKeyTyped(evt);
            }
        });
        panelEndereco.add(campoBairro);
        campoBairro.setBounds(100, 120, 227, 20);

        campoNumero.setEditable(false);
        campoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNumeroActionPerformed(evt);
            }
        });
        campoNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoNumeroKeyTyped(evt);
            }
        });
        panelEndereco.add(campoNumero);
        campoNumero.setBounds(100, 60, 227, 20);

        campoCidade.setEditable(false);
        campoCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCidadeKeyTyped(evt);
            }
        });
        panelEndereco.add(campoCidade);
        campoCidade.setBounds(100, 150, 227, 20);

        campoComplemento.setEditable(false);
        campoComplemento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoComplementoKeyTyped(evt);
            }
        });
        panelEndereco.add(campoComplemento);
        campoComplemento.setBounds(100, 90, 227, 20);

        campoCep.setEditable(false);
        try {
            campoCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        campoCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCepActionPerformed(evt);
            }
        });
        panelEndereco.add(campoCep);
        campoCep.setBounds(100, 180, 227, 20);

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
        buttonEditar.setFocusable(false);
        buttonEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEditarActionPerformed(evt);
            }
        });
        toolbarCrud.add(buttonEditar);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        toolbarCrud.add(jSeparator2);

        buttonExcluir.setBackground(new java.awt.Color(204, 204, 255));
        buttonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Lixeira-50.png"))); // NOI18N
        buttonExcluir.setToolTipText("Excluir");
        buttonExcluir.setFocusable(false);
        buttonExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(titulo)
                .addGap(8, 8, 8)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSexoActionPerformed

    private void campoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNumeroActionPerformed

    }//GEN-LAST:event_campoNumeroActionPerformed

    private void campoNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumeroKeyTyped
        Mensagens.somenteNumeros(evt);
        Mensagens.restringirTamanho(evt, 11);
    }//GEN-LAST:event_campoNumeroKeyTyped

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (this.valida()) {
            if (comboResultados.getSelectedIndex() == 0) {
                if (this.validaUnique()) {
                    int id = cliControl.add(newCliente());
                    if (id != 0) {
                        Mensagens.sucessoCreate();
                        this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
                        this.setClientesResultado(id);
                    }
                }
            } else {
                cliControl.altera(alteraCliente(clientes.get(comboResultados.getSelectedIndex() - 1)));
                this.setClientesResultado(clientes.get(comboResultados.getSelectedIndex() - 1).getCodigo());
                Mensagens.sucessoAlterar();
                this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
            }
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        this.limpar();
        this.enableButton(buttonAdicionar,buttonExcluir,buttonEditar);
    }//GEN-LAST:event_buttonLimparActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed
        this.limpar();
        this.editable(true);
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboResultados.setModel(cliControl.procurar(comboTipo.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoActionPerformed

    private void comboResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadosActionPerformed
        if ((comboResultados.getSelectedIndex() != 0) && (comboResultados.getSelectedIndex() != -1)) {
            this.preencher(clientes.get(comboResultados.getSelectedIndex() - 1));
            this.editable(false);
        }
    }//GEN-LAST:event_comboResultadosActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        if (comboResultados.getSelectedIndex() != 0) {
            this.editable(true);
            this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum cliente para editar!", "Erro", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (comboResultados.getSelectedIndex() != 0) {
            cliControl.remove(clientes.get(comboResultados.getSelectedIndex() - 1).getCodigo());
            this.editable(false);
            this.limpar();
            this.preencheProcurar();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum cliente para excluir!", "Erro", JOptionPane.PLAIN_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void campoTelefoneFixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneFixoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneFixoActionPerformed

    private void campoCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpfActionPerformed

    private void campoTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneActionPerformed

    private void campoCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCepActionPerformed

    private void campoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNomeKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoNomeKeyTyped

    private void campoRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRgKeyTyped
        Mensagens.restringirTamanho(evt, 14);
    }//GEN-LAST:event_campoRgKeyTyped

    private void campoRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRuaKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoRuaKeyTyped

    private void campoBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBairroKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoBairroKeyTyped

    private void campoCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCidadeKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoCidadeKeyTyped

    private void campoComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoComplementoKeyTyped
        Mensagens.restringirTamanho(evt, 25);
    }//GEN-LAST:event_campoComplementoKeyTyped

    private void campoEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEmailKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoEmailKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JTextField campoBairro;
    private javax.swing.JFormattedTextField campoCep;
    private javax.swing.JTextField campoCidade;
    private javax.swing.JTextField campoComplemento;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JTextField campoRg;
    private javax.swing.JTextField campoRua;
    private javax.swing.JFormattedTextField campoTelefone;
    private javax.swing.JFormattedTextField campoTelefoneFixo;
    private javax.swing.JComboBox<String> comboResultados;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel labelPor;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JLabel titulo;
    private javax.swing.JToolBar toolbarCrud;
    // End of variables declaration//GEN-END:variables
}
