package springfox.documentation.grails


import org.grails.datastore.mapping.model.PersistentEntity
import spock.lang.Specification

class EntitiesSpec extends Specification {

  def "cannot instantiate this class"() {
    when:
    new Entities()
    then:
    thrown(UnsupportedOperationException)
  }

  def "Get entity logical property name"() {
    given:
    def entity = Mock(PersistentEntity)
    entity.getJavaClass() >> entityClass

    when:
    def logicalPropertyName = Entities.getEntityLogicalPropertyName(entity)

    then:
    logicalPropertyName == expectedPropertyName

    where:
    entityClass  | expectedPropertyName
    ADomain      | "ADomain"
    EntitiesSpec | "entitiesSpec"
    Entities     | "entities"
  }
}
