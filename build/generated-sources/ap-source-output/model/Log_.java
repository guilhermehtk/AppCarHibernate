package model;

import java.sql.Timestamp;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Log.class)
public abstract class Log_ {

	public static volatile SingularAttribute<Log, Timestamp> data;
	public static volatile SingularAttribute<Log, Integer> cod;
	public static volatile SingularAttribute<Log, Funcionario> funcionario;
	public static volatile SingularAttribute<Log, String> descricao;

}

