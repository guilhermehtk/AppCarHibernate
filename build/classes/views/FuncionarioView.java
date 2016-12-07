package views;

import control.FuncionarioController;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Endereco;
import model.Funcionario;
import model.Login;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.mindrot.jbcrypt.BCrypt;

public class FuncionarioView extends javax.swing.JInternalFrame {

    private FuncionarioController funControl = new FuncionarioController();
    private ArrayList<Funcionario> funcionarios;

    public FuncionarioView() {
        initComponents();
        this.preencheProcurar();
        AutoCompleteDecorator.decorate(comboResultados);
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

    private void preencheProcurar() {
        funcionarios = funControl.getAll();
        comboResultados.setModel(funControl.procurar(comboTipo.getSelectedIndex()));
        comboResultados.setSelectedIndex(0);
    }

    private boolean valida() {
        return Mensagens.errosValidacao(funControl.valida(newFuncionario()));
    }

    private boolean validaPassword() {
        return Mensagens.tamanhoMinimo(String.copyValueOf(campoPassword.getPassword()), "Senha", 8);
    }

    private boolean validaConfirmaPassword() {
        return Mensagens.confirmaPassword(String.copyValueOf(campoPassword.getPassword()), String.copyValueOf(campoConfirmaPassword.getPassword()));
    }

    private boolean validaLogin() {
        return Mensagens.tamanhoMinimo(campoLogin.getText(), "Login", 6);
    }

    private boolean validaUnique() {
        return Mensagens.errosUnique(funControl.validaUniques(newFuncionario()));
    }

    private void setFuncionarioResultado(int id) {
        this.preencheProcurar();
        for (Funcionario cli : funcionarios) {
            if (cli.getCodigo() == id) {
                comboResultados.setSelectedIndex(funcionarios.indexOf(cli) + 1);
            }
        }
    }

    private void preencher(Funcionario funcionario) {
        campoNome.setText(funcionario.getNome());
        campoCpf.setText(funcionario.getCpf());
        campoRg.setText(funcionario.getRg());
        campoEmail.setText(funcionario.getEmail());
        campoTelefone.setText(funcionario.getTelefoneM());
        campoTelefoneFixo.setText(funcionario.getTelefoneF());
        comboSexo.setSelectedItem(funcionario.getSexo());
        // Endereço
        campoNumero.setText(funcionario.getEndereco().getNumero());
        campoRua.setText(funcionario.getEndereco().getRua());
        campoBairro.setText(funcionario.getEndereco().getBairro());
        campoCidade.setText(funcionario.getEndereco().getCidade());
        campoCep.setText(funcionario.getEndereco().getCep());
        campoComplemento.setText(funcionario.getEndereco().getComplemento());
        // Login
        campoLogin.setText(funcionario.getLogin().getUsuario());
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
        // Login
        campoLogin.setText("");
        campoConfirmaPassword.setText("");
        campoPassword.setText("");
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
        // Login
        campoLogin.setEditable(flag);
        campoConfirmaPassword.setEditable(flag);
        campoPassword.setEditable(flag);
    }

    private Funcionario newFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(campoNome.getText());
        for (Funcionario fun1 : funcionarios) {
            if (fun1.getCpf().equals(campoCpf.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já existente", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        funcionario.setCpf(campoCpf.getText());
        funcionario.setRg(campoRg.getText().toUpperCase());
        funcionario.setEmail(campoEmail.getText().toLowerCase());
        funcionario.setTelefoneF(campoTelefoneFixo.getText());
        funcionario.setTelefoneM(campoTelefone.getText());
        funcionario.setSexo(comboSexo.getSelectedItem().toString());
        // Endereço
        Endereco endereco = new Endereco();
        endereco.setRua(campoRua.getText());
        endereco.setNumero(campoNumero.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setCidade(campoCidade.getText());
        endereco.setComplemento(campoComplemento.getText());
        endereco.setCep(campoCep.getText());
        funcionario.setEndereco(endereco);
        // Login
        Login login = new Login();
        login.setUsuario(campoLogin.getText());
        login.setSenha(String.copyValueOf(campoConfirmaPassword.getPassword()));
        funcionario.setLogin(login);
        return funcionario;
    }

    private Funcionario alteraFuncionario(Funcionario funcionario) {
        funcionario.setNome(campoNome.getText());
        for (Funcionario fun1 : funcionarios) {
            if (fun1.getCpf().equals(campoCpf.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já existente", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        funcionario.setCpf(campoCpf.getText());
        funcionario.setRg(campoRg.getText());
        funcionario.setEmail(campoEmail.getText());
        funcionario.setTelefoneF(campoTelefoneFixo.getText());
        funcionario.setTelefoneM(campoTelefone.getText());
        funcionario.setSexo(comboSexo.getSelectedItem().toString());
        // Endereço
        Endereco endereco = funcionario.getEndereco();
        endereco.setRua(campoRua.getText());
        endereco.setNumero(campoNumero.getText());
        endereco.setBairro(campoBairro.getText());
        endereco.setCidade(campoCidade.getText());
        endereco.setComplemento(campoComplemento.getText());
        endereco.setCep(campoCep.getText());
        funcionario.setEndereco(endereco);
        // Login
        Login login = funcionario.getLogin();
        login.setUsuario(campoLogin.getText());
        login.setSenha(BCrypt.hashpw(String.copyValueOf(campoConfirmaPassword.getPassword()), BCrypt.gensalt()));
        funcionario.setLogin(login);
        return funcionario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        panelAcesso = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        campoLogin = new javax.swing.JTextField();
        campoConfirmaPassword = new javax.swing.JPasswordField();
        campoPassword = new javax.swing.JPasswordField();
        toolbarCrud = new javax.swing.JToolBar();
        buttonAdicionar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonEditar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        buttonExcluir = new javax.swing.JButton();
        panelDados = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoRg = new javax.swing.JTextField();
        campoEmail = new javax.swing.JTextField();
        comboSexo = new javax.swing.JComboBox<String>();
        campoTelefoneFixo = new javax.swing.JFormattedTextField();
        campoCpf = new javax.swing.JFormattedTextField();
        campoTelefone = new javax.swing.JFormattedTextField();
        panelEndereco = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        campoRua = new javax.swing.JTextField();
        campoBairro = new javax.swing.JTextField();
        campoNumero = new javax.swing.JTextField();
        campoCidade = new javax.swing.JTextField();
        campoComplemento = new javax.swing.JTextField();
        campoCep = new javax.swing.JFormattedTextField();
        panelProcurar = new javax.swing.JPanel();
        labelPor = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<String>();
        comboResultados = new javax.swing.JComboBox<String>();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionário");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Mecanico-25.png"))); // NOI18N

        titulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Funcionário");

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

        panelAcesso.setBorder(javax.swing.BorderFactory.createTitledBorder("Acesso"));
        panelAcesso.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("Login*:");
        panelAcesso.add(jLabel15);
        jLabel15.setBounds(70, 30, 50, 20);

        jLabel31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel31.setText("Senha*:");
        panelAcesso.add(jLabel31);
        jLabel31.setBounds(70, 60, 50, 20);

        jLabel32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel32.setText("Confirmar Senha*:");
        panelAcesso.add(jLabel32);
        jLabel32.setBounds(10, 90, 110, 20);

        campoLogin.setEditable(false);
        campoLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoLoginKeyTyped(evt);
            }
        });
        panelAcesso.add(campoLogin);
        campoLogin.setBounds(120, 30, 240, 20);

        campoConfirmaPassword.setEditable(false);
        campoConfirmaPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoConfirmaPasswordKeyTyped(evt);
            }
        });
        panelAcesso.add(campoConfirmaPassword);
        campoConfirmaPassword.setBounds(120, 90, 240, 20);

        campoPassword.setEditable(false);
        campoPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoPasswordKeyTyped(evt);
            }
        });
        panelAcesso.add(campoPassword);
        campoPassword.setBounds(120, 60, 240, 20);

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

        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        panelDados.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Nome*:");
        panelDados.add(jLabel16);
        jLabel16.setBounds(37, 30, 50, 20);

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("Sexo*:");
        panelDados.add(jLabel17);
        jLabel17.setBounds(50, 210, 35, 20);

        jLabel30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel30.setText("Telefone*:");
        panelDados.add(jLabel30);
        jLabel30.setBounds(30, 150, 60, 20);

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setText("Telefone Fixo:");
        panelDados.add(jLabel33);
        jLabel33.setBounds(10, 180, 76, 20);

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("CPF*:");
        panelDados.add(jLabel41);
        jLabel41.setBounds(47, 60, 40, 20);

        jLabel42.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel42.setText("RG*:");
        panelDados.add(jLabel42);
        jLabel42.setBounds(60, 90, 30, 20);

        jLabel43.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel43.setText("Email:");
        panelDados.add(jLabel43);
        jLabel43.setBounds(50, 120, 35, 20);

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

        jLabel44.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel44.setText("Número*:");
        panelEndereco.add(jLabel44);
        jLabel44.setBounds(40, 60, 60, 20);

        jLabel45.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel45.setText("Rua*:");
        panelEndereco.add(jLabel45);
        jLabel45.setBounds(60, 30, 40, 20);

        jLabel46.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel46.setText("Bairro*:");
        panelEndereco.add(jLabel46);
        jLabel46.setBounds(50, 120, 41, 20);

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setText("Cidade*:");
        panelEndereco.add(jLabel47);
        jLabel47.setBounds(40, 150, 48, 20);

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setText("CEP:");
        panelEndereco.add(jLabel48);
        jLabel48.setBounds(60, 180, 40, 20);

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setText("Complemento:");
        panelEndereco.add(jLabel49);
        jLabel49.setBounds(10, 90, 82, 20);

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
                .addComponent(comboResultados, 0, 573, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(panelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                            .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(227, 227, 227))
                        .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(titulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(58, 58, 58)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(panelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(154, 154, 154)
                            .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (this.valida() && this.validaLogin() && this.validaPassword() && this.validaConfirmaPassword()) {
            if (comboResultados.getSelectedIndex() == 0) {
                if (this.validaUnique()) {
                    Funcionario fun = newFuncionario();
                    int id = 0;
                    if (fun != null) {
                        id = funControl.add(fun);
                    } else {
                        return;
                    }
                    if (id != 0) {
                        Mensagens.sucessoCreate();
                        this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
                        this.setFuncionarioResultado(id);
                    }
                }
            } else {
                Funcionario fun2 = alteraFuncionario(funcionarios.get(comboResultados.getSelectedIndex() - 1));
                if (fun2 != null) {
                    funControl.altera(fun2);
                } else {
                    return;
                }

                this.setFuncionarioResultado(funcionarios.get(comboResultados.getSelectedIndex() - 1).getCodigo());
                Mensagens.sucessoAlterar();
                this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
            }
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        this.limpar();
        this.enableButton(buttonAdicionar, buttonExcluir, buttonEditar);
    }//GEN-LAST:event_buttonLimparActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed
        this.limpar();
        this.editable(true);
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void campoNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNomeKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoNomeKeyTyped

    private void campoRgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRgKeyTyped
        Mensagens.restringirTamanho(evt, 14);
    }//GEN-LAST:event_campoRgKeyTyped

    private void campoEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEmailKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoEmailKeyTyped

    private void comboSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboSexoActionPerformed

    private void campoTelefoneFixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneFixoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneFixoActionPerformed

    private void campoCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCpfActionPerformed

    private void campoTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTelefoneActionPerformed

    private void campoRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoRuaKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoRuaKeyTyped

    private void campoBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoBairroKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoBairroKeyTyped

    private void campoNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNumeroActionPerformed

    }//GEN-LAST:event_campoNumeroActionPerformed

    private void campoNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoNumeroKeyTyped
        Mensagens.somenteNumeros(evt);
        Mensagens.restringirTamanho(evt, 11);
    }//GEN-LAST:event_campoNumeroKeyTyped

    private void campoCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCidadeKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoCidadeKeyTyped

    private void campoComplementoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoComplementoKeyTyped
        Mensagens.restringirTamanho(evt, 25);
    }//GEN-LAST:event_campoComplementoKeyTyped

    private void campoCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCepActionPerformed

    private void campoLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoLoginKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoLoginKeyTyped

    private void campoPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoPasswordKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoPasswordKeyTyped

    private void campoConfirmaPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoConfirmaPasswordKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_campoConfirmaPasswordKeyTyped

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboResultados.setModel(funControl.procurar(comboTipo.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            this.editable(true);
            this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum funcionário para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            funControl.remove(funcionarios.get(comboResultados.getSelectedIndex() - 1).getCodigo());
            this.editable(false);
            this.limpar();
            this.preencheProcurar();
            Mensagens.sucessoDelete();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum funcionário para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void comboResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadosActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            this.preencher(funcionarios.get(comboResultados.getSelectedIndex() - 1));
            this.editable(false);
            this.enableButton(buttonAdicionar, buttonEditar, buttonExcluir);
        }
    }//GEN-LAST:event_comboResultadosActionPerformed


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
    private javax.swing.JPasswordField campoConfirmaPassword;
    private javax.swing.JFormattedTextField campoCpf;
    private javax.swing.JTextField campoEmail;
    private javax.swing.JTextField campoLogin;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoNumero;
    private javax.swing.JPasswordField campoPassword;
    private javax.swing.JTextField campoRg;
    private javax.swing.JTextField campoRua;
    private javax.swing.JFormattedTextField campoTelefone;
    private javax.swing.JFormattedTextField campoTelefoneFixo;
    private javax.swing.JComboBox<String> comboResultados;
    private javax.swing.JComboBox<String> comboSexo;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JLabel labelPor;
    private javax.swing.JPanel panelAcesso;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelEndereco;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JLabel titulo;
    private javax.swing.JToolBar toolbarCrud;
    // End of variables declaration//GEN-END:variables
}
