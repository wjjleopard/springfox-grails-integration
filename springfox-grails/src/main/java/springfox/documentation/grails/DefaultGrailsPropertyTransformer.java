package springfox.documentation.grails;

import org.grails.datastore.mapping.model.PersistentProperty;
import springfox.documentation.builders.AlternateTypePropertyBuilder;

public class DefaultGrailsPropertyTransformer implements GrailsPropertyTransformer {
  @Override
  public AlternateTypePropertyBuilder apply(PersistentProperty property) {
    return new AlternateTypePropertyBuilder()
        .withName(property.getName())
        .withType(property.getType())
        .withCanRead(true)
        .withCanWrite(true);
  }
}
