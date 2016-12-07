package views;

import control.CarroController;
import control.ClienteController;
import control.FuncionarioController;
import control.OrdemServicoController;
import control.ServicoController;
import control.Servico_OSController;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Carro;
import model.Cliente;
import model.OrdemServico;
import model.Servico;
import model.Servico_OS;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import relatorios.RelatorioController;

public class OrdensServicoView extends javax.swing.JInternalFrame {

    private DefaultTableModel dTable;
    private TabelaServicos ts;

    private ClienteController cliControl = new ClienteController();
    private CarroController carControl = new CarroController();
    private OrdemServicoController osControl = new OrdemServicoController();
    private ServicoController serControl = new ServicoController();
    private Servico_OSController serOS = new Servico_OSController();

    private ArrayList<Carro> carros;
    private ArrayList<Cliente> clientes;
    private ArrayList<OrdemServico> ordens;
    ArrayList<Servico> servicos = new ArrayList<>();

    private int funcionario;

    public OrdensServicoView(int funcionario) {
        initComponents();
        this.preencheProcurar();
        AutoCompleteDecorator.decorate(comboResultados);
        AutoCompleteDecorator.decorate(comboSelectCarro);
        AutoCompleteDecorator.decorate(comboSelectCliente);
        this.funcionario = funcionario;
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
        ordens = osControl.getAll();
        clientes = cliControl.getAll();
        carros = carControl.getAll();
        comboResultados.setModel(osControl.procurar(comboTipo.getSelectedIndex()));
        comboSelectCarro.setModel(carControl.procurar(comboTipoCarro.getSelectedIndex()));
        comboSelectCliente.setModel(cliControl.procurar(comboTipoCliente.getSelectedIndex()));
        comboResultados.setSelectedIndex(0);
        comboSelectCarro.setSelectedIndex(0);
        comboSelectCliente.setSelectedIndex(0);
    }

