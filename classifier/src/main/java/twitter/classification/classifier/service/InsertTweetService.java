package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.persist.DbConnection;
import twitter.classification.classifier.persist.jdbc.InsertTweetDao;

public class InsertTweetService {

  private final InsertTweetDao insertTweetDao;

  @Inject
  public InsertTweetService(InsertTweetDao insertTweetDao) {

    this.insertTweetDao = insertTweetDao;
  }

  @DbConnection
  public void insertTweet(Long tweetId, String tweetText, Integer classificationId) {

    insertTweetDao.insertTweet(tweetId, tweetText, classificationId);
  }
}
