package twitter.classification.stream.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import twitter.classification.common.models.TwitterStreamResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.stream.listener.NewTweetListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Singleton
@Path("/stream")
public class StreamTweetsResource {

  public static Channel channel;

  private static final Logger logger = LoggerFactory.getLogger(StreamTweetsResource.class);

  private static boolean isRunning = false;
  private static TwitterStream twitterStream;

  @Inject
  public StreamTweetsResource(
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_KEY) String oauthAccessKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_SECRET) String oauthAccessSecret,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_KEY) String oauthConsumerKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_SECRET) String oauthConsumerSecret,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_HOST) String queueHost,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_USER) String queueUser,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_PASSWORD) String queuePassword,
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_NAME) String queueName
  ) {

    twitterStream = new TwitterStreamFactory(new ConfigurationBuilder()
        .setOAuthConsumerKey(oauthConsumerKey)
        .setOAuthConsumerSecret(oauthConsumerSecret)
        .setOAuthAccessToken(oauthAccessKey)
        .setOAuthAccessTokenSecret(oauthAccessSecret)
        .setJSONStoreEnabled(true)
        .build()
    ).getInstance();

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(queueHost);
    factory.setUsername(queueUser);
    factory.setPassword(queuePassword);

    try {
      Connection connection = factory.newConnection();

      channel = connection.createChannel();

      channel.queueDeclare(queueName, false, false, false, null);
    } catch (Exception e) {

      logger.error("Issue creating queue", e);
    }

    twitterStream.addListener(new NewTweetListener());
  }

  @GET
  @Path("/start")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse startStream() {

    if (!isRunning) {
      String[] filterList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
          "s", "t", "u", "v", "w", "x", "y", "z"};

      twitterStream.filter(new FilterQuery(filterList).language("en"));

      isRunning = true;

      return new TwitterStreamResponse().setRunning(isRunning);

    } else {

      return new TwitterStreamResponse().setRunning(isRunning);
    }
  }

  @GET
  @Path("/running")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse isRunning() {

    return new TwitterStreamResponse().setRunning(isRunning);
  }

  @GET
  @Path("/stop")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse stopStream() {

    twitterStream.shutdown();

    isRunning = false;

    return new TwitterStreamResponse().setRunning(isRunning);
  }
}
