package servidor.rest;

import java.util.ArrayList;
import model.Servico;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class ServicoJSON {

    public static Servico getServicoJSON(JSONObject object) {
        //instancia vetor de servicos
        Servico servico = new Servico();
        try {
            //pega do json os registros da tag servico
            servico.setCod(object.getInt("cod"));
            servico.setDescricao(object.getString("descricao"));
            servico.setValor(object.getDouble("valor"));
        } catch (Exception x) {
        }
        return servico;
    }

    public static String geraJSONServicos(ArrayList<Servico> servicos) {
        ArrayList<JSONObject> tabelaServicos = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Servico servico : servicos) {
            registro = preencheJSON(servico);

            //adiciona registro à lista de registros
            tabelaServicos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("servico", (Object) tabelaServicos);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONServico(Servico servico) {
        return UtilJSON.limpaJSON(preencheJSON(servico));
    }

    public static JSONObject preencheJSON(Servico servico) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("cod", servico.getCod());
            registro.put("descricao", servico.getDescricao());
            registro.put("valor", servico.getValor());
            return registro;
        } catch (JSONException k) {
        }
        return null;
    }

}
