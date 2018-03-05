package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.persist.jdbc.InsertTweetsDao;

public class InsertTweetsService {

  private final InsertTweetsDao insertTweetsDao;

  @Inject
  public InsertTweetsService(InsertTweetsDao insertTweetsDao) {

    this.insertTweetsDao = insertTweetsDao;
  }

  public void insert(Long tweetId, String originalTweetText, String processedTweetText, String classificationCode) {

    insertTweetsDao.insert(tweetId, originalTweetText, processedTweetText, classificationCode);
  }
}
