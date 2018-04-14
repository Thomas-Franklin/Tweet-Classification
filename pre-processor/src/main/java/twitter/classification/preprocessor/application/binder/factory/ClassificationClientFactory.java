package twitter.classification.preprocessor.application.binder.factory;

import javax.inject.Inject;

import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.binder.factory.BaseFactory;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.preprocessor.client.ClassificationClient;

public class ClassificationClientFactory implements BaseFactory<ClassificationClient> {

  private String classifierUri;

  @Inject
  public ClassificationClientFactory(
      @ConfigurationVariableParam(variable = ConfigurationVariable.CLASSIFIER_CLASSIFICATION_URI) String classifierUri
  ) {

    this.classifierUri = classifierUri;
  }

  @Override
  public ClassificationClient provide() {

    return new ClassificationClient(classifierUri);
  }
}
