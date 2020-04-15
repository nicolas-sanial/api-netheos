package sanial.netheos.demoapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorPayload {

    @JsonProperty
    String message;

    public ErrorPayload(String msg){
        this.message = msg;
    }
}
