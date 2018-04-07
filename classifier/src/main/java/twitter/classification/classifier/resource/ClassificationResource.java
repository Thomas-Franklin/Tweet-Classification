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
import twitter.classification.classifier.service.HandleProcessedTweetService;
import twitter.classification.classifier.service.TrainedClassifier;
import twitter.classification.classifier.service.mallet.NaiveBayesClassifier;
import twitter.classification.common.models.ClassifierStatusResponse;
import twitter.classification.common.tweetdetails.model.ClassificationModel;
import twitter.classification.common.tweetdetails.model.PreProcessedItem;
import twitter.classification.common.tweetdetails.model.ProcessedTweetModel;

@Singleton
@Path("/classify")
public class ClassificationResource {

  private static final Logger logger = LoggerFactory.getLogger(ClassificationResource.class);

  private TrainedClassifier classifier;
  private HandleProcessedTweetService handleProcessedTweetService;

  @Inject
  public ClassificationResource(
      TrainedClassifier classifier,
      HandleProcessedTweetService handleProcessedTweetService
  ) {

    this.classifier = classifier;
    this.handleProcessedTweetService = handleProcessedTweetService;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public ClassificationModel getClassificationForTweet(PreProcessedItem preProcessedItem) {

    try {

      logger.debug("PreprocessedItem is {}", new ObjectMapper().writeValueAsString(preProcessedItem));

      ProcessedTweetModel processedTweetModel = new ProcessedTweetModel(preProcessedItem);
      processedTweetModel.setClassificationValue(classifier.classifyTweet(preProcessedItem.getProcessedTweetBody()));

      handleProcessedTweetService.handle(processedTweetModel);

    } catch (JsonProcessingException e) {

      e.printStackTrace();
    }

    return new ClassificationModel().setClassificationLabel(classifier.classifyTweet(preProcessedItem.getProcessedTweetBody()));
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/status")
  public ClassifierStatusResponse getClassifiersStatus() {

    return new ClassifierStatusResponse().setRunning(true);
  }
}
