package model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue(value="F")
public class Funcionario extends Pessoa implements Serializable {

    private Login login;

    public Funcionario(String nome, String sexo, String email, String telefoneM, String telefoneF, String cpf, String rg, Endereco endereco, Login login) {
        super(nome, cpf, sexo, email, telefoneM, telefoneF, endereco, rg);
        this.login = login;
    }

    public Funcionario() {
        super();
    }

    @OneToOne(cascade = {CascadeType.ALL})
    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

}
