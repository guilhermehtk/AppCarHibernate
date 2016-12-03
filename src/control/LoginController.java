package control;

import model.dao.FuncionarioDao;
import model.dao.LoginDao;
import model.Login;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {

    static LoginDao loginDao = new LoginDao();
    static FuncionarioDao mecanicoDao = new FuncionarioDao();
    static Login log;

    public static int validaLogin(Login login) {
        log = loginDao.getUsuario(login);
        if (log != null) {
            if (BCrypt.checkpw(login.getSenha(), log.getSenha())) {
                // Validou -> Retorna ID do usuário
                return mecanicoDao.getLogin(log.getCod()).getCodigo();
            } else {
                // Senha Incorreta
                return 0;
            }
        } else {
            // Usuario Incorreto
            return -1;
        }

    }

}
