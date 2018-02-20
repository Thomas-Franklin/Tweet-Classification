CREATE TABLE IF NOT EXISTS classification_types
(
  id TINYINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  classification_value VARCHAR(11),
  classification_code VARCHAR(3),
  CONSTRAINT code_and_value_unique UNIQUE (classification_value, classification_code)
) CHARACTER SET utf8;

INSERT IGNORE INTO classification_types (classification_value, classification_code)
VALUES ('undefined', 'UDF'), ('rumour', 'RMR'), ('non-rumour', 'NOR');

CREATE TABLE IF NOT EXISTS tweets
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  tweet_id BIGINT,
  tweet_text VARCHAR(300),
  classification_id TINYINT,
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  FOREIGN KEY (classification_id)
    REFERENCES classification_types(id),
  UNIQUE (tweet_id)
) CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS users
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(30),
  twitter_id BIGINT,
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT twitter_id_and_username_unique UNIQUE (username, twitter_id)
) CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS users_tweet_classifications
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  user_id BIGINT,
  tweet_id BIGINT,
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT user_id_and_tweet_id_unique UNIQUE (user_id, tweet_id),
  FOREIGN KEY (user_id)
    REFERENCES users(id),
  FOREIGN KEY (tweet_id)
    REFERENCES tweets(id)
) CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS hashtags
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hashtag_value VARCHAR(30),
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  UNIQUE (hashtag_value)
) CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS hashtag_tweet_classifications
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  hashtag_id BIGINT,
  tweet_id BIGINT,
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  CONSTRAINT hashtag_id_and_tweet_id_unique UNIQUE (hashtag_id, tweet_id),
  FOREIGN KEY (hashtag_id)
    REFERENCES hashtags(id),
  FOREIGN KEY (tweet_id)
    REFERENCES tweets(id)
) CHARACTER SET utf8;