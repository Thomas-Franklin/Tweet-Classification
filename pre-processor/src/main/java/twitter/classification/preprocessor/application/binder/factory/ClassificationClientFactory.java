package twitter.classification.preprocessor.application.binder.factory;

import javax.inject.Inject;

import twitter.classification.common.system.binder.factory.BaseFactory;
import twitter.classification.preprocessor.client.ClassificationClient;

public class ClassificationClientFactory implements BaseFactory<ClassificationClient> {

  @Inject
  public ClassificationClientFactory() {


  }

  @Override
  public ClassificationClient provide() {

    return new ClassificationClient("http://classifier:8080/classify");
  }
}
