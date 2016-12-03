package servidor;

import control.CarroController;
import control.ClienteController;
import control.EnderecoController;
import control.FuncionarioController;
import control.LoginController;
import control.OrdemServicoController;
import control.PessoaController;
import control.ServicoController;
import control.Servico_OSController;
import java.net.*;
import java.io.*;
import model.Carro;
import model.Cliente;
import model.Funcionario;
import model.OrdemServico;
import model.Servico;
import model.Servico_OS;
import servidor.json.JSONArray;
import servidor.json.JSONObject;
import servidor.rest.CarroJSON;
import servidor.rest.ClienteJSON;
import servidor.rest.EnderecoJSON;
import servidor.rest.FuncionarioJSON;
import servidor.rest.LoginJSON;
import servidor.rest.OrdemServicoJSON;
import servidor.rest.PessoaJSON;
import servidor.rest.ServicoJSON;
import servidor.rest.Servico_OSJSON;
import views.Mensagens;

public class ServidorTCP {

    private ServerSocket ss;
    private int porta;
    private Socket s = null;
    private BufferedReader br = null;
    private PrintStream ps = null;
    private String msgRecebida = "";

    public ServidorTCP(int p) {
        porta = p;//a porta pode ser um argumento 
        try {
            ss = new ServerSocket(porta);//socket do servidor
        } catch (Exception e) {
            Mensagens.erroBD(e.toString());
        }
        System.out.println("Servidor Ligado");
    }

    public void esperaConexao() throws IOException {
        //laço infinito para esperar várias conexões
        while (ss != null) {

            System.out.println("Esperando Conexoes");
            //inicia processo de conexão

            //aguarda conexões
            s = ss.accept();

            //buffer de leitura, classe io, poderia estar manipulando um arquivo    
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            msgRecebida = br.readLine(); // recebendo dados do cliente como string    
            System.out.println("JSON do Cliente: \n" + msgRecebida + "\n");

            //cria prinstream para responder ao cliente
            ps = new PrintStream(s.getOutputStream());

            JSONObject json = new JSONObject(msgRecebida);

            if (json.has("request")) {
                if (json.get("request").equals("login")) {
                    JSONObject login = json.getJSONObject("object").getJSONObject("login");
                    int flag = LoginController.validaLogin(LoginJSON.getLoginJSON(login));
                    System.out.println(flag);
                    // testa o login do usuário
                    if (flag == 0) {
                        ps.println(new JSONObject().put("return", "password_incorrect"));
                    } else if (flag < 0) {
                        ps.println(new JSONObject().put("return", "user_not_found"));
                    } else {
                        JSONObject resposta = new JSONObject();
                        resposta.put("return", "success");
                        resposta.put("cod_login", flag);
                        ps.println(resposta.toString());
                    }
                } else {
                    String resposta = this.request(json.getString("request"), json);
                    ps.println(resposta);
                    System.out.println("JSON ENVIADO: " + resposta);
                }
            }
            s.close();
            System.gc();
        }
    }

    public void close() throws IOException {
        System.out.println("Servidor Desligado");
        ss.close();
    }

    public void restart() throws IOException {
        System.out.println("Servidor Desligado");
        ss.close();
        ss = null;
        try {
            ss = new ServerSocket(porta);//socket do servidor
        } catch (Exception e) {
            Mensagens.erroBD(e.toString());
        }
        System.out.println("Servidor Ligado");

    }

    public String request(String request, JSONObject object) {
        if (object.has("array")) {
            JSONArray array = new JSONArray();
            for (int i = 0; i < object.getJSONArray("array").length(); i++) {
                array.put(new JSONObject(switchRequest(request, object.getJSONArray("array").getJSONObject(i))));
            }
            return new JSONObject().put("return", array).toString();
        } else if (object.has("object")) {
            return new JSONObject().put("return", new JSONObject(switchRequest(request, object.getJSONObject("object")))).toString();
        } else {
            return new JSONObject().put("return", new JSONObject(switchRequest(request, null))).toString();
        }

    }

