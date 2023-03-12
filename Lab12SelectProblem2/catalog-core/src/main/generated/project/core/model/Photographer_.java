package project.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Photographer.class)
public abstract class Photographer_ extends project.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Photographer, String> cameraBrand;
	public static volatile SingularAttribute<Photographer, String> name;
	public static volatile SingularAttribute<Photographer, Integer> rating;
	public static volatile SingularAttribute<Photographer, Integer> age;

	public static final String CAMERA_BRAND = "cameraBrand";
	public static final String NAME = "name";
	public static final String RATING = "rating";
	public static final String AGE = "age";

}

