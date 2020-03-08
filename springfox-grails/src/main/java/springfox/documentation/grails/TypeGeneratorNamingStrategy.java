package springfox.documentation.grails;

import org.grails.datastore.mapping.model.PersistentEntity;

public interface TypeGeneratorNamingStrategy {
  String name(PersistentEntity entity);
}
