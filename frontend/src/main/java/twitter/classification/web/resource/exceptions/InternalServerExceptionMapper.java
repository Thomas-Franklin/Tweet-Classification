package twitter.classification.web.resource.exceptions;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.web.render.TemplateRender;

import static java.util.Collections.emptyMap;

/**
 * ExceptionMapper to display custom HTML when no other error handler is thrown and an exception
 * is thrown that is not caught in a method/resource
 */
@Provider
public class InternalServerExceptionMapper implements ExceptionMapper<Throwable> {

  private static final Logger logger = LoggerFactory.getLogger(InternalServerExceptionMapper.class);

  private TemplateRender templateRender;

  @Inject
  public InternalServerExceptionMapper(TemplateRender templateRender) {

    this.templateRender = templateRender;
  }

  @Override
  public Response toResponse(Throwable exception) {

    logger.error("Encountered an error", exception);

    String template = templateRender.render("exceptions/exception", emptyMap());

    return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
        .header("Content-type", "text/html")
        .entity(template)
        .build();
  }
}
