package org.kshrd.springhomework003.services;

import org.kshrd.springhomework003.model.Event;
import org.kshrd.springhomework003.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);

    Event getEventById(Integer eventId);

    Event createNewEvent(EventRequest eventRequest);

    Event updateEvent(Integer eventId, EventRequest eventRequest);

    Event deleteEvent(Integer eventId);
}
