package springfox.documentation.grails;

import java.util.function.Predicate;
import org.grails.datastore.mapping.model.PersistentProperty;

public interface GrailsPropertySelector extends Predicate<PersistentProperty> {
}
