package springfox.documentation.grails

import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty
import spock.lang.Specification
import spock.lang.Unroll


class DefaultGrailsPropertyTransformerSpec extends Specification {
  @Unroll
  def "Infers types correctly for grails property #name" (){
    given:
      def sut = new DefaultGrailsPropertyTransformer()
    when:
      def transformed = sut.apply(property)
    then:
      transformed.name == name
      transformed.clazz == type
    where:
      property                        | name                | type
      id()                            | "id"                | Long
      relatedEntityProperty()         | "relatedEntity"     | RelatedEntity
      scalarProperty("test", String)  | "test"              | String
  }

  def id() {
    scalarProperty("id", Long)
  }

  def scalarProperty(propertyName, propertyType) {
    def property = Mock(PersistentProperty)
    property.type >> propertyType
    property.name >> propertyName
    property
  }

  def relatedEntityProperty() {
    def property = Mock(PersistentProperty)
    property.type >> RelatedEntity
    property.name >> "relatedEntity"
    property.owner >> relatedEntityDomain()
    property
  }

  PersistentEntity relatedEntityDomain() {
    def entity = Mock(PersistentEntity)
    entity.identity >> id()
    entity
  }

  class RelatedEntity {
  }
}
