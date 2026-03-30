package org.kshrd.springhomework003.services;

import org.kshrd.springhomework003.model.Attendee;
import org.kshrd.springhomework003.model.request.AttendeeRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees(Integer page, Integer size);

    Attendee getAttendeeById(Integer attendeeId);

    Attendee createNewAttendee(AttendeeRequest attendeeRequest);

    Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest);

    Attendee deleteAttendee(Integer attendeeId);
}
