package springfox.documentation.grails

import grails.core.GrailsApplication
import org.grails.datastore.mapping.model.MappingContext
import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty
import spock.lang.Specification

class GrailsSerializationTypeGeneratorSpec extends Specification {
  def "Serialization properties work" () {
    given:
      def sut = grailsGenerator()
    when:
      def clazz = sut.from(petEntity())
      def instance = clazz.newInstance()
    and:
      instance.setName("Dilip")
    then:
      clazz.declaredFields.length == 1
      clazz.declaredFields[0].name == "name"
      instance.getName() == "Dilip"
  }

  def grailsApplication() {
    def app = Mock(GrailsApplication)
    app.getMappingContext() >> Mock(MappingContext)
    app.getMappingContext().getPersistentEntities() >> [petEntity()]
    app
  }

  def petEntity() {
    def entity = Mock(PersistentEntity)
    entity.name >> "Pet"
    entity.javaClass >> Pet
    entity.persistentProperties >> [property("name", String)]
    entity
  }

  def property(name, type) {
    def property = Mock(PersistentProperty)
    property.name >> name
    property.type >> type
    property
  }

  def grailsGenerator() {
    new GrailsSerializationTypeGenerator(
        new DefaultGrailsPropertySelector(),
        new DefaultGrailsPropertyTransformer(),
        new DefaultGeneratedClassNamingStrategy())
  }
}
