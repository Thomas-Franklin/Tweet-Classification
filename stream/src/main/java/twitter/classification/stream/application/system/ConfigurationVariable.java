package twitter.classification.stream.application.system;

import twitter.classification.stream.config.ConfigurationKey;

public enum ConfigurationVariable implements ConfigurationKey {

  QUEUE_HOST("QUEUE_HOST"),
  QUEUE_USER("QUEUE_USER"),
  QUEUE_PASSWORD("QUEUE_PASSWORD"),
  QUEUE_PORT("QUEUE_PORT"),
  QUEUE_NAME("QUEUE_NAME"),
  TWITTER_OAUTH_CONSUMER_KEY("TWITTER_OAUTH_CONSUMER_KEY"),
  TWITTER_OAUTH_CONSUMER_SECRET("TWITTER_OAUTH_CONSUMER_SECRET"),
  TWITTER_OAUTH_ACCESS_KEY("TWITTER_OAUTH_ACCESS_KEY"),
  TWITTER_OAUTH_ACCESS_SECRET("TWITTER_OAUTH_ACCESS_SECRET");

  final String name;

  ConfigurationVariable(String name) {

    this.name = name;
  }

  @Override
  public String getName() {


    return name;
  }
}
