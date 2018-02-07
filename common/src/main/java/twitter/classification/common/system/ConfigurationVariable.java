package twitter.classification.common.system;

import twitter.classification.common.config.ConfigurationKey;

public enum ConfigurationVariable implements ConfigurationKey {

  DB_USERNAME("DB_USERNAME"),
  TWITTER_OAUTH_ACCESS_KEY("TWITTER_OAUTH_ACCESS_KEY"),
  TWITTER_OAUTH_ACCESS_SECRET("TWITTER_OAUTH_ACCESS_SECRET"),
  TWITTER_OAUTH_CONSUMER_KEY("TWITTER_OAUTH_CONSUMER_KEY"),
  TWITTER_OAUTH_CONSUMER_SECRET("TWITTER_OAUTH_CONSUMER_SECRET"),
  QUEUE_HOST("QUEUE_HOST"),
  QUEUE_USER("QUEUE_USER"),
  QUEUE_PASSWORD("QUEUE_PASSWORD"),
  QUEUE_NAME("QUEUE_NAME");

  final String name;

  ConfigurationVariable(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return name;
  }
}