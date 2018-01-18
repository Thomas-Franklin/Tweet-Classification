package twitter.classification.api.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/")
public class TweetClassificationResource {

  public TweetClassificationResource() {

  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getAllTweets() {

    return "{hello: hello}";
  }
}
