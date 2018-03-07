package twitter.classification.classifier.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbIntegrationHelper {

  public static String getUsername(Connection connection, Long twitterUserId) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("SELECT username FROM users WHERE twitter_id = ?;");
    preparedStatement.setObject(1, twitterUserId);

    ResultSet resultSet = preparedStatement.executeQuery();

    resultSet.next();
    return resultSet.getString(1);
  }

  public static void cleanUpHashtagClassificationTable(Connection connection, Long twitterTweetId) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM hashtag_tweet_classifications WHERE tweet_id = (SELECT id FROM tweets WHERE tweet_id = ?);");
    preparedStatement.setObject(1, twitterTweetId);

    preparedStatement.executeUpdate();
  }

  public static void cleanUpHashtagTable(Connection connection, String hashtagValue) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM hashtags WHERE hashtag_value = ?;");
    preparedStatement.setObject(1, hashtagValue);

    preparedStatement.executeUpdate();
  }


  public static void cleanUpUsersClassificationTable(Connection connection, Long twitterTweetId) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users_tweet_classifications WHERE tweet_id = (SELECT id FROM tweets WHERE tweet_id = ?);");
    preparedStatement.setObject(1, twitterTweetId);

    preparedStatement.executeUpdate();
  }

  public static void cleanUpUsersTable(Connection connection, Long twitterUserId) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE twitter_id = ?;");
    preparedStatement.setObject(1, twitterUserId);

    preparedStatement.executeUpdate();
  }

  public static void cleanUpTweetsTable(Connection connection, Long twitterTweetId) throws SQLException {

    PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tweets WHERE tweet_id = ?;");
    preparedStatement.setObject(1, twitterTweetId);

    preparedStatement.executeUpdate();
  }
}
