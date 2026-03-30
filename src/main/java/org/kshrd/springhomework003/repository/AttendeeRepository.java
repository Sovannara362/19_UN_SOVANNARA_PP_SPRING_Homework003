package org.kshrd.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.springhomework003.model.Attendee;
import org.kshrd.springhomework003.model.request.AttendeeRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id = "attendeesMapper",value = {
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    @Select("""
            SELECT * FROM attendees
            LIMIT #{size}
            OFFSET (#{page}-1) * #{size}
            """)
    List<Attendee> findAllAttendees(Integer page, Integer size);

    @ResultMap("attendeesMapper")
    @Select("""
            SELECT * FROM attendees
            WHERE attendee_id = #{attendeeId}
            """)
    Attendee findAttendeeById(Integer attendeeId);

    @ResultMap("attendeesMapper")
    @Select("""
            INSERT INTO attendees(attendee_name,email)
            VALUES(#{req.attendeeName},#{req.email})
            RETURNING *
            """)
    Attendee saveAttendee(@Param("req") AttendeeRequest attendeeRequest);

    @ResultMap("attendeesMapper")
    @Select("""
            UPDATE attendees
            SET attendee_name = #{req.attendeeName},
            email = #{req.email}
            WHERE attendee_id = #{attendeeId}
            RETURNING *
            """)
    Attendee updateAttendee(Integer attendeeId,@Param("req") AttendeeRequest attendeeRequest);

    @ResultMap("attendeesMapper")
    @Select("""
            DELETE FROM attendees
            WHERE attendee_id = #{attendeeId}
            RETURNING *
            """)
    Attendee deleteAttendee(Integer attendeeId);
}
