package twitter.classification.web.resource.exceptions;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import twitter.classification.web.render.TemplateRender;

import static java.util.Collections.emptyMap;

/**
 * ExceptionMapper to display custom HTML when a 404 is encountered
 * through no route matching the url or by explicitly throwing {@link NotFoundException}
 * in a method/resource
 */
@Provider
public class NotFoundExceptionResourceMapper implements ExceptionMapper<NotFoundException> {

  private TemplateRender render;

  @Inject
  public NotFoundExceptionResourceMapper(TemplateRender render) {

    this.render = render;
  }

  @Override
  public Response toResponse(NotFoundException exception) {

    String template = render.render("exceptions/not-found", emptyMap());

    return Response.status(Response.Status.NOT_FOUND)
        .header("Content-type", "text/html")
        .entity(template)
        .build();
  }
}
