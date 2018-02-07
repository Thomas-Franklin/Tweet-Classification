package twitter.classification.preprocessor.resource;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter.classification.common.tweetdetails.PreProcessingTweetDetails;
import twitter4j.Status;

@Singleton
@Path("/")
public class ReceiveStatusesResource {

  private static final Logger logger = LoggerFactory.getLogger(ReceiveStatusesResource.class);

  public ReceiveStatusesResource() {

  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public String receiveStatus(String status) {

    logger.info("Tweet body is {}", status);


    return "{hello: hello}";
  }
}
