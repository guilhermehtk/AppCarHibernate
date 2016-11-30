package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Funcionario.class)
public abstract class Funcionario_ extends model.Pessoa_ {

	public static volatile SingularAttribute<Funcionario, Login> login;
	public static volatile ListAttribute<Funcionario, Servico_OS> servicosRealizados;

}

