package views;

import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Mensagens {

    private static final ImageIcon icon = icon("/views/icons/Ok-48.png");

    private static ImageIcon icon(String path) {
        URL resource = Mensagens.class.getResource(path);
        return new ImageIcon(resource);
    }

    public static void erroBD(String erro) {
        JOptionPane.showMessageDialog(null, "Erro na Conexão com o Banco de Dados " + erro, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public static void sucessoCreate() {
        JOptionPane.showMessageDialog(null, "Inserido com Sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE, icon);
    }

    public static void sucessoAlterar() {
        JOptionPane.showMessageDialog(null, "Alterado com Sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE, icon);
    }

    public static void sucessoDelete() {
        JOptionPane.showMessageDialog(null, "Deletado com Sucesso!", "Sucesso", JOptionPane.PLAIN_MESSAGE, icon);
    }

    public static boolean errosValidacao(ArrayList<String> erros) {
        if (erros.isEmpty()) {
            return true;
        } else {
            if (erros.size() == 1) {
                JOptionPane.showMessageDialog(null, "O campo " + erros.get(0) + " não foi preenchido!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                String mensagem = null;
                for (String erro : erros) {
                    if (erros.get(0).equals(erro)) {
                        mensagem = erro;
                    } else if (erros.size() - 1 == erros.indexOf(erro)) {
                        mensagem = mensagem + " e " + erro;
                    } else {
                        mensagem = mensagem + ", " + erro;
                    }
                }
                JOptionPane.showMessageDialog(null, "Os campos " + mensagem + " não foram preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    public static boolean errosUnique(ArrayList<String> erros) {
        if (erros.isEmpty()) {
            return true;
        } else {
            if (erros.size() == 1) {
                JOptionPane.showMessageDialog(null, erros.get(0) + " já está cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                String mensagem = null;
                for (String erro : erros) {
                    if (erros.get(0).equals(erro)) {
                        mensagem = erro;
                    } else if (erros.size() - 1 == erros.indexOf(erro)) {
                        mensagem = mensagem + " e " + erro;
                    } else {
                        mensagem = mensagem + ", " + erro;
                    }
                }
                JOptionPane.showMessageDialog(null, mensagem + " já estão cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    public static void somenteNumeros(java.awt.event.KeyEvent evt) {
        String caracteres = "0123456789";
        if (!caracteres.contains(evt.getKeyChar() + "")) {
            evt.consume();
        }
    }

    public static void restringirTamanho(java.awt.event.KeyEvent evt, int tamanho) {
        JTextField tf = (JTextField) evt.getComponent();
        if (tf.getText().length() == tamanho) {
            evt.consume();
        }
    }

    public static boolean tamanhoMinimo(String string, String campo, int tamanho) {
        if (string.length() < tamanho) {
            JOptionPane.showMessageDialog(null, campo + " precisa ter no mínimo " + tamanho + " caracteres!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

    public static boolean confirmaPassword(String senha, String confirmacao) {
        if (!senha.equals(confirmacao)) {
            JOptionPane.showMessageDialog(null, "Confirmação de senha incorreta!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            return true;
        }
    }

}
