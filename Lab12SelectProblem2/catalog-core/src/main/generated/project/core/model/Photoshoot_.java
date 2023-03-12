package project.core.model;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photoshoot.class)
public abstract class Photoshoot_ extends project.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Photoshoot, Date> date;
	public static volatile SetAttribute<Photoshoot, Client> clients;
	public static volatile SingularAttribute<Photoshoot, Integer> NoMaxClients;
	public static volatile SingularAttribute<Photoshoot, Integer> price;
	public static volatile SingularAttribute<Photoshoot, Location> location;

	public static final String DATE = "date";
	public static final String CLIENTS = "clients";
	public static final String NO_MAX_CLIENTS = "NoMaxClients";
	public static final String PRICE = "price";
	public static final String LOCATION = "location";

}

