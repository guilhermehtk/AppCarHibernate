package model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="2")
public class Cliente extends Pessoa implements Serializable {

    public Cliente() {
        super();
    }

    public Cliente(String nome, String sexo, String email, String telefoneM, String telefoneF, String cpf, String rg, int tipo, Endereco endereco) {
        super(nome, cpf, sexo, email, telefoneM, telefoneF, endereco, rg);
    }
}
