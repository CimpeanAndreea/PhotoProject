package project.core.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Album.class)
public abstract class Album_ extends project.core.model.BaseEntity_ {

	public static volatile SingularAttribute<Album, Long> photographerId;
	public static volatile SingularAttribute<Album, Long> clientId;
	public static volatile SingularAttribute<Album, String> photoSessionName;

	public static final String PHOTOGRAPHER_ID = "photographerId";
	public static final String CLIENT_ID = "clientId";
	public static final String PHOTO_SESSION_NAME = "photoSessionName";

}

