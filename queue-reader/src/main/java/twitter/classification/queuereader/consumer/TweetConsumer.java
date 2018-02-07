package twitter.classification.queuereader.consumer;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import twitter.classification.common.tweetdetails.PreProcessingTweetDetails;
import twitter.classification.queuereader.tweetdetails.TweetDetailsClient;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

public class TweetConsumer extends DefaultConsumer {

  private static final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  private TweetDetailsClient client;

  public TweetConsumer(Channel channel, TweetDetailsClient client) {

    super(channel);

    this.client = client;
  }

  @Override
  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
      throws IOException {

    String message = new String(body, "UTF-8");


    try {
      Status status = TwitterObjectFactory.createStatus(message);

      logger.info("Handling message with {}", status.getText());
    } catch (TwitterException e) {
      e.printStackTrace();
    }

    client.postStatusForProcessing(message);
  }
}
