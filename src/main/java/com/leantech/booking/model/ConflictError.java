package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

/**
 * The resource that is references is in conflict with what already exists
 */
//@Schema(description = "The resource that is references is in conflict with what already exists")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConflictError   {
  @JsonProperty("code")
//  @Schema(example = "409.0.0", description = "error code associated to conflict")
  private String code;

  @JsonProperty("message")
//  @Schema(example = "booking id already exists", description = "human readable error message")
  private String message;

  }
