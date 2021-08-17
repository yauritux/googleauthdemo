package link.yauritux.infrastructure.adapter.input;

import link.yauritux.model.response.TokenValidationResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class GetResourceClient {

    private Client client;
    private ExecutorService executorService =
            Executors.newCachedThreadPool();

    @ConfigProperty(name = "api.key")
    private String apiKey;

    @ConfigProperty(name = "validator.url")
    private String validatorUrl;

    @ConfigProperty(name = "userid")
    private String userId;

    @Inject
    Logger logger;

    @Inject
    public GetResourceClient() {
        client = ClientBuilder.newBuilder()
                .executorService(executorService)
                .build();
    }

    public TokenValidationResponse validate(String token) throws Exception {
        WebTarget target =  client.target(validatorUrl + "/" + token + "/" + userId);
        try (var response = target.request()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("X-Authy-API-Key", apiKey)
                .get()) {
            return response.readEntity(TokenValidationResponse.class);
        }
    }
}
