package twitter.classification.classifier.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import twitter.classification.classifier.model.ClassificationModel;
import twitter.classification.classifier.model.PostModel;
import twitter.classification.classifier.service.NaiveBayesClassifier;

@Singleton
@Path("/classify")
public class ClassificationResource {

  private NaiveBayesClassifier classifier;

  @Inject
  public ClassificationResource(NaiveBayesClassifier classifier) {

    this.classifier = classifier;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClassificationModel getClassifyForTweet(PostModel postModel) {

    return new ClassificationModel().setLabel(classifier.classifyTweet(postModel.getText()).toString());
  }
}
