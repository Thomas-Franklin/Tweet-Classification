package twitter.classification.classifier.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.classifier.service.InsertTweetService;
import twitter.classification.classifier.service.NaiveBayesClassifier;
import twitter.classification.common.tweetdetails.model.ClassificationModel;
import twitter.classification.common.tweetdetails.model.PreProcessedItem;

@Singleton
@Path("/classify")
public class ClassificationResource {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationResource.class);

  private NaiveBayesClassifier classifier;
  private InsertTweetService insertTweetService;

  @Inject
  public ClassificationResource(
      NaiveBayesClassifier classifier,
      InsertTweetService insertTweetService
  ) {

    this.classifier = classifier;
    this.insertTweetService = insertTweetService;
  }

  @GET
  public String test() {

    insertTweetService.insertTweet(1000L, "Hello, this is a text", 2);

    return "Sweet";
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClassificationModel getClassificationForTweet(PreProcessedItem preProcessedItem) {

    try {
      logger.debug("PreprocessedItem is {}", new ObjectMapper().writeValueAsString(preProcessedItem));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return new ClassificationModel().setClassificationLabel(classifier.classifyTweet(preProcessedItem.getProcessedTweetBody()).toString());
  }
}
