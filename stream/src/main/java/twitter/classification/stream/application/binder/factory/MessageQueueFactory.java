package twitter.classification.stream.application.binder.factory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.binder.factory.BaseFactory;
import twitter.classification.common.system.helper.ConfigurationVariableParam;

public class MessageQueueFactory implements BaseFactory<Connection> {

  private static final Logger logger = LoggerFactory.getLogger(MessageQueueFactory.class);

  private Connection connection;

  @Inject
  public MessageQueueFactory(
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_HOST) String queueHost,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_USER) String queueUser,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_PASSWORD) String queuePassword
  ) {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(queueHost);
    factory.setUsername(queueUser);
    factory.setPassword(queuePassword);

    try {

      connection = factory.newConnection();
    } catch (IOException | TimeoutException e) {

      logger.error("Issue creating queue", e);
    }
  }

  @Override
  public Connection provide() {

    return connection;
  }
}
