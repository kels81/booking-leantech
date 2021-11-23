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
 * error block for all validations failing on request
 */
//@Schema(description = "error block for all validations failing on request")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestError {

    @JsonProperty("code")
//    @Schema(example = "400.10.0", description = "one or more validations that are failing")
    private int code;

    @JsonProperty("message")
//    @Schema(example = "there were validation errors in your request", description = "message to show what type of error was encountered")
    private String message;

    @JsonProperty("details")
//    @Schema(description = "array of errors that we encountered when parsing the request")
    @Valid
    private List<Error> details;

}
