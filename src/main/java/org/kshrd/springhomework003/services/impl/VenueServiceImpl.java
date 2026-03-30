package org.kshrd.springhomework003.services.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.exception.NotFoundExceptionHandler;
import org.kshrd.springhomework003.model.Venue;
import org.kshrd.springhomework003.model.request.VenueRequest;
import org.springframework.stereotype.Service;
import org.kshrd.springhomework003.repository.VenueRepository;
import org.kshrd.springhomework003.services.VenueService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;
    @Override
    public List<Venue> getAllVenues(Integer page,Integer size) {
        return venueRepository.findAllVenues(page,size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        Venue venue = venueRepository.findVenueById(venueId);
        if(venue == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + venueId + " not found");
        }
        return venue;
    }

    @Override
    public Venue createNewVenue(VenueRequest venueRequest) {
        return venueRepository.saveVenue(venueRequest);
    }

    @Override
    public Venue updateVenue(Integer venueId, VenueRequest venueRequest) {
        Venue venue = venueRepository.updateVenue(venueId,venueRequest);
        if(venue == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + venueId + " not found");
        }
        return venue;
    }

    @Override
    public Venue deleteVenue(Integer venueId) {
        Venue venue = venueRepository.deleteVenue(venueId);
        if(venue == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + venueId + " not found");
        }
        return venue;
    }
}