    private String switchRequest(String request, JSONObject object) {
        switch (request) {
            // ------------------------------- GET all ------------------------- //
            case "get_Carro_All":
                return CarroJSON.geraJSONCarros(new CarroController().getAll());
            case "get_Cliente_All":
                return ClienteJSON.geraJSONClientes(new ClienteController().getAll());
            case "get_Funcionario_All":
                return FuncionarioJSON.geraJSONFuncionarios(new FuncionarioController().getAll());
            case "get_OrdemServico_All":
                return OrdemServicoJSON.geraJSONOrdemServicos(new OrdemServicoController().getAll());
            case "get_Servico_All":
                return ServicoJSON.geraJSONServicos(new ServicoController().getAll());
            case "get_Servico_OS_All":
                return Servico_OSJSON.geraJSONServico_OSs(new Servico_OSController().getAll());
            case "get_Endereco_All":
                return EnderecoJSON.geraJSONEnderecos(new EnderecoController().getAll());
            case "get_Login_All":
                return LoginJSON.geraJSONLogins(new LoginController().getAll());
            case "get_Pessoa_All":
                return PessoaJSON.geraJSONPessoas(new PessoaController().getAll());

            // ------------------------------- GET id ------------------------- //
            case "get_Carro":
                return CarroJSON.geraJSONCarro((Carro) new CarroController().get(object.getInt("carCod")));
            case "get_Cliente":
                return ClienteJSON.geraJSONCliente((Cliente) new ClienteController().get(object.getInt("pesCod")));
            case "get_Funcionario":
                return FuncionarioJSON.geraJSONFuncionario((Funcionario) new FuncionarioController().get(object.getInt("pesCod")));
            case "get_OrdemServico":
                return OrdemServicoJSON.geraJSONOrdemServico((OrdemServico) new OrdemServicoController().get(object.getInt("osCod")));
            case "get_Servico":
                return ServicoJSON.geraJSONServico((Servico) new ServicoController().get(object.getInt("svcCod")));
            case "get_Servico_OS":
                return Servico_OSJSON.geraJSONServico_OS((Servico_OS) new Servico_OSController().get(object.getInt("serCod")));
            case "get_Servico_OS_osCod":
                return Servico_OSJSON.geraJSONServico_OS((Servico_OS) new Servico_OSController().get(object.getInt("ser_osCod")));

            // ------------------------------- SET object ------------------------- //
            case "set_Carro":
                new CarroController().altera(CarroJSON.getCarroJSON(object.getJSONObject("carro")));
                return "true";
            case "set_Cliente":
                new ClienteController().altera(ClienteJSON.getClienteJSON(object.getJSONObject("cliente")));
                return "true";
            case "set_Funcionario":
                new FuncionarioController().altera(FuncionarioJSON.getFuncionarioJSON(object.getJSONObject("funcionario")));
                return "true";
            case "set_OrdemServico":
                new OrdemServicoController().altera(OrdemServicoJSON.getOrdemServicoJSON(object.getJSONObject("ordemservico")));
                return "true";
            case "set_Servico":
                new ServicoController().altera(ServicoJSON.getServicoJSON(object.getJSONObject("servico")));
                return "true";
            case "set_Servico_OS":
                new Servico_OSController().altera(Servico_OSJSON.getServico_OSJSON(object.getJSONObject("servico_os")));
                return "true";

            // ------------------------------- ADD object ------------------------- //
            case "add_Carro":
                return Integer.toString(new CarroController().add(CarroJSON.getCarroJSON(object.getJSONObject("carro"))));
            case "add_Cliente":
                return Integer.toString(new ClienteController().add(ClienteJSON.getClienteJSON(object.getJSONObject("cliente"))));
            case "add_Funcionario":
                return Integer.toString(new FuncionarioController().add(FuncionarioJSON.getFuncionarioJSON(object.getJSONObject("funcionario"))));
            case "add_OrdemServico":
                return Integer.toString(new OrdemServicoController().add(OrdemServicoJSON.getOrdemServicoJSON(object.getJSONObject("ordemservico"))));
            case "add_Servico":
                return Integer.toString(new ServicoController().add(ServicoJSON.getServicoJSON(object.getJSONObject("servico"))));
            case "add_Servico_OS":
                return Integer.toString(new Servico_OSController().add(Servico_OSJSON.getServico_OSJSON(object.getJSONObject("servico_os"))));

            // ------------------------------- REMOVE object ------------------------- //
            case "rmv_Carro":
                new CarroController().remove(CarroJSON.getCarroJSON(object.getJSONObject("carro")).getCod());
                return "true";
            case "rmv_Cliente":
                new ClienteController().remove(ClienteJSON.getClienteJSON(object.getJSONObject("cliente")).getCodigo());
                return "true";
            case "rmv_Funcionario":
                new FuncionarioController().remove(FuncionarioJSON.getFuncionarioJSON(object.getJSONObject("funcionario")).getCodigo());
                return "true";
            case "rmv_OrdemServico":
                new OrdemServicoController().remove(OrdemServicoJSON.getOrdemServicoJSON(object.getJSONObject("ordemservico")).getCod());
                return "true";
            case "rmv_Servico":
                new ServicoController().remove(ServicoJSON.getServicoJSON(object.getJSONObject("servico")).getCod());
                return "true";
            case "rmv_Servico_OS":
                new Servico_OSController().remove(Servico_OSJSON.getServico_OSJSON(object.getJSONObject("servico_os")).getCod());
                return "true";

            // ------------------------------- Default ------------------------- //    
            default:
                break;
        }
        return null;
    }
}
