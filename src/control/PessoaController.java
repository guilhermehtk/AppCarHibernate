package control;

import java.util.ArrayList;
import model.dao.PessoaDao;
import model.Pessoa;

public class PessoaController implements InterfaceControllerCrud {

    PessoaDao pesDao = new PessoaDao();

    @Override
    public int add(Object pessoa) {
        return pesDao.add((Pessoa) pessoa);
    }

    @Override
    public void remove(int id) {
        pesDao.remove(id);
    }

    @Override
    public void altera(Object pessoa) {
        Pessoa pes = (Pessoa) pessoa;
        pesDao.altera(pes);
    }

    @Override
    public Object get(int id) {
        return pesDao.get(id);
    }

    @Override
    public ArrayList<model.Pessoa> getAll() {
        return pesDao.getAll();
    }

    @Override
    public ArrayList<String> valida(Object objeto) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

  
}
