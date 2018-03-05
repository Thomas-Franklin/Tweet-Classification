package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.helper.ClassificationCodeFromValue;
import twitter.classification.classifier.persist.DbConnection;
import twitter.classification.common.tweetdetails.model.ProcessedTweetModel;

public class HandleProcessedTweetService {

  private InsertTweetsService insertTweetsService;
  private InsertUsersService insertUsersService;
  private InsertUserTweetClassificationService insertUserTweetClassificationService;
  private InsertHashtagEntitiesService insertHashtagEntitiesService;

  @Inject
  public HandleProcessedTweetService(
      InsertTweetsService insertTweetsService,
      InsertUsersService insertUsersService,
      InsertUserTweetClassificationService insertUserTweetClassificationService,
      InsertHashtagEntitiesService insertHashtagEntitiesService
  ) {

    this.insertTweetsService = insertTweetsService;
    this.insertUsersService = insertUsersService;
    this.insertUserTweetClassificationService = insertUserTweetClassificationService;
    this.insertHashtagEntitiesService = insertHashtagEntitiesService;
  }

  @DbConnection
  public void handle(ProcessedTweetModel processedTweetModel) {

    insertTweetsService.insert(
        processedTweetModel.getTweetId(),
        processedTweetModel.getOriginalTweetBody(),
        processedTweetModel.getProcessedTweetBody(),
        ClassificationCodeFromValue.getClassificationCodeFromValue(processedTweetModel.getClassificationValue())
    );

    insertUsersService.insert(processedTweetModel.getUsername(), processedTweetModel.getUserId());

    insertUserTweetClassificationService.insert(processedTweetModel.getUserId(), processedTweetModel.getTweetId());

    for (String hashtagValue : processedTweetModel.getHashtags()) {

      insertHashtagEntitiesService.insert(hashtagValue, processedTweetModel.getTweetId());
    }
  }
}
