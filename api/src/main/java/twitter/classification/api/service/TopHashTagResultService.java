package twitter.classification.api.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import twitter.classification.api.persist.jdbc.SelectTopHashtagsClassificationCountDao;
import twitter.classification.api.persist.jdbc.TweetsForHashtagsDao;
import twitter.classification.api.persist.jdbc.models.HashTagsProcessedTweetsModel;
import twitter.classification.api.persist.jdbc.models.TopHashtagsClassificationModel;
import twitter.classification.api.wordclouds.WordCloudCreation;
import twitter.classification.common.models.HashtagResults;
import twitter.classification.common.models.TopHashtagsResponse;
import twitter.classification.common.persist.DbConnection;

public class TopHashTagResultService {

  private SelectTopHashtagsClassificationCountDao selectTopHashtagsClassificationCountDao;
  private TweetsForHashtagsDao tweetsForHashtagsDao;
  private WordCloudCreation wordCloudCreation;

  @Inject
  public TopHashTagResultService(
      SelectTopHashtagsClassificationCountDao selectTopHashtagsClassificationCountDao,
      TweetsForHashtagsDao tweetsForHashtagsDao,
      WordCloudCreation wordCloudCreation
  ) {


    this.selectTopHashtagsClassificationCountDao = selectTopHashtagsClassificationCountDao;
    this.tweetsForHashtagsDao = tweetsForHashtagsDao;
    this.wordCloudCreation = wordCloudCreation;
  }

  @DbConnection
  public TopHashtagsResponse get() throws IOException {

    List<TopHashtagsClassificationModel> topHashtagsClassificationModelList = selectTopHashtagsClassificationCountDao.select();
    TopHashtagsResponse topHashtagsResponse = new TopHashtagsResponse();


    for (TopHashtagsClassificationModel topHashtagsClassificationModel : topHashtagsClassificationModelList) {
      HashtagResults hashtagResults = new HashtagResults();

      hashtagResults.setHashtagValue(topHashtagsClassificationModel.getHashtagValue());
      hashtagResults.setCountOfNonRumours(topHashtagsClassificationModel.getCountOfNonRumours().intValue());
      hashtagResults.setCountOfRumours(topHashtagsClassificationModel.getCountOfRumours().intValue());
      hashtagResults.setTotalCountOfClassifications(topHashtagsClassificationModel.getTotalClassificationCount().intValue());

      List<HashTagsProcessedTweetsModel> hashTagsProcessedTweetsModelList = tweetsForHashtagsDao.get(topHashtagsClassificationModel.getHashtagValue());
      List<String> wordCloudList = new ArrayList<>();

      for (HashTagsProcessedTweetsModel hashTagsProcessedTweetsModel : hashTagsProcessedTweetsModelList) {
        wordCloudList.add(hashTagsProcessedTweetsModel.getOriginalTextList());
      }

      hashtagResults.setBase64WordCloudImage(wordCloudCreation.base64String(wordCloudList));

      topHashtagsResponse.addHashtagResult(hashtagResults);
    }

    return topHashtagsResponse;
  }
}
