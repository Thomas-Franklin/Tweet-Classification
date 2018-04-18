package twitter.classification.api.persist.jdbc.queries;

import twitter.classification.common.persist.jdbc.queries.DbQuery;

public class TimeLineForSearchTermDbQuery implements DbQuery {

  @Override
  public String buildQuery() {
    
    return "SELECT" +
        "  (SELECT count(*)" +
        "   FROM tweets" +
        "     JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "     JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "     JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "     JOIN users ON users.id = users_tweet_classifications.user_id" +
        "     JOIN classification_types ON classification_id = classification_types.id" +
        "   WHERE classification_value = 'rumour' AND tweets.created_on >= DATE_SUB(NOW(), INTERVAL 1 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?) count_of_rumours_in_last_hour," +
        "  (SELECT count(*)" +
        "   FROM tweets" +
        "     JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "     JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "     JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "     JOIN users ON users.id = users_tweet_classifications.user_id" +
        "     JOIN classification_types ON classification_id = classification_types.id" +
        "   WHERE classification_value = 'non-rumour' AND" +
        "         tweets.created_on >= DATE_SUB(NOW(), INTERVAL 1 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_non_rumours_in_last_hour," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 2 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 1 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_rumours_over_an_hour," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'non-rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 2 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 1 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_non_rumours_over_an_hour," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 3 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 2 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_rumours_over_two_hours," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'non-rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 3 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 2 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_non_rumours_over_two_hours," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 4 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 3 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_rumours_over_three_hours," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'non-rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 4 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 3 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_non_rumours_over_three_hours," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 5 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 4 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_rumours_over_four_hours," +
        "  (SELECT count(*)" +
        "     FROM tweets" +
        "       JOIN hashtag_tweet_classifications ON hashtag_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN hashtags ON hashtags.id = hashtag_tweet_classifications.hashtag_id" +
        "       JOIN users_tweet_classifications ON users_tweet_classifications.tweet_id = tweets.id" +
        "       JOIN users ON users.id = users_tweet_classifications.user_id" +
        "       JOIN classification_types ON classification_id = classification_types.id" +
        "     WHERE classification_value = 'non-rumour' AND" +
        "           tweets.created_on >= DATE_SUB(NOW(), INTERVAL 5 HOUR) AND" +
        "           tweets.created_on <= DATE_SUB(NOW(), INTERVAL 4 HOUR) AND hashtags.hashtag_value = ? OR users.username = ?)     count_of_non_rumours_over_four_hours;";
  }
}
