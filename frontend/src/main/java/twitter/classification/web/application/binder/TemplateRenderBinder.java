package twitter.classification.web.application.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.web.render.HandleBarsTemplateRender;
import twitter.classification.web.render.TemplateRender;

/**
 * Binding the handlebars implementation of the template engine interface
 */
public class TemplateRenderBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(HandleBarsTemplateRender.class).to(TemplateRender.class).in(Singleton.class);
  }
}
