package twitter.classification.classifier.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.classifier.model.ClassificationModel;
import twitter.classification.classifier.model.PostModel;
import twitter.classification.classifier.service.NaiveBayesClassifier;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;

@Singleton
@Path("/classify")
public class ClassificationResource {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationResource.class);

  private NaiveBayesClassifier classifier;
  private String host;

  @Inject
  public ClassificationResource(NaiveBayesClassifier classifier, @ConfigurationVariableParam(variable = ConfigurationVariable.QUEUE_HOST) String host) {

    this.host = host;
    this.classifier = classifier;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClassificationModel getClassifyForTweet(PostModel postModel) {

    logger.info(host);

    return new ClassificationModel().setLabel(classifier.classifyTweet(postModel.getText()).toString());
  }
}
