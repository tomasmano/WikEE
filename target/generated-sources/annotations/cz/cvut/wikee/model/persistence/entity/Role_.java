package cz.cvut.wikee.model.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Role.class)
public abstract class Role_ extends cz.cvut.wikee.model.persistence.entity.WikeeEntity_ {

	public static volatile SingularAttribute<Role, String> name;
	public static volatile ListAttribute<Role, User> members;

}

