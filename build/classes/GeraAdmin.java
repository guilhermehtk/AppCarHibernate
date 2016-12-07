
import model.Endereco;
import model.Funcionario;
import model.Login;
import model.dao.FuncionarioDao;
import org.mindrot.jbcrypt.BCrypt;

public class GeraAdmin {
     public static void main(String args[]) {
         Login login = new Login("admin",BCrypt.hashpw("admin", BCrypt.gensalt()));
        Funcionario fun = new Funcionario();
        fun.setCpf("1");
        fun.setEmail("1");
        fun.setEndereco(new Endereco("1", "1", "1", "1", "1", "1"));
        fun.setLogin(login);
        fun.setNome("1");
        fun.setRg("1");
        fun.setSexo("M");
        fun.setTelefoneF("1");
        fun.setTelefoneM("1");
        FuncionarioDao dao = new FuncionarioDao();
        dao.add(fun);
     }
}