    public void povoaTabela() {
        dTable = criaTabela();
        dTable.addColumn("Código");
        dTable.addColumn("Nome");
        dTable.addColumn("Valor");
        tableServicos.setModel(dTable);
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

    public void setServicos(ArrayList<Servico> servicos) {
        for (Servico servico : servicos) {
            this.servicos.add(servico);
            dTable.addRow(new Object[]{servico.getCod(), servico.getDescricao(), servico.getValor()});
        }
        tableServicos.setModel(dTable);
        this.atualizaValor();
    }

    public void setServicosOS(ArrayList<Servico> servicos) {
        dTable = criaTabela();
        povoaTabela();
        for (Servico servico : servicos) {
            dTable.addRow(new Object[]{servico.getCod(), servico.getDescricao(), servico.getValor()});
        }
        tableServicos.setModel(dTable);
        this.atualizaValor();
    }

    public void atualizaValor() {
        double a = 0;
        for (int i = 0; i < dTable.getRowCount(); i++) {
            a = a + (double) dTable.getValueAt(i, 2);
        }
        labelValor.setText(String.valueOf(a));
    }

    private void preencher(OrdemServico os) {
        servicos = new ArrayList<>();
        labelCodigo.setText(String.valueOf(os.getCod()));
        labelData.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm").format(os.getData()));
        buttonGerarRecibo.setEnabled(true);
        buttonGerarOrcamento.setEnabled(true);
        switch (os.getSituacao()) {
            case 1:
                // Orçamento
                cbSituacao.setSelectedItem("Orçamento");
                break;
            case 2:
                // Em Andamento
                cbSituacao.setSelectedItem("Aberta");
                break;
            case 3:
                // Finalizada
                cbSituacao.setSelectedItem("Fechada");
                break;
            case 4:
                // Cancelada
                cbSituacao.setSelectedItem("Cancelada");
                break;
        }
        labelValor.setText("");
        tfDescricao.setText(os.getDescricao());
        tfTipo.setText(os.getTipo());
        comboTipoCarro.setSelectedIndex(0);
        comboTipoCliente.setSelectedIndex(0);
        for (Carro carro : carros) {
            if (carro.getCod() == os.getCarro().getCod()) {
                comboSelectCarro.setSelectedIndex(carros.indexOf(carro) + 1);
            }
        }
        for (Cliente cliente : clientes) {
            if (cliente.getCodigo() == os.getCliente().getCodigo()) {
                comboSelectCliente.setSelectedIndex(clientes.indexOf(cliente) + 1);
            }
        }
        this.setServicosOS(this.getServicosOS(os.getCod()));
        switch (cbSituacao.getSelectedIndex()) {
            case 0:
                buttonGerarOrcamento.setEnabled(true);
                buttonGerarRecibo.setEnabled(false);
                break;
            case 1:
                buttonGerarRecibo.setEnabled(true);
                buttonGerarOrcamento.setEnabled(false);
                break;
            default:
                buttonGerarOrcamento.setEnabled(false);
                buttonGerarRecibo.setEnabled(false);
                break;
        }
    }

    private ArrayList<Servico> getServicosOS(int os) {
        for (Servico_OS ser : serOS.getAllOS(os)) {
            Servico servico = ser.getServico();
            servicos.add(servico);
        }
        return servicos;
    }

    private void limpar() {
        labelCodigo.setText("");
        labelData.setText("");
        cbSituacao.setSelectedIndex(0);
        labelValor.setText("");
        tfTipo.setText("");
        tfDescricao.setText("");
        comboResultados.setSelectedIndex(0);
        comboSelectCarro.setSelectedIndex(0);
        comboSelectCliente.setSelectedIndex(0);
        this.povoaTabela();
        servicos = new ArrayList<>();
    }

    private void editable(boolean flag) {
        buttonAdd.setEnabled(flag);
        buttonExc.setEnabled(flag);
        tfTipo.setEditable(flag);
        tfDescricao.setEditable(flag);
        comboTipoCarro.setEnabled(flag);
        comboTipoCliente.setEnabled(flag);
        comboSelectCarro.setEnabled(flag);
        comboSelectCliente.setEnabled(flag);
        tableServicos.setEnabled(flag);
        cbSituacao.setEnabled(flag);
    }

    private OrdemServico newOS() {
        OrdemServico os = new OrdemServico();
        // Tipo e Data definidos no Controller
        if (comboSelectCarro.getSelectedIndex() <= 0) {
            os.setCarro(null);
        } else {
            os.setCarro(carros.get(comboSelectCarro.getSelectedIndex() - 1));
        }
        if (comboSelectCliente.getSelectedIndex() <= 0) {
            os.setCliente(null);
        } else {
            os.setCliente(clientes.get(comboSelectCliente.getSelectedIndex() - 1));
        }
        os.setTipo(tfTipo.getText());
        os.setDescricao(tfDescricao.getText());
        return os;
    }

    private OrdemServico alteraOS(OrdemServico os) {
        if (comboSelectCarro.getSelectedIndex() == 0) {
            os.setCarro(null);
        } else {
            os.setCarro(carros.get(comboSelectCarro.getSelectedIndex() - 1));
        }
        if (comboSelectCliente.getSelectedIndex() == 0) {
            os.setCliente(null);
        } else {
            os.setCliente(clientes.get(comboSelectCliente.getSelectedIndex() - 1));
        }
        switch (cbSituacao.getSelectedIndex()) {
            case 0:
                // Orçamento
                os.setSituacao(1);
                break;
            case 1:
                // Aberta
                os.setSituacao(2);
                break;
            case 2:
                // Fechada
                os.setSituacao(3);
                break;
            case 3:
                // Cancelada
                os.setSituacao(4);
                break;
        }
        os.setTipo(tfTipo.getText());
        os.setDescricao(tfDescricao.getText());
        return os;
    }

    private boolean valida() {
        return Mensagens.errosValidacao(osControl.valida(newOS()));
    }

    private void setOSResultado(int id) {
        this.preencheProcurar();
        for (OrdemServico os : ordens) {
            if (os.getCod() == id) {
                comboResultados.setSelectedIndex(ordens.indexOf(os) + 1);
            }
        }
    }

    private void addServicos_OS(int osCod) {
        for (Servico ser : servicos) {
            Servico_OS os = new Servico_OS(ser, osControl.get(osCod), new FuncionarioController().get(funcionario));
            serOS.add(os);
        }
    }

    private void alteraServicos(int osCod) {
        for (Servico_OS serv : serOS.getAllOS(osCod)) {
            serOS.remove(serv.getCod());
        }
        this.addServicos_OS(osCod);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        panelProcurar = new javax.swing.JPanel();
        labelPor = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<>();
        comboResultados = new javax.swing.JComboBox<>();
        toolbarCrud = new javax.swing.JToolBar();
        buttonAdicionar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        buttonEditar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        buttonExcluir = new javax.swing.JButton();
        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        panelDados = new javax.swing.JPanel();
        labelMarca1 = new javax.swing.JLabel();
        tfTipo = new javax.swing.JTextField();
        comboTipoCarro = new javax.swing.JComboBox<>();
        comboSelectCarro = new javax.swing.JComboBox<>();
        comboSelectCliente = new javax.swing.JComboBox<>();
        comboTipoCliente = new javax.swing.JComboBox<>();
        labelMarca5 = new javax.swing.JLabel();
        labelMarca4 = new javax.swing.JLabel();
        labelMarca2 = new javax.swing.JLabel();
        tfDescricao = new javax.swing.JTextField();
        panelTabelaServicos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableServicos = new javax.swing.JTable();
        buttonExc = new javax.swing.JButton();
        buttonAdd = new javax.swing.JButton();
        panelValor = new javax.swing.JPanel();
        jLabel0 = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelData = new javax.swing.JLabel();
        cbSituacao = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        buttonGerarRecibo = new javax.swing.JButton();
        buttonGerarOrcamento = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordem de Serviço");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/OS-25.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(667, 653));
        setMinimumSize(new java.awt.Dimension(667, 653));
        setPreferredSize(new java.awt.Dimension(667, 653));

        titulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Ordem de Serviço");

        panelProcurar.setBorder(javax.swing.BorderFactory.createTitledBorder("Procurar"));

        labelPor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelPor.setText("Por:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Cliente", "Carro", "Data" }));
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
                .addComponent(comboResultados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        toolbarCrud.add(jSeparator2);

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

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));
        toolbarCrud.add(jSeparator3);

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

        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        labelMarca1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMarca1.setText("Tipo*:");

        tfTipo.setEditable(false);
        tfTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTipoActionPerformed(evt);
            }
        });
        tfTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTipoKeyTyped(evt);
            }
        });

        comboTipoCarro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboTipoCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Placa", "Chassi", "Dono" }));
        comboTipoCarro.setEnabled(false);
        comboTipoCarro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoCarroActionPerformed(evt);
            }
        });

        comboSelectCarro.setEditable(true);
        comboSelectCarro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboSelectCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboSelectCarro.setToolTipText("");
        comboSelectCarro.setEnabled(false);

        comboSelectCliente.setEditable(true);
        comboSelectCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboSelectCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        comboSelectCliente.setToolTipText("");
        comboSelectCliente.setEnabled(false);

        comboTipoCliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        comboTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome", "CPF", "RG" }));
        comboTipoCliente.setEnabled(false);
        comboTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoClienteActionPerformed(evt);
            }
        });

        labelMarca5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMarca5.setText("Cliente*:");

        labelMarca4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMarca4.setText("Carro*:");

        labelMarca2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMarca2.setText("Descrição:");

        tfDescricao.setEditable(false);
        tfDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDescricaoActionPerformed(evt);
            }
        });
        tfDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDescricaoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelDadosLayout = new javax.swing.GroupLayout(panelDados);
        panelDados.setLayout(panelDadosLayout);
        panelDadosLayout.setHorizontalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDadosLayout.createSequentialGroup()
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDadosLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelMarca5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMarca4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboTipoCliente, 0, 74, Short.MAX_VALUE)
                            .addComponent(comboTipoCarro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboSelectCarro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboSelectCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelDadosLayout.createSequentialGroup()
                                .addComponent(labelMarca1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDadosLayout.createSequentialGroup()
                                .addComponent(labelMarca2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelDadosLayout.setVerticalGroup(
            panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarca2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSelectCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarca5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(panelDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboSelectCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMarca4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTabelaServicos.setBorder(javax.swing.BorderFactory.createTitledBorder("Serviços"));

        tableServicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
        tableServicos.setEnabled(false);
        tableServicos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableServicos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableServicos);
        if (tableServicos.getColumnModel().getColumnCount() > 0) {
            tableServicos.getColumnModel().getColumn(0).setMinWidth(75);
            tableServicos.getColumnModel().getColumn(0).setMaxWidth(75);
            tableServicos.getColumnModel().getColumn(2).setMinWidth(120);
            tableServicos.getColumnModel().getColumn(2).setMaxWidth(120);
        }

        buttonExc.setText("Excluir");
        buttonExc.setEnabled(false);
        buttonExc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExcActionPerformed(evt);
            }
        });

        buttonAdd.setText("Adicionar");
        buttonAdd.setEnabled(false);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTabelaServicosLayout = new javax.swing.GroupLayout(panelTabelaServicos);
        panelTabelaServicos.setLayout(panelTabelaServicosLayout);
        panelTabelaServicosLayout.setHorizontalGroup(
            panelTabelaServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTabelaServicosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonExc, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(panelTabelaServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        panelTabelaServicosLayout.setVerticalGroup(
            panelTabelaServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTabelaServicosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTabelaServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonExc, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        panelValor.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));
        panelValor.setLayout(null);

        jLabel0.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel0.setText("Valor:");
        panelValor.add(jLabel0);
        jLabel0.setBounds(420, 20, 31, 20);

        labelValor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelValor.setText("____________");
        panelValor.add(labelValor);
        labelValor.setBounds(460, 20, 90, 20);

        jLabel1.setText("Código:");
        panelValor.add(jLabel1);
        jLabel1.setBounds(10, 20, 37, 20);
        panelValor.add(labelCodigo);
        labelCodigo.setBounds(50, 20, 50, 20);

        jLabel3.setText("Data:");
        panelValor.add(jLabel3);
        jLabel3.setBounds(110, 20, 27, 20);
        panelValor.add(labelData);
        labelData.setBounds(140, 20, 90, 20);

        cbSituacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Orçamento", "Aberta", "Fechada", "Cancelada" }));
        cbSituacao.setEnabled(false);
        panelValor.add(cbSituacao);
        cbSituacao.setBounds(300, 20, 90, 20);

        jLabel7.setText("Situação:");
        panelValor.add(jLabel7);
        jLabel7.setBounds(250, 20, 50, 20);

        buttonGerarRecibo.setText("Gerar Recibo");
        buttonGerarRecibo.setEnabled(false);
        buttonGerarRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGerarReciboActionPerformed(evt);
            }
        });
        panelValor.add(buttonGerarRecibo);
        buttonGerarRecibo.setBounds(320, 60, 130, 20);

        buttonGerarOrcamento.setText("Gerar Orçamento");
        buttonGerarOrcamento.setEnabled(false);
        buttonGerarOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGerarOrcamentoActionPerformed(evt);
            }
        });
        panelValor.add(buttonGerarOrcamento);
        buttonGerarOrcamento.setBounds(120, 60, 130, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelTabelaServicos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTabelaServicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonExcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcActionPerformed
        if (tableServicos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um serviço para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            servicos.remove(tableServicos.getSelectedRow());
            dTable.removeRow(tableServicos.getSelectedRow());
            tableServicos.setModel(dTable);
            this.atualizaValor();
        }
    }//GEN-LAST:event_buttonExcActionPerformed

    private void tfTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTipoActionPerformed

    }//GEN-LAST:event_tfTipoActionPerformed

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed
        this.limpar();
        this.editable(true);
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (this.valida()) {
            if (comboResultados.getSelectedIndex() <= 0) {
                int id = osControl.add(newOS());
                this.addServicos_OS(id);
                if (id != 0) {
                    Mensagens.sucessoCreate();
                    this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
                    this.setOSResultado(id);
                }
            } else {
                osControl.altera(alteraOS(ordens.get(comboResultados.getSelectedIndex() - 1)));
                this.alteraServicos(ordens.get(comboResultados.getSelectedIndex() - 1).getCod());
                this.setOSResultado(ordens.get(comboResultados.getSelectedIndex() - 1).getCod());
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

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            this.editable(true);
            this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma O.S para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            for (Servico_OS serv : serOS.getAllOS(ordens.get(comboSelectCarro.getSelectedIndex() - 1).getCod())) {
                serOS.remove(serv.getCod());
            }
            osControl.remove(ordens.get(comboResultados.getSelectedIndex() - 1).getCod());
            this.editable(false);
            this.limpar();
            this.preencheProcurar();
            Mensagens.sucessoDelete();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione alguma O.S para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void tfDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDescricaoActionPerformed

    private void comboResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadosActionPerformed
        if (comboResultados.getSelectedIndex() > 0) {
            this.preencher(ordens.get(comboResultados.getSelectedIndex() - 1));
            this.editable(false);
            this.enableButton(buttonAdicionar, buttonEditar, buttonExcluir);
        }
    }//GEN-LAST:event_comboResultadosActionPerformed

    private void comboTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoClienteActionPerformed
        comboSelectCliente.setModel(cliControl.procurar(comboTipoCliente.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoClienteActionPerformed

    private void comboTipoCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoCarroActionPerformed
        comboSelectCarro.setModel(carControl.procurar(comboTipoCarro.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoCarroActionPerformed

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboResultados.setModel(osControl.procurar(comboTipo.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoActionPerformed

    private void tfTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTipoKeyTyped
        Mensagens.restringirTamanho(evt, 11);
    }//GEN-LAST:event_tfTipoKeyTyped

    private void tfDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDescricaoKeyTyped
        Mensagens.restringirTamanho(evt, 20);
    }//GEN-LAST:event_tfDescricaoKeyTyped

    private void buttonGerarReciboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGerarReciboActionPerformed
        if (cbSituacao.getSelectedIndex() == 1) {
            RelatorioController.geraRelatorioRecibo(this, Integer.parseInt(labelCodigo.getText()));
            ordens.get(comboResultados.getSelectedIndex() - 1).setSituacao(3);
            osControl.altera(ordens.get(comboResultados.getSelectedIndex() - 1));
            cbSituacao.setSelectedIndex(ordens.get(comboResultados.getSelectedIndex() - 1).getSituacao());
            this.setOSResultado(ordens.get(comboResultados.getSelectedIndex() - 1).getCod());
            Mensagens.sucessoAlterar();

        } else {
            JOptionPane.showMessageDialog(this, "O.S não é Recibo", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonGerarReciboActionPerformed

    private void buttonGerarOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGerarOrcamentoActionPerformed
        if (cbSituacao.getSelectedIndex() >= 0) {
            RelatorioController.geraRelatorioOrcamento(Integer.parseInt(labelCodigo.getText()), this);
        } else {
            JOptionPane.showMessageDialog(this, "O.S não é orçamento", "Alerta", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_buttonGerarOrcamentoActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        ts = new TabelaServicos(this);
        ts.setVisible(true);
    }//GEN-LAST:event_buttonAddActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExc;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonGerarOrcamento;
    private javax.swing.JButton buttonGerarRecibo;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JComboBox cbSituacao;
    private javax.swing.JComboBox<String> comboResultados;
    private javax.swing.JComboBox<String> comboSelectCarro;
    private javax.swing.JComboBox<String> comboSelectCliente;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JComboBox<String> comboTipoCarro;
    private javax.swing.JComboBox<String> comboTipoCliente;
    private javax.swing.JLabel jLabel0;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelMarca1;
    private javax.swing.JLabel labelMarca2;
    private javax.swing.JLabel labelMarca4;
    private javax.swing.JLabel labelMarca5;
    private javax.swing.JLabel labelPor;
    private javax.swing.JLabel labelValor;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JPanel panelTabelaServicos;
    private javax.swing.JPanel panelValor;
    private javax.swing.JTable tableServicos;
    private javax.swing.JTextField tfDescricao;
    private javax.swing.JTextField tfTipo;
    private javax.swing.JLabel titulo;
    private javax.swing.JToolBar toolbarCrud;
    // End of variables declaration//GEN-END:variables
}
