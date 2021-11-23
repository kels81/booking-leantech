package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GetBookingIdResponse
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingIdResponse {

    @JsonProperty("status")
//    @Schema(example = "200", description = "status code ok")
    private int status;

    @JsonProperty("data")
//    @Schema(description = "")
    private GetBookingIdResponseData data;

}
