package link.yauritux.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QRCodeResponse {

    private String label;
    @JsonProperty("Issuer")
    private String issuer;
    @JsonProperty("qr_code")
    private String qrCode;
    private boolean success;
}
