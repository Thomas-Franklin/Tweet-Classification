package twitter.classification.api.service;

import java.util.List;

import twitter.classification.api.persist.jdbc.models.PaginatedTweetsModel;
import twitter.classification.common.models.ClassificationValueForTweets;

public class PaginatedResultsService {

  public List<ClassificationValueForTweets> paginatedResults(List<ClassificationValueForTweets> classificationValueForTweetsList, List<PaginatedTweetsModel> paginatedTweetsModels) {

    for (PaginatedTweetsModel paginatedTweetsModel : paginatedTweetsModels) {

      ClassificationValueForTweets classificationValueForTweets = new ClassificationValueForTweets();

      classificationValueForTweets.setId(paginatedTweetsModel.getId().intValue());
      classificationValueForTweets.setTweetText(paginatedTweetsModel.getTweetText());
      classificationValueForTweets.setClassificationValue(paginatedTweetsModel.getClassificationValue());

      classificationValueForTweetsList.add(classificationValueForTweets);
    }

    return classificationValueForTweetsList;
  }
}