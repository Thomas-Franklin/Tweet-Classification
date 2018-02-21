package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.helper.ClassificationCodeFromValue;
import twitter.classification.classifier.persist.DbConnection;
import twitter.classification.common.tweetdetails.model.ProcessedTweetModel;

public class HandleProcessedTweetService {

  private InsertTweetsService insertTweetsService;

  @Inject
  public HandleProcessedTweetService(InsertTweetsService insertTweetsService) {

    this.insertTweetsService = insertTweetsService;
  }

  @DbConnection
  public void handleProcessedTweet(ProcessedTweetModel processedTweetModel) {

    insertTweetsService.insertTweet(
        processedTweetModel.getTweetId(),
        processedTweetModel.getOriginalTweetBody(),
        processedTweetModel.getProcessedTweetBody(),
        ClassificationCodeFromValue.getClassificationCodeFromValue(processedTweetModel.getClassificationValue())
    );
  }
}
