package twitter.classification.queuereader.consumer;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import twitter.classification.common.tweetdetails.model.ProcessedStatusResponse;
import twitter.classification.queuereader.application.exceptions.IgnoredHashtagEntity;
import twitter.classification.queuereader.tweetdetails.TweetDetailsClient;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.TwitterObjectFactory;

public class TweetConsumer extends DefaultConsumer {

  private static final Logger logger = LoggerFactory.getLogger(TweetConsumer.class);

  private TweetDetailsClient client;
  private String[] hashtagIgnoreList;

  public TweetConsumer(Channel channel, TweetDetailsClient client, String hashtagIgnoreList) {

    super(channel);

    this.client = client;

    this.hashtagIgnoreList = hashtagIgnoreList.split(",");
  }

  @Override
  public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
      throws IOException {

    String message = new String(body, "UTF-8");

    try {

      logger.debug("Handling message with body of {}", message);

      Status status = TwitterObjectFactory.createStatus(message);

      for (HashtagEntity hashtagEntity : status.getHashtagEntities()) {
        for(String hashtagIgnore : hashtagIgnoreList) {
          if (hashtagEntity.getText().toLowerCase().equals(hashtagIgnore)) {
            throw new IgnoredHashtagEntity(String.format("Hashtag %s is in the ignore list", hashtagEntity.getText().toLowerCase()));
          }
        }
      }

      Optional<ProcessedStatusResponse> response = client.postStatusForProcessing(message);

      if (response.isPresent())
        logger.debug("Response handled correctly: {}", new ObjectMapper().writeValueAsString(response.get()));

    } catch (IgnoredHashtagEntity e) {
      logger.error(e.getMessage());
    } catch (Exception e) {
      logger.error("Issue handling message", e);
    }
  }
}
