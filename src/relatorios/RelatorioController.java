package relatorios;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class RelatorioController {

    public static void geraRelatorio(final String jrxml, final HashMap param, JInternalFrame frame) {
        GlassPaneWorker worker = new GlassPaneWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                try {
                    SimpleDateFormat dt = new SimpleDateFormat("dd-mm-yyyy");
                    Connection con = ConnectionFactory.getConnection();
                    Map parameters = param;
                    JasperReport report = JasperCompileManager.compileReport("src/relatorios/xml/" + jrxml + ".jrxml");
                    JasperPrint impressao = JasperFillManager.fillReport(report, parameters, con);
                    JasperExportManager.exportReportToPdfFile(impressao, "relatorios/" + jrxml + "-" + dt.format(Calendar.getInstance().getTime()) + ".pdf");
                    File arquivo = new File("relatorios/" + jrxml + "-" + dt.format(Calendar.getInstance().getTime())+ ".pdf");                                             
                    Desktop.getDesktop().open(arquivo);
                } catch (IOException | JRException e) {
                    JOptionPane.showMessageDialog(null, "Erro ao Gerar o Relatório, " + e.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println(e.getMessage());
                }
                Thread.sleep(2500);
                return null;
            }
        };
        worker.execute(frame, "Gerando relatório...");
    }

    public static void geraRelatorioOs(int cliCod, JInternalFrame frame) {
        HashMap params = new HashMap<>();
        params.put("codigo", cliCod);
        geraRelatorio("oscliente", params, frame);
    }

    public static void geraRelatorioOrcamento(int osCod, JInternalFrame frame) {
        HashMap params = new HashMap<>();
        params.put("osCod", osCod);
        geraRelatorio("orcamento", params, frame);
    }

    public static void geraRelatorioServicosPeriodo(String dataInicial, String dataFinal, JInternalFrame frame) {
        HashMap<String, String> params = new HashMap<>();
        params.put("dataInicial", dataInicial);
        params.put("dataFinal", dataFinal);
        geraRelatorio("servicosdata", params, frame);
    }

    public static void geraRelatorioServicos(JInternalFrame frame) {
        geraRelatorio("listaservicos", null, frame);
    }
    public static void geraRelatorioServicoRealizado(JInternalFrame frame) {
        geraRelatorio("servicorealizado", null, frame);
    }

    public static void geraRelatorioRecibo(JInternalFrame frame, int osCod) {
        HashMap params = new HashMap<>();
        params.put("osCod", osCod);
        geraRelatorio("recibo", params, frame);
    }

}
