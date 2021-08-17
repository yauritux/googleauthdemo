package link.yauritux.infrastructure.adapter.input;

import link.yauritux.model.request.QRCodeRequest;
import link.yauritux.model.response.QRCodeResponse;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class PostResourceClient {

    private Client client;
    private ExecutorService executorService =
            Executors.newCachedThreadPool();

    @ConfigProperty(name = "api.key")
    private String apiKey;

    @ConfigProperty(name = "qr.url")
    private String qrUrl;

    @Inject
    Logger logger;

    @Inject
    public PostResourceClient() {
        client = ClientBuilder.newBuilder()
                .executorService(executorService)
                .build();
    }

    public QRCodeResponse getQRCode(QRCodeRequest request) throws Exception {
        WebTarget target =  client.target(qrUrl);
        try (var response = target.request()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("X-Authy-API-Key", apiKey)
                .post(Entity.json(request))) {
            return response.readEntity(QRCodeResponse.class);
        }
    }
}
