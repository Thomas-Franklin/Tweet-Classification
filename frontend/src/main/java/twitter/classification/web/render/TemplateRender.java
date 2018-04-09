package twitter.classification.web.render;

public interface TemplateRender {

  /**
   * Rendering of a template based on a template name
   *
   * @param templateName String
   * @param context      Object
   * @return template String
   */
  String render(String templateName, Object context);
}
