package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * GetBookingsResponseData
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookingsResponseData {

    @JsonProperty("bookings")
//    @Schema(description = "")
    @Valid
    private List<Booking> bookings;

}
