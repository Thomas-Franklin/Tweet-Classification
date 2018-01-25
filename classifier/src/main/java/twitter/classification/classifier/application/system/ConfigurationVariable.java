package twitter.classification.classifier.application.system;

import twitter.classification.classifier.config.ConfigurationKey;

public enum ConfigurationVariable implements ConfigurationKey {

  DB_USERNAME("DB_USERNAME");

  final String name;

  ConfigurationVariable(String name) {

    this.name = name;
  }

  @Override
  public String getName() {


    return name;
  }
}
