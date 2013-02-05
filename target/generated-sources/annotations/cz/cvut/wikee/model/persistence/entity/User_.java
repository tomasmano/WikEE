package cz.cvut.wikee.model.persistence.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends cz.cvut.wikee.model.persistence.entity.WikeeEntity_ {

	public static volatile SingularAttribute<User, String> username;
	public static volatile ListAttribute<User, WikeeEntity> createdItems;
	public static volatile SingularAttribute<User, Role> role;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> password;

}

