package project.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Picture.class)
public abstract class Picture_ extends project.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Picture, Integer> width;
	public static volatile SingularAttribute<Picture, String> description;
	public static volatile SingularAttribute<Picture, String> title;
	public static volatile SingularAttribute<Picture, Integer> height;

	public static final String WIDTH = "width";
	public static final String DESCRIPTION = "description";
	public static final String TITLE = "title";
	public static final String HEIGHT = "height";

}

