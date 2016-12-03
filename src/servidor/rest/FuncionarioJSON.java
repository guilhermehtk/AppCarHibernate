package servidor.rest;

import java.util.ArrayList;
import model.Funcionario;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class FuncionarioJSON {

    public static Funcionario getFuncionarioJSON(JSONObject object) {
        //instancia vetor de funcionarios
        Funcionario funcionario = new Funcionario();
        try {
            //pega do json os registros da tag funcionario
            funcionario.setCodigo(object.getInt("codigo"));
            funcionario.setLogin(LoginJSON.getLoginJSON(object.getJSONObject("login_cod")));
        } catch (Exception x) {
        }
        return funcionario;
    }

    public static String geraJSONFuncionarios(ArrayList<Funcionario> funcionarios) {
        ArrayList<JSONObject> tabelaFuncionarios = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Funcionario funcionario : funcionarios) {
            registro = preencheJSON(funcionario);

            //adiciona registro à lista de registros
            tabelaFuncionarios.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("funcionario", (Object) tabelaFuncionarios);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONFuncionario(Funcionario funcionario) {
        return UtilJSON.limpaJSON(preencheJSON(funcionario));
    }

    public static JSONObject preencheJSON(Funcionario funcionario) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("codigo", funcionario.getCodigo());
            registro.put("login_cod", funcionario.getLogin().getCod());
            return registro;
        } catch (JSONException k) {
        }
        return null;
    }

}
