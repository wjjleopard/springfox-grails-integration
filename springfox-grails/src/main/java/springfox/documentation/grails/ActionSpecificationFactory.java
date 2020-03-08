package springfox.documentation.grails;

import org.grails.datastore.mapping.model.PersistentEntity;

@FunctionalInterface
public interface ActionSpecificationFactory {
  default Class<?> idType(PersistentEntity entity) {
    return entity != null ? entity.getIdentity().getType() : Void.TYPE;
  }

  default Class entityClass(PersistentEntity entity) {
    if (entity != null) {
      return entity.getJavaClass();
    }
    return Void.TYPE;
  }

  ActionSpecification create(GrailsActionContext actionContext);
}
