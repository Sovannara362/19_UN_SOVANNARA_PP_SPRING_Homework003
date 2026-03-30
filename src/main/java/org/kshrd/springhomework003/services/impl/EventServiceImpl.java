package org.kshrd.springhomework003.services.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.exception.NotFoundExceptionHandler;
import org.kshrd.springhomework003.model.Event;
import org.kshrd.springhomework003.model.request.EventRequest;
import org.kshrd.springhomework003.model.request.VenueRequest;
import org.kshrd.springhomework003.repository.AttendeeRepository;
import org.kshrd.springhomework003.repository.EventAttendeeRepository;
import org.kshrd.springhomework003.repository.EventRepository;
import org.kshrd.springhomework003.repository.VenueRepository;
import org.kshrd.springhomework003.services.AttendeeService;
import org.kshrd.springhomework003.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final VenueRepository venueRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        return eventRepository.findAllEvents(page, size);
    }

    @Override
    public Event getEventById(Integer eventId) {
        Event event = eventRepository.findEventById(eventId);
        if (event == null) {
            throw new NotFoundExceptionHandler("Event with ID " + eventId + " not found");
        }
        return event;
    }

    @Override
    public Event createNewEvent(EventRequest eventRequest) {
        if (venueRepository.findVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + eventRequest.getVenueId() + " not found");
        }

        Event event = eventRepository.saveEvent(eventRequest);

        for (Integer attendeeId : eventRequest.getAttendeeIds()) {
            eventAttendeeRepository.saveAttendeeId(event.getEventId(), attendeeId);
        }
        return eventRepository.findEventById(event.getEventId());
    }

    @Override
    public Event updateEvent(Integer eventId, EventRequest eventRequest) {
        Event event = eventRepository.updateEvent(eventId, eventRequest);
        if (event == null) {
            throw new NotFoundExceptionHandler("Event with ID " + eventId + " not found");
        }

        if (venueRepository.findVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundExceptionHandler("Venue with ID " + eventRequest.getVenueId() + " not found");
        }

        if (eventRequest.getAttendeeIds().size() > event.getAttendees().size()) {
            for (Integer attendeeId : eventRequest.getAttendeeIds()) {
                eventAttendeeRepository.saveAttendeeId(event.getEventId(), attendeeId);
            }
        } else {
            for (int i = 0; i < event.getAttendees().size(); i++) {

                eventAttendeeRepository.updateAttendeeId(
                        event.getEventId(),
                        event.getAttendees().get(i).getAttendeeId(),
                        eventRequest.getAttendeeIds().get(i)
                );
            }
        }

        return eventRepository.findEventById(event.getEventId());
    }

    @Override
    public Event deleteEvent(Integer eventId) {
        Event event = eventRepository.deleteEvent(eventId);
        if (event == null) {
            throw new NotFoundExceptionHandler("Event with ID " + eventId + " not found");
        }
        return event;
    }
}
