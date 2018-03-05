package twitter.classification.classifier.service;

import javax.inject.Inject;

import twitter.classification.classifier.persist.jdbc.InsertUsersDao;

public class InsertUsersService {

  private final InsertUsersDao insertUsersDao;

  @Inject
  public InsertUsersService(InsertUsersDao insertUsersDao) {

    this.insertUsersDao = insertUsersDao;
  }

  public void insert(String username, Long twitterUsernameId) {

    insertUsersDao.insert(username, twitterUsernameId);
  }
}
