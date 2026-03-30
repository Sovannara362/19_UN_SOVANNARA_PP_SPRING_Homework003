package org.kshrd.springhomework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.model.Venue;
import org.kshrd.springhomework003.model.request.VenueRequest;
import org.kshrd.springhomework003.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.kshrd.springhomework003.services.VenueService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
@RequiredArgsConstructor
public class VenueController {
    private final VenueService venueService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @Positive(message = "page cannot less than 1")
            @RequestParam(defaultValue = "1") Integer page,
            @Positive(message = "size cannot less than 1")
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<List<Venue>>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved venues successfully")
                        .status(HttpStatus.OK.name())
                        .payload(venueService.getAllVenues(page, size))
                        .build()
        );
    }

    @GetMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(
            @Positive(message = "Venue ID cannot less than 1")
            @PathVariable Integer venueId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venue>builder()
                        .timestamp(Instant.now())
                        .message("Retrieved venue with id " + venueId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(venueService.getVenueById(venueId))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createNewVenue(
            @RequestBody @Valid VenueRequest venueRequest
    ) {
        Venue venue = venueService.createNewVenue(venueRequest);
        if (venue != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponse.<Venue>builder()
                            .timestamp(Instant.now())
                            .message("Created venue successfully")
                            .status(HttpStatus.CREATED.name())
                            .payload(venue)
                            .build()
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Venue>builder()
                        .timestamp(Instant.now())
                        .message("Venue with not found")
                        .status(HttpStatus.NOT_FOUND.name())
                        .payload(null)
                        .build()
        );
    }

    @PutMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(
            @Positive(message = "Venue ID cannot less than 1")
            @PathVariable Integer venueId,
            @RequestBody @Valid VenueRequest venueRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venue>builder()
                        .timestamp(Instant.now())
                        .message("Updated venue with id" + venueId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(venueService.updateVenue(venueId, venueRequest))
                        .build()
        );

    }

    @DeleteMapping("/{venueId}")
    public ResponseEntity<ApiResponse<Venue>> deleteVenueById(
            @Positive(message = "Venue ID cannot less than 1")
            @PathVariable Integer venueId
    ) {

        venueService.deleteVenue(venueId);
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<Venue>builder()
                        .timestamp(Instant.now())
                        .message("Deleted venue with id " + venueId + " successfully")
                        .status(HttpStatus.OK.name())
                        .payload(null)
                        .build()
        );
    }
}
