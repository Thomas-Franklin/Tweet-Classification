package twitter.classification.web.resource;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Path("/")
public class HomePageResource {

  private static final Logger logger = LoggerFactory.getLogger(HomePageResource.class);

  public HomePageResource() {

  }

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String homePage() {

    Client client = ClientBuilder.newClient();

    WebTarget target = client.target("http://api:8080/");

    Response response = client.target(target.getUri())
        .request()
        .get();

    logger.info("Info from API {}", response.readEntity(String.class));

    return "Hi";
  }
}
