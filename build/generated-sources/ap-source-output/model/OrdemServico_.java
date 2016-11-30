package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OrdemServico.class)
public abstract class OrdemServico_ {

	public static volatile SingularAttribute<OrdemServico, Cliente> cliente;
	public static volatile SingularAttribute<OrdemServico, Carro> carro;
	public static volatile SingularAttribute<OrdemServico, String> tipo;
	public static volatile SingularAttribute<OrdemServico, Integer> situacao;
	public static volatile SingularAttribute<OrdemServico, Timestamp> data;
	public static volatile SingularAttribute<OrdemServico, Integer> cod;
	public static volatile SingularAttribute<OrdemServico, String> descricao;

}

