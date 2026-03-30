package org.kshrd.springhomework003.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotNull(message = "Event name cannot be null")
    @NotBlank(message = "Event name cannot be blank")
    private String eventName;
    @Future(message = "Event date must be in the future")
    private LocalDateTime eventDate;
    @Positive(message = "Venue ID must be positive number")
    private Integer venueId;

    private List<@Positive(message = "Attendee ID must be positive number") Integer> attendeeIds;
}
