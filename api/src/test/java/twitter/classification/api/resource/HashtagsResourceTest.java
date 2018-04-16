package twitter.classification.api.resource;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.util.JSONPObject;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import twitter.classification.common.models.ClassificationValueForTweets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class HashtagsResourceTest {

  private HashtagsResource hashtagsResource;

  @Before
  public void setup() {

    hashtagsResource = mock(HashtagsResource.class);
  }

  @Test
  public void whenAPICallIsMade_responseWillBeJSON() throws JsonProcessingException {

    List<ClassificationValueForTweets> classificationValueForTweetsList = new ArrayList<>();
    ClassificationValueForTweets classificationValueForTweets = new ClassificationValueForTweets();
    classificationValueForTweets.setClassificationValue("rumour");
    classificationValueForTweets.setTweetText("Tweet test");
    classificationValueForTweets.setId(100);
    classificationValueForTweetsList.add(classificationValueForTweets);

    doReturn(classificationValueForTweetsList).when(hashtagsResource).getPaginatedResults("hashtag", 0, 10);

    List<ClassificationValueForTweets> response = hashtagsResource.getPaginatedResults("hashtag", 0, 10);

    JsonArray jsonObject = new JsonParser().parse(new ObjectMapper().writeValueAsString(response)).getAsJsonArray();

    assertTrue(jsonObject.isJsonArray());
    assertEquals(classificationValueForTweets.getTweetText(), jsonObject.get(0).getAsJsonObject().get("tweetText").getAsString());
    assertEquals(classificationValueForTweets.getClassificationValue(), jsonObject.get(0).getAsJsonObject().get("classificationValue").getAsString());
  }
}