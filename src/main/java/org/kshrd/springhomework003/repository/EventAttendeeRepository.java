package org.kshrd.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.springhomework003.model.Attendee;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {
    @Results(id = "event-attendeeMapper",value = {
            @Result(property = "eventId",column = "event_id"),
            @Result(property = "attendeeId",column = "attendee_id"),
            @Result(property = "attendeeName",column = "attendee_name")
    })
    @Select("""
            SELECT a.* FROM attendees a
            INNER JOIN event_attendee ea
            ON a.attendee_id = ea.attendee_id
            WHERE ea.event_id = #{eventId}
            """)
    List<Attendee> getAllAttendeesByEventId(Integer eventId);
    @Insert("""
            INSERT INTO event_attendee(event_id,attendee_id)
            VALUES(#{eventId},#{attendeeId})
            """)
    void saveAttendeeId(Integer eventId, Integer attendeeId);
    @Update("""
            UPDATE event_attendee
            SET event_id = #{eventId},attendee_id = #{newAttendeeId}
            WHERE event_id = #{eventId} AND attendee_id = #{oldAttendeeId}
            """)
    void updateAttendeeId(Integer eventId, Integer oldAttendeeId, Integer newAttendeeId);
}
