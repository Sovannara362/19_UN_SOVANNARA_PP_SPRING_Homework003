package org.kshrd.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.model.Event;
import org.kshrd.springhomework003.model.request.EventRequest;
import org.kshrd.springhomework003.model.response.ApiResponse;
import org.kshrd.springhomework003.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(
            @Positive(message = "page cannot less than 1")
            @RequestParam(defaultValue = "1") Integer page,
            @Positive(message = "size cannot less than 1")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Event>>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved events successfully")
                        .status(HttpStatus.OK.name())
                        .payload(eventService.getAllEvents(page, size))
                        .build()
        );
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> getEventById(
            @Positive(message = "Event id cannot less than 1")
            @PathVariable Integer eventId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved event with id" + eventId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(eventService.getEventById(eventId))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Event>> createNewEvent(
            @RequestBody @Valid EventRequest eventRequest
    ) {
        Event event = eventService.createNewEvent(eventRequest);
        if (event != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.<Event>builder()
                            .timestamp(Instant.now())
                            .message("Created event successfully")
                            .status(HttpStatus.CREATED.name())
                            .payload(event)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Event with not found")
                        .status(HttpStatus.NOT_FOUND.name())
                        .payload(null)
                        .build()
        );
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> updateEvent(
            @Positive(message = "Event cannot less than 1")
            @PathVariable Integer eventId,
            @RequestBody @Valid EventRequest eventRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Updated event with id " + eventId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(eventService.updateEvent(eventId, eventRequest))
                        .build()
        );
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<ApiResponse<Event>> deleteEventById(
            @Positive(message = "EventId cannot less than 1")
            @PathVariable Integer eventId
    ) {

        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Event>builder()
                        .timestamp(Instant.now())
                        .message("Deleted event with id " + eventId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(null)
                        .build()
        );

    }
}
