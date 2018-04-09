package twitter.classification.stream.application.binder;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import twitter.classification.stream.application.binder.factory.TwitterStreamFactory;
import twitter4j.TwitterStream;

public class TwitterStreamBinder extends AbstractBinder {

  @Override
  protected void configure() {

    bindFactory(TwitterStreamFactory.class).to(TwitterStream.class);
  }
}
