package twitter.classification.common.system.helper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import twitter.classification.common.system.ConfigurationVariable;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface ConfigurationVariableParam {

  ConfigurationVariable variable();
}
