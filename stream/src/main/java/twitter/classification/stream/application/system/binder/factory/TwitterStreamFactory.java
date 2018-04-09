package twitter.classification.stream.application.system.binder.factory;

import javax.inject.Inject;

import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.binder.factory.BaseFactory;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter4j.TwitterStream;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamFactory implements BaseFactory<TwitterStream> {

  private final TwitterStream twitterStream;

  @Inject
  public TwitterStreamFactory(
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_KEY) String oauthAccessKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_ACCESS_SECRET) String oauthAccessSecret,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_KEY) String oauthConsumerKey,
      @ConfigurationVariableParam(variable = ConfigurationVariable.TWITTER_OAUTH_CONSUMER_SECRET) String oauthConsumerSecret
  ) {

    twitterStream = new twitter4j.TwitterStreamFactory(new ConfigurationBuilder()
        .setTweetModeExtended(true)
        .setOAuthConsumerKey(oauthConsumerKey)
        .setOAuthConsumerSecret(oauthConsumerSecret)
        .setOAuthAccessToken(oauthAccessKey)
        .setOAuthAccessTokenSecret(oauthAccessSecret)
        .setJSONStoreEnabled(true)
        .build()
    ).getInstance();
  }

  @Override
  public TwitterStream provide() {

    return twitterStream;
  }
}
