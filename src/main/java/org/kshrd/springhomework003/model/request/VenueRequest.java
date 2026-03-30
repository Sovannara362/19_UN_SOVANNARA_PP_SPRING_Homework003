package org.kshrd.springhomework003.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueRequest {
    @NotBlank(message = "Venue Name cannot be blank")
    @NotNull(message = "Venue Name cannot be null")
    private String venueName;
    @NotBlank(message = "Location cannot be blank")
    @NotNull(message = "Location cannot be null")
    private String location;
}
