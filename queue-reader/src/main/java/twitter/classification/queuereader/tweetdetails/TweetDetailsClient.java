package twitter.classification.queuereader.tweetdetails;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TweetDetailsClient {

  private static final Logger logger = LoggerFactory.getLogger(TweetDetailsClient.class);

  private Client client;
  private String uri;

  public TweetDetailsClient(ClientConfig clientConfig, String uri) {

    this.client = ClientBuilder.newClient(clientConfig);
    this.uri = uri;
  }

  public Response postStatusForProcessing(String status) {

    return post(status);
  }

  private Response post(String status) {


    WebTarget target = client.target(uri);

    return client.target(target.getUri())
        .request()
        .post(Entity.entity(status, MediaType.APPLICATION_JSON));
  }
}
