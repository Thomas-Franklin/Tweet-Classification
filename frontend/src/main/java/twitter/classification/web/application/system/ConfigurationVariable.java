package twitter.classification.web.application.system;

import twitter.classification.web.config.ConfigurationKey;

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
