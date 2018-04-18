package twitter.classification.api.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbIntegrationHelper {

  public static void insertInToUserTweetClassification(Connection connection, Long twitterUserId, Long twitterTweetId) throws SQLException {

    String sql = "INSERT IGNORE INTO users_tweet_classifications (user_id, tweet_id) SELECT users.id, tweets.id FROM users, tweets WHERE users.twitter_id = ? AND tweets.tweet_id = ?;";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setObject(1, twitterUserId);
    preparedStatement.setObject(2, twitterTweetId);

    preparedStatement.executeUpdate();
  }

  public static void insertInToUsers(Connection connection, String username, Long twitterUsernameId) throws SQLException {

    String sql = "INSERT IGNORE INTO users (username, twitter_id) VALUES (?, ?);";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setObject(1, username);
    preparedStatement.setObject(2, twitterUsernameId);

    preparedStatement.executeUpdate();
  }

  public static void insertInToTweets(Connection connection, Long tweetId, String originalTweetText, String processedTweetText, int minute, String classificationCode) throws SQLException {

    String sql = "INSERT IGNORE INTO tweets (tweet_id, original_tweet_text, processed_tweet_text, classification_id, created_on) SELECT ?, ?, ?, classification_types.id, DATE_SUB(NOW(), INTERVAL ? MINUTE) FROM classification_types WHERE classification_types.classification_code = ?;";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setObject(1, tweetId);
    preparedStatement.setObject(2, originalTweetText);
    preparedStatement.setObject(3, processedTweetText);
    preparedStatement.setObject(4, minute);
    preparedStatement.setObject(5, classificationCode);

    preparedStatement.executeUpdate();
  }

  public static void insertInToHashtagsTweetClassification(Connection connection, String hashtagValue, Long twitterTweetId) throws SQLException {

    String sql = "INSERT IGNORE INTO hashtag_tweet_classifications (hashtag_id, tweet_id) SELECT hashtags.id, tweets.id FROM hashtags, tweets WHERE hashtags.hashtag_value = ? AND tweets.tweet_id = ?;";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setObject(1, hashtagValue);
    preparedStatement.setObject(2, twitterTweetId);

    preparedStatement.executeUpdate();
  }

  public static void insertInToHashtags(Connection connection, String hashtagValue) throws SQLException {

    String sql = "INSERT IGNORE INTO hashtags (hashtag_value) VALUES (?);";

    PreparedStatement preparedStatement = connection.prepareStatement(sql);

    preparedStatement.setObject(1, hashtagValue);

    preparedStatement.executeUpdate();
  }
}
