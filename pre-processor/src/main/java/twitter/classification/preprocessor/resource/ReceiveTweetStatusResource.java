package twitter.classification.preprocessor.resource;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter.classification.common.exceptions.ProcessingClientException;
import twitter.classification.common.models.PreProcessorStatusResponse;
import twitter.classification.common.system.ConfigurationVariable;
import twitter.classification.common.system.helper.ConfigurationVariableParam;
import twitter.classification.common.tweetdetails.model.PreProcessedItem;
import twitter.classification.common.tweetdetails.model.ProcessedStatusResponse;
import twitter.classification.common.tweetdetails.processing.TweetBodyProcessor;
import twitter.classification.preprocessor.client.ClassificationClient;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

@Singleton
@Path("/process")
public class ReceiveTweetStatusResource {

  private static final Logger logger = LoggerFactory.getLogger(ReceiveTweetStatusResource.class);

  private ClassificationClient classificationClient;
  private boolean usePreProcessing;

  @Inject
  public ReceiveTweetStatusResource(
      ClassificationClient classificationClient,
      @ConfigurationVariableParam(variable = ConfigurationVariable.USE_PRE_PROCESSING) String usePreProcessing
  ) {

    this.classificationClient = classificationClient;
    this.usePreProcessing = Boolean.parseBoolean(usePreProcessing);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public ProcessedStatusResponse receiveStatus(String tweetDetails) {

    logger.debug("Tweet body is {}", tweetDetails);

    ProcessedStatusResponse processedStatusResponse = new ProcessedStatusResponse();

    try {

      // converting the JSON to the Twitter4J Status object
      Status status = TwitterObjectFactory.createStatus(tweetDetails);

      processedStatusResponse.setTweetBody(status.getText());
      processedStatusResponse.setUserName(status.getUser().getScreenName());
      processedStatusResponse.setHashtag(status.getHashtagEntities()[0] != null ? status.getHashtagEntities()[0].getText() : "NO-HASHTAG");

      // preparing the preprocessed item which will be sent for classification
      PreProcessedItem preProcessedItem = new PreProcessedItem();

      preProcessedItem.setUsername(status.getUser().getScreenName());
      preProcessedItem.setTweetId(status.getId());
      preProcessedItem.setUserId(status.getUser().getId());
      preProcessedItem.setOriginalTweetBody(status.getText());

      // this is where the pre processing will occur if the configuration value is set
      if (usePreProcessing) {
        preProcessedItem.setProcessedTweetBody(TweetBodyProcessor.processTweetBody(status.getText()));
      } else {
        preProcessedItem.setProcessedTweetBody(status.getText());
      }

      logger.debug("Tweet body is: {}", preProcessedItem.getProcessedTweetBody());

      for (HashtagEntity hashtagEntity : status.getHashtagEntities()) {

        // all hashtags will be kept in lowercase format
        preProcessedItem.addHashtag(hashtagEntity.getText().toLowerCase());
      }

      classificationClient.postProcessedTweetItem(new ObjectMapper().writeValueAsString(preProcessedItem));

    } catch (TwitterException e) {
      logger.error("Issue creating status from tweet details", e);
    } catch (ProcessingClientException | JsonProcessingException e) {
      logger.info("Issue processing object", e);
    }

    return processedStatusResponse;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/status")
  public PreProcessorStatusResponse getPreProcessorStatus() {

    return new PreProcessorStatusResponse().setRunning(true);
  }
}
