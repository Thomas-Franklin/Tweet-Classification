package twitter.classification.web.render;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.cache.ConcurrentMapTemplateCache;
import com.github.jknack.handlebars.io.ClassPathTemplateLoader;
import com.github.jknack.handlebars.io.TemplateLoader;

public class HandleBarsTemplateRender implements TemplateRender {

  private static final Logger logger = LoggerFactory.getLogger(HandleBarsTemplateRender.class);

  private static final String LIST_GROUP_COLOUR = "listGroupColour";
  private static final String INCREMENT_INDEX = "increment";
  private static final String PATH_IS_ACTIVE = "pathIsActive";

  private final Handlebars handlebars;

  public HandleBarsTemplateRender() {

    TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");

    handlebars = new Handlebars(loader)
        .registerHelper(LIST_GROUP_COLOUR, listGroupColourHelper())
        .registerHelper(INCREMENT_INDEX, rankIncreaseHelper())
        .registerHelper(PATH_IS_ACTIVE, HandleBarsTemplateRender::pathEqualsHelper)
        .with(new ConcurrentMapTemplateCache());
  }

  @Override
  public String render(String templateName, Object context) {

    try {
      return handlebars.compile(templateName).apply(context);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private static Helper<Object> listGroupColourHelper() {

    return ((context, options) -> options.param(0) ? "list-group-item-success" : "list-group-item-danger");
  }

  private static Helper<Object> rankIncreaseHelper() {

    return (context, options) -> ((Integer) context + 1);
  }

  /**
   * Helper to highlight which navigation tab
   * is highlighted depending on users paths
   *
   * @param context {@link Object} this is the context of the handlebars page
   * @param options {@link Options} Options that are passed to the helper such as current path and comparator
   * @return {@link String} either active or nothing to highlight the tabs
   */
  private static String pathEqualsHelper(Object context, Options options) {

    if (options.param(0) == null) {

      return "";
    }

    if (options.param(0).equals(options.param(1))) {

      return "active";
    }

    return "";
  }
}
