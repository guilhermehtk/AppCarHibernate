package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servico.class)
public abstract class Servico_ {

	public static volatile SingularAttribute<Servico, Double> valor;
	public static volatile SingularAttribute<Servico, Integer> cod;
	public static volatile SingularAttribute<Servico, String> descricao;
	public static volatile ListAttribute<Servico, Servico_OS> servicosRealizados;

}

