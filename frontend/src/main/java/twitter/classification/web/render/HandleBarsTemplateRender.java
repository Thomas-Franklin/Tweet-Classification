package twitter.classification.web.render;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

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

  private final Handlebars handlebars;

  public HandleBarsTemplateRender() {

    TemplateLoader loader = new ClassPathTemplateLoader("/templates", ".hbs");

    handlebars = new Handlebars(loader)
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
}
