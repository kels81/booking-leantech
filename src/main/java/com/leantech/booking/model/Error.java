package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Error
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    @JsonProperty("code")
//    @Schema(example = "400.03.0", description = "specific error code encountered")
    private int code;

    @JsonProperty("target")
//    @Schema(example = "titularReserva", description = "request field/param error is associated with")
    private String target;

    @JsonProperty("message")
//    @Schema(example = "titularReserva must be passed as string", description = "information to help correct invalid request")
    private String message;

}
