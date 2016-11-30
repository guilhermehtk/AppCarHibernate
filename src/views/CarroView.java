package views;

import control.CarroController;
import control.ClienteController;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.Carro;
import model.Cliente;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class CarroView extends javax.swing.JInternalFrame {

    private CarroController carControl = new CarroController();
    private ClienteController cliControl = new ClienteController();
    private ArrayList<Carro> carros;
    private ArrayList<Cliente> clientes;

    public CarroView() {
        initComponents();
        this.preencheProcurar();
        AutoCompleteDecorator.decorate(comboResultados);
        AutoCompleteDecorator.decorate(comboDono);
        this.setFrameIcon(new ImageIcon(getClass().getResource("/views/icons/AppCar.png")));
    }

    private DefaultComboBoxModel preencheModelo(String marca) {
        DefaultComboBoxModel cbArray = new DefaultComboBoxModel();
        cbArray.insertElementAt("Selecione...", 0);
        switch (marca) {
            case "FIAT":
                cbArray.addElement("Uno");
                cbArray.addElement("Mobi");
                cbArray.addElement("Palio");
                cbArray.addElement("Strada");
                cbArray.addElement("Freemont");
                cbArray.addElement("Ducato");
                break;
            case "GM - Chevrolet":
                cbArray.addElement("Camaro");
                cbArray.addElement("Cobalt");
                cbArray.addElement("Onix");
                cbArray.addElement("Cruze");
                cbArray.addElement("Monza");
                break;
            case "Volkswagen":
                cbArray.addElement("Saveiro");
                cbArray.addElement("Gol");
                cbArray.addElement("UP");
                cbArray.addElement("Jetta");
                cbArray.addElement("Fox");
                cbArray.addElement("Golf");
                break;
            case "Hyundai":
                cbArray.addElement("HB20");
                cbArray.addElement("ix35");
                cbArray.addElement("Veloster");
                break;
            case "Ford":
                cbArray.addElement("Escort Hobby");
                cbArray.addElement("KA");
                cbArray.addElement("Fiesta");
                cbArray.addElement("Fusion");
                cbArray.addElement("Ecosport");
                cbArray.addElement("Edge");
                break;
            default:
                cbArray.addElement("Outros");
                break;

        }
        return cbArray;
    }

    private void preencheProcurar() {
        clientes = cliControl.getAll();
        carros = carControl.getAll();
        comboResultados.setModel(carControl.procurar(comboTipo.getSelectedIndex()));
        comboDono.setModel(cliControl.procurar(comboTipoDono.getSelectedIndex()));
        comboResultados.setSelectedIndex(0);
    }

    private boolean valida() {
        return Mensagens.errosValidacao(carControl.valida(newCarro()));
    }

    private boolean validaUnique() {
        return Mensagens.errosUnique(carControl.validaUniques(newCarro()));
    }

    private void preencher(Carro carro) {
        cbCor.setSelectedItem(carro.getCor());
        cbMarca.setSelectedItem(carro.getMarca());
        cbModelo.setSelectedItem(carro.getModelo());
        tfAno.setText(carro.getAno());
        tfChassi.setText(carro.getChassi());
        tfKm.setText(carro.getKm());
        tfObs.setText(carro.getObs());
        tfObs.setBackground(Color.white);
        tfPlaca.setText(carro.getPlaca());
        comboTipoDono.setSelectedIndex(0);
        if (carro.getDono() == null) {
            comboDono.setSelectedIndex(0);
        }
        for (Cliente dono : clientes) {
            if (dono.getCodigo() == carro.getDono().getCodigo()) {
                comboDono.setSelectedIndex(clientes.indexOf(dono) + 1);
            }
        }
    }

    private void setCarroResultado(int id) {
        this.preencheProcurar();
        for (Carro car : carros) {
            if (car.getCod() == id) {
                comboResultados.setSelectedIndex(carros.indexOf(car) + 1);
            }
        }
    }

    private void limpar() {
        cbCor.setSelectedIndex(0);
        cbMarca.setSelectedIndex(0);
        cbModelo.setSelectedIndex(0);
        tfAno.setText("");
        tfKm.setText("");
        tfPlaca.setText("");
        tfObs.setText("");
        tfChassi.setText("");
        comboTipoDono.setSelectedIndex(0);
        comboDono.setSelectedIndex(0);
        comboResultados.setSelectedIndex(0);
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

    private void editable(boolean flag) {
        cbCor.setEnabled(flag);
        cbMarca.setEnabled(flag);
        cbModelo.setEnabled(flag);
        tfAno.setEditable(flag);
        tfKm.setEditable(flag);
        tfAno.setEditable(flag);
        tfChassi.setEditable(flag);
        tfPlaca.setEditable(flag);
        tfObs.setEditable(flag);
        comboTipoDono.setEnabled(flag);
        comboDono.setEnabled(flag);
        if (flag) {
            tfObs.setBackground(Color.white);
        } else {
            tfObs.setBackground(this.getBackground());
        }
    }

    private Carro newCarro() {
        Carro carro = new Carro();
        carro.setAno(tfAno.getText());
        carro.setChassi(tfKm.getText());
        carro.setCor(cbCor.getSelectedItem().toString());
        carro.setKm(tfKm.getText());
        carro.setMarca(cbMarca.getSelectedItem().toString());
        carro.setModelo(cbModelo.getSelectedItem().toString());
        carro.setObs(tfObs.getText());
        for (Carro carro1 : carros) {
            if (carro1.getPlaca().equals(tfPlaca.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já existente", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        carro.setPlaca(tfPlaca.getText());
        if (comboDono.getSelectedIndex() > 0) {
            carro.setDono(clientes.get(comboDono.getSelectedIndex() - 1));
        }
        return carro;
    }

    private Carro alteraCarro(Carro carro) {
        carro.setAno(tfAno.getText());
        carro.setChassi(tfAno.getText());
        carro.setCor(cbCor.getSelectedItem().toString());
        carro.setKm(tfKm.getText());
        carro.setMarca(cbMarca.getSelectedItem().toString());
        carro.setModelo(cbModelo.getSelectedItem().toString());
        carro.setObs(tfObs.getText());
        for (Carro carro1 : carros) {
            if (carro1.getPlaca().equals(tfPlaca.getText())) {
                JOptionPane.showMessageDialog(this, "CPF já existente", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
        carro.setPlaca(tfPlaca.getText());
        if (comboDono.getSelectedIndex() <= 0) {
            carro.setDono(null);
        } else {
            carro.setDono(clientes.get(comboDono.getSelectedIndex() - 1));
        }

        return carro;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelButtons = new javax.swing.JPanel();
        buttonSalvar = new javax.swing.JButton();
        buttonLimpar = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        panelProcurar = new javax.swing.JPanel();
        labelPor = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox<String>();
        comboResultados = new javax.swing.JComboBox<String>();
        titulo = new javax.swing.JLabel();
        panelDados = new javax.swing.JPanel();
        labelModelo2 = new javax.swing.JLabel();
        cbMarca = new javax.swing.JComboBox();
        labelCor = new javax.swing.JLabel();
        labelChassi = new javax.swing.JLabel();
        labelPlaca = new javax.swing.JLabel();
        labelKm = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfObs = new javax.swing.JTextArea();
        labelObs = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        cbCor = new javax.swing.JComboBox();
        labelAno = new javax.swing.JLabel();
        cbModelo = new javax.swing.JComboBox();
        jSeparator2 = new javax.swing.JSeparator();
        labelMarca1 = new javax.swing.JLabel();
        labelCor1 = new javax.swing.JLabel();
        comboTipoDono = new javax.swing.JComboBox<String>();
        comboDono = new javax.swing.JComboBox<String>();
        tfPlaca = new javax.swing.JFormattedTextField();
        tfAno = new javax.swing.JTextField();
        tfKm = new javax.swing.JTextField();
        tfChassi = new javax.swing.JTextField();
        toolbarCrud = new javax.swing.JToolBar();
        buttonAdicionar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        buttonEditar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        buttonExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carro");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/views/icons/Carro-25.png"))); // NOI18N

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

        panelProcurar.setBorder(javax.swing.BorderFactory.createTitledBorder("Procurar"));

        labelPor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelPor.setText("Por:");

        comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Placa", "Chassi", "Dono" }));
        comboTipo.setToolTipText("");
        comboTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoActionPerformed(evt);
            }
        });

        comboResultados.setEditable(true);
        comboResultados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        comboResultados.setToolTipText("");
        comboResultados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboResultadosItemStateChanged(evt);
            }
        });
        comboResultados.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                comboResultadosCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        comboResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboResultadosActionPerformed(evt);
            }
        });
        comboResultados.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboResultadosPropertyChange(evt);
            }
        });
        comboResultados.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                comboResultadosVetoableChange(evt);
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
                .addComponent(comboResultados, 0, 405, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelProcurarLayout.setVerticalGroup(
            panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcurarLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panelProcurarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPor)
                    .addComponent(comboResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        titulo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Carro");

        panelDados.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));
        panelDados.setLayout(null);

        labelModelo2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelModelo2.setText("Modelo*:");
        panelDados.add(labelModelo2);
        labelModelo2.setBounds(190, 70, 50, 20);

        cbMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "FIAT", "Ford", "GM - Chevrolet", "Volkswagen", "Hyundai", "Honda", "Audi", "BMW", "Toyota", "Volvo", "Mercedes Benz", "Porsche", "GMC", "JEEP", "Nissan", "Mazda", "Mitsubishi", "Renault", "Suzuki" }));
        cbMarca.setEnabled(false);
        cbMarca.setMinimumSize(new java.awt.Dimension(81, 20));
        cbMarca.setPreferredSize(new java.awt.Dimension(81, 20));
        cbMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMarcaItemStateChanged(evt);
            }
        });
        cbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMarcaActionPerformed(evt);
            }
        });
        panelDados.add(cbMarca);
        cbMarca.setBounds(60, 70, 110, 20);

        labelCor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelCor.setText("Dono:");
        panelDados.add(labelCor);
        labelCor.setBounds(10, 30, 40, 20);

        labelChassi.setText("Chassi:");
        panelDados.add(labelChassi);
        labelChassi.setBounds(10, 150, 80, 20);

        labelPlaca.setText("Placa*:");
        panelDados.add(labelPlaca);
        labelPlaca.setBounds(10, 210, 70, 20);

        labelKm.setText("Quilometragem:");
        panelDados.add(labelKm);
        labelKm.setBounds(10, 180, 80, 20);

        tfObs.setEditable(false);
        tfObs.setColumns(20);
        tfObs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        tfObs.setRows(4);
        jScrollPane1.setViewportView(tfObs);

        panelDados.add(jScrollPane1);
        jScrollPane1.setBounds(100, 240, 390, 80);

        labelObs.setText("Observações:");
        panelDados.add(labelObs);
        labelObs.setBounds(10, 240, 80, 20);
        panelDados.add(jSeparator6);
        jSeparator6.setBounds(440, 380, 0, 20);

        cbCor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Prata ", "Preto  ", "Branco ", "Cinza ", "Azul ", "Vermelho ", "Marrom/Bege ", "Verde ", "Amarelo/Dourado", "Outro", " " }));
        cbCor.setEnabled(false);
        cbCor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCorActionPerformed(evt);
            }
        });
        panelDados.add(cbCor);
        cbCor.setBounds(410, 70, 110, 20);

        labelAno.setText("Ano:");
        panelDados.add(labelAno);
        labelAno.setBounds(10, 120, 30, 20);

        cbModelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione...", "Uno" }));
        cbModelo.setEnabled(false);
        cbModelo.setMinimumSize(new java.awt.Dimension(81, 20));
        cbModelo.setPreferredSize(new java.awt.Dimension(81, 20));
        cbModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbModeloActionPerformed(evt);
            }
        });
        panelDados.add(cbModelo);
        cbModelo.setBounds(240, 70, 110, 20);
        panelDados.add(jSeparator2);
        jSeparator2.setBounds(10, 100, 520, 10);

        labelMarca1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelMarca1.setText("Marca:*");
        panelDados.add(labelMarca1);
        labelMarca1.setBounds(10, 70, 50, 20);

        labelCor1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelCor1.setText("Cor:");
        panelDados.add(labelCor1);
        labelCor1.setBounds(380, 70, 30, 20);

        comboTipoDono.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Código", "Nome", "CPF", "RG" }));
        comboTipoDono.setToolTipText("");
        comboTipoDono.setEnabled(false);
        comboTipoDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoDonoActionPerformed(evt);
            }
        });
        panelDados.add(comboTipoDono);
        comboTipoDono.setBounds(50, 30, 60, 20);

        comboDono.setEditable(true);
        comboDono.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione..." }));
        comboDono.setToolTipText("");
        comboDono.setEnabled(false);
        comboDono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboDonoActionPerformed(evt);
            }
        });
        panelDados.add(comboDono);
        comboDono.setBounds(120, 30, 400, 20);

        tfPlaca.setEditable(false);
        try {
            tfPlaca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPlacaActionPerformed(evt);
            }
        });
        panelDados.add(tfPlaca);
        tfPlaca.setBounds(100, 210, 390, 20);

        tfAno.setEditable(false);
        tfAno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfAnoActionPerformed(evt);
            }
        });
        tfAno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAnoKeyTyped(evt);
            }
        });
        panelDados.add(tfAno);
        tfAno.setBounds(100, 120, 390, 20);

        tfKm.setEditable(false);
        tfKm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfKmActionPerformed(evt);
            }
        });
        tfKm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfKmKeyTyped(evt);
            }
        });
        panelDados.add(tfKm);
        tfKm.setBounds(100, 180, 390, 20);

        tfChassi.setEditable(false);
        tfChassi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfChassiKeyTyped(evt);
            }
        });
        panelDados.add(tfChassi);
        tfChassi.setBounds(100, 150, 390, 20);

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

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));
        toolbarCrud.add(jSeparator5);

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelButtons, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelProcurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDados, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(toolbarCrud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMarcaActionPerformed

    }//GEN-LAST:event_cbMarcaActionPerformed

    private void cbCorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCorActionPerformed

    private void cbModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbModeloActionPerformed

    private void buttonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSalvarActionPerformed
        if (this.valida()) {
            if (comboResultados.getSelectedIndex() == 0) {
                if (this.validaUnique()) {
                    int id = 0;
                    Carro carro = newCarro();
                    if (carro != null) {
                        id = carControl.add(carro);
                    } else {
                        return;
                    }
                    if (id != 0) {
                        this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
                        Mensagens.sucessoCreate();
                        this.setCarroResultado(id);
                    }
                }
            } else {
                Carro car = alteraCarro(carros.get(comboResultados.getSelectedIndex() - 1));
                if (car != null) {
                    carControl.altera(car);
                } else {
                    return;
                }
                this.setCarroResultado(carros.get(comboResultados.getSelectedIndex() - 1).getCod());
                Mensagens.sucessoAlterar();
                this.enableButton(buttonEditar, buttonAdicionar, buttonExcluir);
            }
        }
    }//GEN-LAST:event_buttonSalvarActionPerformed

    private void buttonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimparActionPerformed
        this.limpar();
    }//GEN-LAST:event_buttonLimparActionPerformed

    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_buttonCancelarActionPerformed

    private void buttonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdicionarActionPerformed

        this.limpar();
        this.editable(true);
        this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
    }//GEN-LAST:event_buttonAdicionarActionPerformed

    private void comboResultadosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboResultadosPropertyChange

    }//GEN-LAST:event_comboResultadosPropertyChange

    private void comboTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoActionPerformed
        comboResultados.setModel(carControl.procurar(comboTipo.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoActionPerformed

    private void comboResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboResultadosActionPerformed

    }//GEN-LAST:event_comboResultadosActionPerformed

    private void buttonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEditarActionPerformed
        if (comboResultados.getSelectedIndex() != 0) {
            this.editable(true);
            this.disableButton(buttonEditar, buttonAdicionar, buttonExcluir);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum carro para editar!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonEditarActionPerformed

    private void buttonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExcluirActionPerformed
        if (comboResultados.getSelectedIndex() != 0) {
            carControl.remove(carros.get(comboResultados.getSelectedIndex() - 1).getCod());
            this.editable(false);
            this.limpar();
            this.preencheProcurar();
            Mensagens.sucessoDelete();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione algum carro para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonExcluirActionPerformed

    private void comboTipoDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoDonoActionPerformed
        comboDono.setModel(cliControl.procurar(comboTipoDono.getSelectedIndex()));
    }//GEN-LAST:event_comboTipoDonoActionPerformed

    private void comboDonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboDonoActionPerformed
    }//GEN-LAST:event_comboDonoActionPerformed

    private void tfPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPlacaActionPerformed

    private void tfAnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfAnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfAnoActionPerformed

    private void tfAnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAnoKeyTyped
        Mensagens.somenteNumeros(evt);
        if (tfAno.getText().length() == 4) {
            evt.consume();
        }
    }//GEN-LAST:event_tfAnoKeyTyped

    private void comboResultadosCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_comboResultadosCaretPositionChanged


    }//GEN-LAST:event_comboResultadosCaretPositionChanged

    private void comboResultadosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_comboResultadosVetoableChange

    }//GEN-LAST:event_comboResultadosVetoableChange

    private void comboResultadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboResultadosItemStateChanged
        if ((comboResultados.getSelectedIndex() != 0) && (comboResultados.getSelectedIndex() != -1)) {
            this.preencher(carros.get(comboResultados.getSelectedIndex() - 1));
            this.editable(false);
            this.enableButton(buttonAdicionar, buttonEditar, buttonExcluir);
        }
    }//GEN-LAST:event_comboResultadosItemStateChanged

    private void tfKmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfKmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfKmActionPerformed

    private void tfChassiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfChassiKeyTyped
        Mensagens.restringirTamanho(evt, 45);
    }//GEN-LAST:event_tfChassiKeyTyped

    private void tfKmKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfKmKeyTyped
        Mensagens.somenteNumeros(evt);
        Mensagens.restringirTamanho(evt, 11);
    }//GEN-LAST:event_tfKmKeyTyped

    private void cbMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMarcaItemStateChanged
        cbModelo.setModel(this.preencheModelo(cbMarca.getSelectedItem().toString()));
        cbModelo.setSelectedIndex(0);
    }//GEN-LAST:event_cbMarcaItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdicionar;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonEditar;
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JButton buttonLimpar;
    private javax.swing.JButton buttonSalvar;
    private javax.swing.JComboBox cbCor;
    private javax.swing.JComboBox cbMarca;
    private javax.swing.JComboBox cbModelo;
    private javax.swing.JComboBox<String> comboDono;
    private javax.swing.JComboBox<String> comboResultados;
    private javax.swing.JComboBox<String> comboTipo;
    private javax.swing.JComboBox<String> comboTipoDono;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel labelAno;
    private javax.swing.JLabel labelChassi;
    private javax.swing.JLabel labelCor;
    private javax.swing.JLabel labelCor1;
    private javax.swing.JLabel labelKm;
    private javax.swing.JLabel labelMarca1;
    private javax.swing.JLabel labelModelo2;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelPlaca;
    private javax.swing.JLabel labelPor;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JPanel panelDados;
    private javax.swing.JPanel panelProcurar;
    private javax.swing.JTextField tfAno;
    private javax.swing.JTextField tfChassi;
    private javax.swing.JTextField tfKm;
    private javax.swing.JTextArea tfObs;
    private javax.swing.JFormattedTextField tfPlaca;
    private javax.swing.JLabel titulo;
    private javax.swing.JToolBar toolbarCrud;
    // End of variables declaration//GEN-END:variables
}
