package iudx.file.server.common;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebClientFactory {

  private static final Logger LOGGER = LogManager.getLogger(WebClientFactory.class);

  private final Vertx vertx;

  public WebClientFactory(final Vertx vertx) {
    this.vertx = vertx;
  }

  public WebClient getWebClientFor(final ServerType serverType) {
    if (serverType.equals(ServerType.FILE_SERVER)) {
      return getFileServerWebClient(vertx);
    } else if (serverType.equals(ServerType.RESOURCE_SERVER)) {
      return getRsServerWebClient(vertx);
    } else {
      LOGGER.error("Unknown type passed." + serverType);
      return null;
    }
  }

  private WebClient getFileServerWebClient(final Vertx vertx) {
    WebClientOptions options =
        new WebClientOptions()
            .setTrustAll(true)
            .setVerifyHost(false)
            .setSsl(true);
    return WebClient.create(vertx, options);
  }

  private WebClient getRsServerWebClient(final Vertx vertx) {
    WebClientOptions options =
        new WebClientOptions()
            .setTrustAll(true)
            .setVerifyHost(false)
            .setSsl(true);
    return WebClient.create(vertx, options);
  }
}
