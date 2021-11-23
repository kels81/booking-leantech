package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * GetBookingsResponse
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsResponse {

    @JsonProperty("status")
//    @Schema(example = "200", description = "status code ok")
    private int status;

    @JsonProperty("data")
//    @Schema(description = "")
    private GetBookingsResponseData data;

}
