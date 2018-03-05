package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.persist.jdbc.InsertHashtagTweetClassificationDao;
import twitter.classification.classifier.persist.jdbc.InsertHashtagsDao;

public class InsertHashtagEntitiesService {

  private InsertHashtagsDao insertHashtagsDao;
  private InsertHashtagTweetClassificationDao insertHashtagTweetClassificationDao;

  @Inject
  public InsertHashtagEntitiesService(
      InsertHashtagsDao insertHashtagsDao,
      InsertHashtagTweetClassificationDao insertHashtagTweetClassificationDao
  ) {


    this.insertHashtagsDao = insertHashtagsDao;
    this.insertHashtagTweetClassificationDao = insertHashtagTweetClassificationDao;
  }

  public void insert(String hashtagValue, Long twitterTweetId) {

    insertHashtagsDao.insert(hashtagValue);
    insertHashtagTweetClassificationDao.insert(hashtagValue, twitterTweetId);
  }
}
