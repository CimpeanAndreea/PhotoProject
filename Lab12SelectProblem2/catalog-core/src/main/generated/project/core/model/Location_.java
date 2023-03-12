package project.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Location.class)
public abstract class Location_ extends project.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Location, String> owner;
	public static volatile SingularAttribute<Location, Integer> timeLimit;
	public static volatile SingularAttribute<Location, Address> address;
	public static volatile SingularAttribute<Location, Float> entranceFee;
	public static volatile SingularAttribute<Location, String> name;

	public static final String OWNER = "owner";
	public static final String TIME_LIMIT = "timeLimit";
	public static final String ADDRESS = "address";
	public static final String ENTRANCE_FEE = "entranceFee";
	public static final String NAME = "name";

}

