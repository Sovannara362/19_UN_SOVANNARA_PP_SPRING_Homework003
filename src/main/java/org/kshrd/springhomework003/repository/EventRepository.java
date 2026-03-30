package org.kshrd.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.springhomework003.model.Event;
import org.kshrd.springhomework003.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventsMapper",value = {
            @Result(property = "eventId" ,column = "event_id"),
            @Result(property = "eventName",column = "event_name"),
            @Result(property = "eventDate",column = "event_date"),
            @Result(property = "venue",column = "venue_id",
            one = @One(select = "org.kshrd.springhomework003.repository.VenueRepository.findVenueById")
            ),
            @Result(property = "attendees",column = "event_id",
            many = @Many(select = "org.kshrd.springhomework003.repository.EventAttendeeRepository.getAllAttendeesByEventId")
            )
    })
    @Select("""
            SELECT * FROM events
            LIMIT #{size}
            OFFSET (#{page} - 1) * #{size}
            """)
    List<Event> findAllEvents(Integer page, Integer size);

    @ResultMap("eventsMapper")
    @Select("""
            SELECT * FROM events
            WHERE event_id = #{eventId}
            """)
    Event findEventById(Integer eventId);

    @ResultMap("eventsMapper")
    @Select("""
            INSERT INTO events (event_name,event_date,venue_id)
            VALUES(#{req.eventName},#{req.eventDate},#{req.venueId})
            RETURNING *
            """)
    Event saveEvent(@Param("req") EventRequest eventRequest);

    @ResultMap("eventsMapper")
    @Select("""
            UPDATE events
            SET event_name = #{req.eventName},
            event_date = #{req.eventDate},
            venue_id = #{req.venueId}
            WHERE event_id = #{eventId}
            RETURNING *
            """)
    Event updateEvent(Integer eventId,@Param("req") EventRequest eventRequest);

    @ResultMap("eventsMapper")
    @Select("""
            DELETE FROM events
            WHERE event_id = #{eventId}
            RETURNING *
            """)
    Event deleteEvent(Integer eventId);
}
