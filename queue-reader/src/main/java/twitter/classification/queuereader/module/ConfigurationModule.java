package twitter.classification.queuereader.module;

import java.lang.reflect.Field;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.queuereader.reader.QueueReader;

public class ConfigurationModule extends AbstractModule {

  @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_HOST)
  private String queue;

  @Override
  protected void configure() {

    bindListener(Matchers.any(), new TypeListener() {
      @Override
      public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {

        Class<?> clazz = type.getRawType();

        for (Field field : clazz.getDeclaredFields()) {

          if (field.getType().isAnnotationPresent(ConfigurationVariableParam.class)) {

            System.out.println("hi");
          }
        }

      }
    });
  }

  @Provides
  public QueueReader provideQueueReader(@ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_HOST) String queue) {

    return new QueueReader(queue);
  }
}
