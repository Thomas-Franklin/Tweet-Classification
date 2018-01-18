package twitter.classification.stream.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import twitter.classification.stream.application.system.ConfigurationVariable;
import twitter.classification.stream.application.system.helper.ConfigurationVariableParam;
import twitter.classification.stream.listener.NewTweetListener;
import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Singleton
@Path("/")
public class StreamTweetsResource {

  private static TwitterStream twitterStream;

  public StreamTweetsResource(
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_KEY) String oauthAccessKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_SECRET) String oauthAccessSecret,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_KEY) String oauthConsumerKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_SECRET) String oauthConsumerSecret
  ) {

    twitterStream = new TwitterStreamFactory(new ConfigurationBuilder()
        .setOAuthConsumerKey(oauthConsumerKey)
        .setOAuthConsumerSecret(oauthConsumerSecret)
        .setOAuthAccessToken(oauthAccessKey)
        .setOAuthAccessTokenSecret(oauthAccessSecret)
        .setJSONStoreEnabled(true)
        .build()
    ).getInstance();

    twitterStream.addListener(new NewTweetListener());
  }

  @GET
  @Path("/start")
  public void startStream() {

    String[] filterList = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
        "s", "t", "u", "v", "w", "x", "y", "z"};

    twitterStream.filter(new FilterQuery(filterList).language("en"));
  }

  @GET
  @Path("/stop")
  public void stopStream() {

    twitterStream.shutdown();
  }
}
