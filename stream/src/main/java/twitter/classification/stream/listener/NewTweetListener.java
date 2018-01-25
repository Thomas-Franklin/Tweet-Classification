package twitter.classification.stream.listener;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import twitter.classification.stream.application.system.ConfigurationVariable;
import twitter.classification.stream.application.system.helper.ConfigurationVariableParam;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterObjectFactory;

import static twitter.classification.stream.resource.StreamTweetsResource.channel;

public class NewTweetListener implements StatusListener {

  private final static Logger logger = LoggerFactory.getLogger(NewTweetListener.class);

  @Override
  public void onStatus(Status status) {

    JsonObject jsonObject = new JsonParser().parse(TwitterObjectFactory.getRawJSON(status)).getAsJsonObject();

    if (!status.isRetweet() && jsonObject.get("in_reply_to_status_id_str").getAsJsonNull() == JsonNull.INSTANCE) {

      try {

        channel.basicPublish("", "tweets", null, TwitterObjectFactory.getRawJSON(status).getBytes());
      } catch (IOException e) {

        logger.error("Issue adding to queue", e);
      }
    }
  }

  @Override
  public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
  }

  @Override
  public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
  }

  @Override
  public void onScrubGeo(long userId, long upToStatusId) {
  }

  @Override
  public void onStallWarning(StallWarning warning) {
  }

  @Override
  public void onException(Exception ex) {
  }
}
