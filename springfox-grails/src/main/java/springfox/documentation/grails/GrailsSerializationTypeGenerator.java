package springfox.documentation.grails;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.grails.datastore.mapping.model.PersistentEntity;
import org.grails.datastore.mapping.model.PersistentProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.AlternateTypeBuilder;
import springfox.documentation.builders.AlternateTypePropertyBuilder;

@Component
public class GrailsSerializationTypeGenerator {

  private final GrailsPropertySelector propertySelector;
  private final GrailsPropertyTransformer propertyTransformer;
  private final GeneratedClassNamingStrategy naming;

  @Autowired
  public GrailsSerializationTypeGenerator(
      GrailsPropertySelector propertySelector,
      GrailsPropertyTransformer propertyTransformer,
      GeneratedClassNamingStrategy naming) {
    this.propertySelector = propertySelector;
    this.propertyTransformer = propertyTransformer;
    this.naming = naming;
  }

  public Class<?> from(PersistentEntity entity) {

    Set<PersistentProperty> allEntityProperties = new HashSet<>();
    if (entity.getIdentity() != null) {
      allEntityProperties.add(entity.getIdentity());
    }
    if (entity.getCompositeIdentity() != null) {
      allEntityProperties.addAll(Arrays.asList(entity.getCompositeIdentity()));
    }
    if (entity.getPersistentProperties() != null) {
      allEntityProperties.addAll(entity.getPersistentProperties());
    }

    List<AlternateTypePropertyBuilder> properties =
        allEntityProperties.stream()
            //show all persistent properties.
            //.filter(propertySelector)
            .map(propertyTransformer)
            .collect(Collectors.toList());

    return new AlternateTypeBuilder()
        .fullyQualifiedClassName(naming.name(entity.getJavaClass()))
        .withProperties(properties)
        .build();
  }
}
