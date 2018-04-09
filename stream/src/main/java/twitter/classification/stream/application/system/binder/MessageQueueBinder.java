package twitter.classification.stream.application.system.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.rabbitmq.client.Connection;
import twitter.classification.stream.application.system.binder.factory.MessageQueueFactory;

public class MessageQueueBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(MessageQueueFactory.class).to(Connection.class);
  }
}
