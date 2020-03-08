package springfox.documentation.grails

import grails.core.GrailsControllerClass
import org.grails.datastore.mapping.model.PersistentEntity
import org.grails.datastore.mapping.model.PersistentProperty
import org.grails.web.mapping.DefaultLinkGenerator
import org.grails.web.mapping.DefaultUrlMappingsHolder
import spock.lang.Specification

class ActionSpecificationFactorySpec extends Specification implements UrlMappingSupport {
  def controller
  def domain
  def links
  def urlMappings
  def actionAttributes
  def regularController

  def setup() {
    urlMappings = new DefaultUrlMappingsHolder(urlMappings())
    links = new DefaultLinkGenerator("http://localhost:8080", "")
    links.urlMappingsHolder = urlMappings
    actionAttributes = new GrailsActionAttributes(links, urlMappings)

    domain = mockEntity()
    controller = mockController()
    regularController = mockRegularController()
  }

  GrailsControllerClass mockRegularController() {
    def regularController = Mock(GrailsControllerClass)
    regularController.clazz >> BookController
    regularController.name >> "Book"
    regularController.logicalPropertyName >> "Book"
    regularController
  }

  def mockController() {
    def controller = Mock(GrailsControllerClass)
    controller.clazz >> AController
    controller.name >> "AController"
    controller.logicalPropertyName >> "A"
    controller
  }

  def mockEntity() {
    def domain = Mock(PersistentEntity)
    domain.javaClass >> ADomain
    domain.hasProperty("id", Long) >> true
    def id = idProperty()
    domain.getPropertyByName("id") >> id
    domain.getPropertyByName(_) >> {args -> property(args[0])}
    domain.hasProperty(_) >> {args -> "format" != args[0]}
    domain.identity >> id
    domain
  }

  PersistentProperty property(name) {
    def property = Mock(PersistentProperty)
    property.type >> String
    property.name >> name
    property
  }

  PersistentProperty idProperty() {
    def property = Mock(PersistentProperty)
    property.type >> Long
    property
  }
}
