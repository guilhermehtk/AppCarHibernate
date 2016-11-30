package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cliente.class)
public abstract class Cliente_ extends model.Pessoa_ {

	public static volatile ListAttribute<Cliente, OrdemServico> ordens;
	public static volatile ListAttribute<Cliente, Carro> carros;

}

