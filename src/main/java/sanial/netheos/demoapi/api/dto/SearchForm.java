package sanial.netheos.demoapi.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class SearchForm {

    @JsonProperty("TOCOMPARE")
    @NotNull(message = "The value that you search is mandatory here.")
    String toCompare;

    public String getToCompare() {
        return toCompare;
    }

    public void setToCompare(String toCompare) {
        this.toCompare = toCompare;
    }
}
