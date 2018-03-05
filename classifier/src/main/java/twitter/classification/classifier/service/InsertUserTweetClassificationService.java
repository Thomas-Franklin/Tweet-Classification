package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.persist.jdbc.InsertUserTweetClassificationDao;

public class InsertUserTweetClassificationService {

  private final InsertUserTweetClassificationDao insertUserTweetClassificationDao;

  @Inject
  public InsertUserTweetClassificationService(InsertUserTweetClassificationDao insertUserTweetClassificationDao) {

    this.insertUserTweetClassificationDao = insertUserTweetClassificationDao;
  }

  public void insert(Long twitterUserId, Long twitterTweetId) {

    insertUserTweetClassificationDao.insert(twitterUserId, twitterTweetId);
  }
}
