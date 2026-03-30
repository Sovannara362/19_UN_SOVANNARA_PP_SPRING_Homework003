package org.kshrd.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.model.Attendee;
import org.kshrd.springhomework003.model.request.AttendeeRequest;
import org.kshrd.springhomework003.model.response.ApiResponse;
import org.kshrd.springhomework003.services.AttendeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @Positive(message = "page cannot less than 1")
            @RequestParam(defaultValue = "1") Integer page,
            @Positive(message = "size cannot less than 1")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Attendee>>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved attendees successfully")
                        .status(HttpStatus.OK.name())
                        .payload(attendeeService.getAllAttendees(page, size))
                        .build()
        );
    }

    @GetMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(
            @Positive(message = "Attendee ID cannot less than 1")
            @PathVariable Integer attendeeId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved attendee with id" + attendeeId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(attendeeService.getAttendeeById(attendeeId))
                        .build()
        );

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendee>> createNewAttendee(
            @RequestBody @Valid AttendeeRequest attendeeRequest
    ) {
        Attendee attendee = attendeeService.createNewAttendee(attendeeRequest);
        if (attendee != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.<Attendee>builder()
                            .timestamp(Instant.now())
                            .message("Created attendee successfully")
                            .status(HttpStatus.CREATED.name())
                            .payload(attendee)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Attendee with not found")
                        .status(HttpStatus.NOT_FOUND.name())
                        .payload(null)
                        .build()
        );
    }

    @PutMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> updateAttendee(
            @Positive(message = "Attendee ID cannot less than 1")
            @PathVariable Integer attendeeId,
            @RequestBody @Valid AttendeeRequest attendeeRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Updated attendee with id" + attendeeId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(attendeeService.updateAttendee(attendeeId, attendeeRequest))
                        .build()
        );
    }

    @DeleteMapping("/{attendeeId}")
    public ResponseEntity<ApiResponse<Attendee>> deleteAttendeeById(
            @Positive(message = "Attendee ID cannot less than 1")
            @PathVariable Integer attendeeId
    ) {
        attendeeService.deleteAttendee(attendeeId);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Attendee>builder()
                        .timestamp(Instant.now())
                        .message("Deleted attendee with id " + attendeeId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(null)
                        .build()
        );
    }
}
