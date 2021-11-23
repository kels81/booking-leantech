package com.leantech.booking.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Edrd
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailTemplate {

    @Email
    private String sendTo;
    @Max(value = 100)
    private String subject;
    @Max(value = 500)
    private String body;

}
