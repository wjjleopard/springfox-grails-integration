package springfox.documentation.grails;

import grails.util.GrailsNameUtils;
import org.grails.datastore.mapping.model.PersistentEntity;

public class Entities {

  private Entities() {
    throw new UnsupportedOperationException();
  }

  /**
   * follow the logic in {@link org.grails.core.AbstractGrailsClass#AbstractGrailsClass(Class,
   * String)}
   *
   * @param entity instance of {@link PersistentEntity}
   * @return the logic property name for entity
   */
  public static String getEntityLogicalPropertyName(PersistentEntity entity) {
    String name = GrailsNameUtils.getLogicalName(entity.getJavaClass(), "");
    return GrailsNameUtils.getPropertyNameRepresentation(name);
  }
}
