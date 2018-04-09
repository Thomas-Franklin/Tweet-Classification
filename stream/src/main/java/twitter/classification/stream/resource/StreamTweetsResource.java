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
import twitter.classification.common.models.TwitterStreamResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.stream.listener.NewTweetListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;

@Singleton
@Path("/stream")
public class StreamTweetsResource {

  private static final Logger logger = LoggerFactory.getLogger(StreamTweetsResource.class);

  private static boolean isRunning = false;

  private TwitterStream twitterStream;
  private String filterList;

  public static Channel channel;

  @Inject
  public StreamTweetsResource(
      @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_NAME) String queueName,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_FILTER_LIST) String filterList,
      TwitterStream twitterStream,
      Connection connection
  ) {

    this.twitterStream = twitterStream;

    try {

      channel = connection.createChannel();

      channel.queueDeclare(queueName, false, false, false, null);
    } catch (Exception e) {

      logger.error("Issue creating queue", e);
    }

    twitterStream.addListener(new NewTweetListener());

    this.filterList = filterList;
  }

  @GET
  @Path("/start")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse startStream() {

    if (!isRunning) {
      String[] filter = filterList.split(",");

      twitterStream.filter(new FilterQuery(filter).language("en"));

      isRunning = true;

      logger.info("Running with filter list: {}", filterList);

      return new TwitterStreamResponse().setRunning(isRunning).setFilterList(filterList);

    } else {

      return new TwitterStreamResponse().setRunning(isRunning).setFilterList(filterList);
    }
  }

  @GET
  @Path("/stop")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse stopStream() {

    twitterStream.shutdown();

    isRunning = false;

    return new TwitterStreamResponse().setRunning(isRunning).setFilterList(filterList);
  }

  @GET
  @Path("/running")
  @Produces(MediaType.APPLICATION_JSON)
  public TwitterStreamResponse isRunning() {

    return new TwitterStreamResponse().setRunning(isRunning).setFilterList(filterList);
  }
}
