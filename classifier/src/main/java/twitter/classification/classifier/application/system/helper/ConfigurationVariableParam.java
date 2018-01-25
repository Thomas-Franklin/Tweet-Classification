package twitter.classification.classifier.application.system.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import twitter.classification.classifier.application.system.ConfigurationVariable;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface ConfigurationVariableParam {

  ConfigurationVariable variable();
}
