package cz.cvut.wikee.model.persistence.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WikeeEntity.class)
public abstract class WikeeEntity_ {

	public static volatile SingularAttribute<WikeeEntity, Integer> id;
	public static volatile SingularAttribute<WikeeEntity, Date> updated;
	public static volatile SingularAttribute<WikeeEntity, Date> created;
	public static volatile ListAttribute<WikeeEntity, WikeeEntity> partOf;
	public static volatile ListAttribute<WikeeEntity, WikeeEntity> contains;
	public static volatile SingularAttribute<WikeeEntity, User> creator;

}

