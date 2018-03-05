package twitter.classification.common.persist;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation to declare Database Entity,
 * Used in Java Reflection to map result set
 * to the Java class
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {
}
