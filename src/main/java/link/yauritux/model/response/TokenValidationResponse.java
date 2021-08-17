package link.yauritux.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class TokenValidationResponse {

    private String message;
    private String token;
    private boolean success;
    private Device device;
    private Error errors;
    @JsonProperty("error_code")
    private String errorCode;

    @Getter
    @Setter
    static class Device {
        private String id;
        @JsonProperty("os_type")
        private String osType;
        @JsonProperty("registration_date")
        private long registrationDate;
        @JsonProperty("registration_method")
        private String registrationMethod;
        @JsonProperty("registration_country")
        private String registrationCountry;
        @JsonProperty("registration_region")
        private String registrationRegion;
        @JsonProperty("registration_city")
        private String registrationCity;
        private String country;
        private String region;
        private String city;
        private String ip;
        @JsonProperty("last_account_recovery_at")
        private long lastAccountRecoveryAt;
        @JsonProperty("last_sync_date")
        private long lastSyncDate;
    }

    @Getter
    @Setter
    static class Error {
        private String message;
    }
}
