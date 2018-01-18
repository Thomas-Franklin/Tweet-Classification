package twitter.classification.web.application.system.binder;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.web.render.HandleBarsTemplateRender;
import twitter.classification.web.render.TemplateRender;

public class TemplateRenderBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bind(HandleBarsTemplateRender.class).to(TemplateRender.class).in(Singleton.class);
  }
}
