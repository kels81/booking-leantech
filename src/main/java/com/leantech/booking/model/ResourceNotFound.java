package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResourceNotFound
 */
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFound {

    @JsonProperty("status")
//    @Schema(example = "404", description = "resource not found")
    private int status;

    @JsonProperty("error")
//    @Schema(description = "")
    private ResourceNotFoundError error;

}
