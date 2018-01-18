package twitter.classification.stream.application;

import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterObjectFactory;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStream {

  public static void main(String[] args) {

    StatusListener listener = new StatusListener() {
      @Override
      public void onStatus(Status status) {

        JsonObject jsonObject = new JsonParser().parse(TwitterObjectFactory.getRawJSON(status)).getAsJsonObject();

        if (!status.isRetweet() && jsonObject.get("in_reply_to_status_id_str").getAsJsonNull() == JsonNull.INSTANCE) {
          System.out.println(TwitterObjectFactory.getRawJSON(status));
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
    };

    twitter4j.TwitterStream twitterStream = new TwitterStreamFactory(new ConfigurationBuilder()
        .setOAuthConsumerKey("8tiAe4cH2pJuqEYqubnXdTJIW")
        .setOAuthConsumerSecret("JPH7Z54WKhCl1VAUxDQ1A7MXhmlrrrwD3GVeAdLspj6BpPPaTC")
        .setOAuthAccessToken("107532674-kKxo0qNVb9os1Dg8pnfiqqDYKneYrzxoS0ybsOtI")
        .setOAuthAccessTokenSecret("3ZlnJipIlkWbzRll6zZWuBAPCuzNSOw2iMROv8Tum9XBe")
        .setJSONStoreEnabled(true)
        .build()).getInstance();

    twitterStream.addListener(listener);

    String[] filterList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "w", "x", "y", "z"};

    twitterStream.filter(new FilterQuery(filterList).language("en"));
  }
}
