package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Servico_OS {

    private int cod;
    private Servico svcCod;
    private OrdemServico osCod;
    private Funcionario mecCod;

    public Servico_OS(Servico svcCod, OrdemServico osCod, Funcionario mecCod) {
        this.svcCod = svcCod;
        this.osCod = osCod;
        this.mecCod = mecCod;
    }

    public Servico_OS() {

    }

    @Id
    @GeneratedValue
    public int getCod() {
        return cod;
    }

    @OneToOne
    public Servico getSvcCod() {
        return svcCod;
    }

    public void setSvcCod(Servico svcCod) {
        this.svcCod = svcCod;
    }

    @OneToOne
    public OrdemServico getOsCod() {
        return osCod;
    }

    public void setOsCod(OrdemServico osCod) {
        this.osCod = osCod;
    }

    @OneToOne
    public Funcionario getMecCod() {
        return mecCod;
    }

    public void setMecCod(Funcionario mecCod) {
        this.mecCod = mecCod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

}
