package org.kshrd.springhomework003.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendeeRequest {
    @NotBlank(message = "Attendee Name cannot be blank")
    @NotNull(message = "Attendee Name cannot be null")
    private String attendeeName;
    @NotBlank(message = "Email cannot be blank")
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email invalid form")
    private String email;
}
