package org.kshrd.springhomework003.services;

import org.kshrd.springhomework003.model.Venue;
import org.kshrd.springhomework003.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenues(Integer page,Integer size);

    Venue getVenueById(Integer venueId);

    Venue createNewVenue(VenueRequest venueRequest);

    Venue updateVenue(Integer venueId, VenueRequest venueRequest);

    Venue deleteVenue(Integer venueId);
}
