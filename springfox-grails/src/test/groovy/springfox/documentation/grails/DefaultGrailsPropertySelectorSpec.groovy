package springfox.documentation.grails

import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty
import org.grails.datastore.mapping.model.types.Association
import spock.lang.Specification
import spock.lang.Unroll


class DefaultGrailsPropertySelectorSpec extends Specification {
  @Unroll
  def "selects scalar properties except version" () {
    given:
      def sut = new DefaultGrailsPropertySelector()
    expect:
      sut.test(property) == expected
    where:
      property        | expected
      version()       | false
      scalar()        | true
      entity()        | false
      versionEntity() | false
  }

  PersistentProperty version() {
    property("version", null)
  }

  PersistentProperty scalar() {
    property("name", null)
  }

  PersistentProperty entity() {
    property("name", Mock(PersistentEntity))
  }

  PersistentProperty versionEntity() {
    property("version", Mock(PersistentEntity))
  }
  
  def property(name, domain) {
    def property
    if (domain != null) {
      property = Mock(Association)
      property.associatedEntity >> domain
    }else {
      property = Mock(PersistentProperty)
    }
    property.name >> name
    property
  }
}
