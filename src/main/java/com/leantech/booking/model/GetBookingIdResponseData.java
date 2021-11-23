package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

/**
 * GetBookingIdResponseData
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingIdResponseData {

    @JsonProperty("booking")
//    @Schema(description = "")
    private Booking booking = null;

}
