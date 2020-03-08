package springfox.documentation.grails;

import com.fasterxml.classmate.TypeResolver;
import springfox.documentation.service.ResolvedMethodParameter;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.grails.Parameters.*;

class SaveActionSpecificationFactory implements ActionSpecificationFactory {
  private final TypeResolver resolver;

  public SaveActionSpecificationFactory(TypeResolver resolver) {
    this.resolver = resolver;
  }

  @Override
  public ActionSpecification create(GrailsActionContext context) {
    List<ResolvedMethodParameter> parameters = new ArrayList<>(context.pathParameters());
    parameters.add(bodyParameter(
        parameters.size(),
        resolver.resolve(entityClass(context.getPersistentEntity()))));

    return new ActionSpecification(
        context.path(),
        context.getRequestMethods(),
        context.supportedMediaTypes(),
        context.supportedMediaTypes(),
        context.handlerMethod(),
        parameters,
        resolver.resolve(entityClass(context.getPersistentEntity())));
  }
}
