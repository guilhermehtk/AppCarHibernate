package servidor.rest;

import java.util.ArrayList;
import model.Cliente;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class ClienteJSON {

    public static Cliente getClienteJSON(JSONObject object) {
        //instancia vetor de clientes
        Cliente cliente = new Cliente();
        try {
            //pega do json os registros da tag cliente
            cliente.setCodigo(object.getInt("codigo"));
        } catch (Exception x) {
        }
        return cliente;
    }

    public static String geraJSONClientes(ArrayList<Cliente> clientes) {
        ArrayList<JSONObject> tabelaClientes = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Cliente cliente : clientes) {
            registro = preencheJSON(cliente);

            //adiciona registro à lista de registros
            tabelaClientes.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("cliente", (Object) tabelaClientes);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONCliente(Cliente cliente) {
        return UtilJSON.limpaJSON(preencheJSON(cliente));
    }

    public static JSONObject preencheJSON(Cliente cliente) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("codigo", cliente.getCodigo());
            return registro;
        } catch (JSONException k) {
        }
        return null;
    }

}
