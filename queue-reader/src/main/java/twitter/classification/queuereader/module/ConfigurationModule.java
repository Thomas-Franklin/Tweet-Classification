package twitter.classification.queuereader.module;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import twitter.classification.common.system.helper.FileVariables;
import twitter.classification.queuereader.consumer.TweetConsumer;
import twitter.classification.queuereader.reader.QueueReader;
import twitter.classification.queuereader.tweetdetails.TweetDetailsClient;

public class ConfigurationModule extends AbstractModule {

  private static final Logger logger = LoggerFactory.getLogger(ConfigurationModule.class);

  @Override
  protected void configure() {

    Names.bindProperties(binder(), FileVariables.properties);
  }

  @Provides
  public QueueReader provideQueueReader(
      @Named("QUEUE_USER") String queueUsername,
      @Named("QUEUE_PASSWORD") String queuePassword,
      @Named("QUEUE_HOST") String queueHost) throws IOException, TimeoutException {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setUsername(queueUsername);
    connectionFactory.setPassword(queuePassword);
    connectionFactory.setHost(queueHost);

    Connection connection = connectionFactory.newConnection();

    Channel channel = connection.createChannel();

    channel.queueDeclare("tweets", false, false, false, null);

    return new QueueReader(channel, new TweetConsumer(channel, new TweetDetailsClient("http://pre-processor:8080/process")));
  }
}
