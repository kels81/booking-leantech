package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * Conflict
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conflict {

    @JsonProperty("status")
//    @Schema(example = "409", description = "conflict")
    private int status;

    @JsonProperty("error")
//    @Schema(description = "")
    private ConflictError error;

}
