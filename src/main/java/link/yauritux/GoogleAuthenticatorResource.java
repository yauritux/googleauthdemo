package link.yauritux;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import link.yauritux.infrastructure.adapter.input.GetResourceClient;
import link.yauritux.infrastructure.adapter.input.PostResourceClient;
import link.yauritux.model.request.QRCodeRequest;
import link.yauritux.model.response.QRCodeResponse;
import link.yauritux.model.response.TokenValidationResponse;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/googleauth")
public class GoogleAuthenticatorResource {

    @Inject
    Template qr;

    @Inject
    PostResourceClient postResourceClient;

    @Inject
    GetResourceClient getResourceClient;

    @Inject
    Logger logger;

    @GET
    @Path("/generate/{label}/{qrsize}")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance fetchQRCode(
            @PathParam("label") String label,
            @PathParam("qrsize") String qrSize) {
        var request = QRCodeRequest.builder()
                .label(label)
                .qrSize(qrSize)
                .build();
        QRCodeResponse response = null;
        try {
            response = postResourceClient.getQRCode(request);
        } catch (Exception e) {
            logger.error("An exception occurred.Message = " + e.getMessage());
        }

        return qr.data("resp", response);
    }

    @GET
    @Path("/validate/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public TokenValidationResponse validateToken(@PathParam("token") String token) {
        TokenValidationResponse response = null;
        try {
            response = getResourceClient.validate(token);
        } catch (Exception e) {
            logger.error("An exception occurred.Message = " + e.getMessage());
        }

        return response;
    }
}