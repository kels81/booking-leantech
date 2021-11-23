package com.leantech.booking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * resource not found
 */
//@Schema(description = "resource not found")
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundError   {
  @JsonProperty("code")
//  @Schema(description = "resource not found")
  private int code;

  @JsonProperty("message")
//  @Schema(description = "human readable error message")
  private String message;

  }
