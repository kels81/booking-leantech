package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * BadRequest
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequest {

    @JsonProperty("status")
//    @Schema(example = "400", description = "bad request")
    private int status;

    @JsonProperty("error")
//    @Schema(description = "")
    private BadRequestError error;

}
