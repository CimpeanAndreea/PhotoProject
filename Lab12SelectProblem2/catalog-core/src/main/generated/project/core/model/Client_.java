package project.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ extends project.core.model.BaseEntity_ {

	public static volatile SetAttribute<Client, Photoshoot> photoshoots;
	public static volatile SingularAttribute<Client, String> phoneNumber;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, String> email;

	public static final String PHOTOSHOOTS = "photoshoots";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String NAME = "name";
	public static final String EMAIL = "email";

}

