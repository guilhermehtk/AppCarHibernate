package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Carro.class)
public abstract class Carro_ {

	public static volatile SingularAttribute<Carro, String> marca;
	public static volatile SingularAttribute<Carro, String> obs;
	public static volatile SingularAttribute<Carro, String> km;
	public static volatile SingularAttribute<Carro, String> ano;
	public static volatile SingularAttribute<Carro, Cliente> dono;
	public static volatile ListAttribute<Carro, OrdemServico> ordens;
	public static volatile SingularAttribute<Carro, String> cor;
	public static volatile SingularAttribute<Carro, Integer> cod;
	public static volatile SingularAttribute<Carro, String> chassi;
	public static volatile SingularAttribute<Carro, String> modelo;
	public static volatile SingularAttribute<Carro, String> placa;

}

