package springfox.documentation.grails;

import com.fasterxml.classmate.TypeResolver;

class ShowActionSpecificationFactory implements ActionSpecificationFactory {
  private final TypeResolver resolver;

  public ShowActionSpecificationFactory(TypeResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  public ActionSpecification create(GrailsActionContext context) {
    return new ActionSpecification(
        context.path(),
        context.getRequestMethods(),
        context.supportedMediaTypes(),
        context.supportedMediaTypes(),
        context.handlerMethod(),
        context.pathParameters(),
        resolver.resolve(entityClass(context.getPersistentEntity())));
  }
}
