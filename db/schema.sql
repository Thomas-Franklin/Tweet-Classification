
CREATE TABLE IF NOT EXISTS tweet_classification
(
  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  tweet_id BIGINT,
  hashtag_term VARCHAR(30),
  classification VARCHAR(10),
  tweet_text VARCHAR(300),
  created_on TIMESTAMP NOT NULL DEFAULT now(),
  updated_on TIMESTAMP NOT NULL DEFAULT now() ON UPDATE now()
) CHARACTER SET utf8;