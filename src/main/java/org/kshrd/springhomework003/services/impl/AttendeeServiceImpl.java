package org.kshrd.springhomework003.services.impl;

import lombok.RequiredArgsConstructor;
import org.kshrd.springhomework003.exception.NotFoundExceptionHandler;
import org.kshrd.springhomework003.model.Attendee;
import org.kshrd.springhomework003.model.request.AttendeeRequest;
import org.kshrd.springhomework003.repository.AttendeeRepository;
import org.kshrd.springhomework003.services.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        return attendeeRepository.findAllAttendees(page,size);
    }

    @Override
    public Attendee getAttendeeById(Integer attendeeId) {
        Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
        if(attendee == null) {
            throw new NotFoundExceptionHandler("Attendee with ID " + attendeeId + " not found");
        }
        return attendee;
    }

    @Override
    public Attendee createNewAttendee(AttendeeRequest attendeeRequest) {
        return attendeeRepository.saveAttendee(attendeeRequest);
    }

    @Override
    public Attendee updateAttendee(Integer attendeeId, AttendeeRequest attendeeRequest) {
        Attendee attendee = attendeeRepository.updateAttendee(attendeeId,attendeeRequest);
        if(attendee == null) {
            throw new NotFoundExceptionHandler("Attendee with ID " + attendeeId + " not found");
        }
        return attendee;
    }

    @Override
    public Attendee deleteAttendee(Integer attendeeId) {
        Attendee attendee = attendeeRepository.deleteAttendee(attendeeId);
        if(attendee == null) {
            throw new NotFoundExceptionHandler("Attendee with ID " + attendeeId + " not found");
        }
        return attendee;
    }
}
