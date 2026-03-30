package org.kshrd.springhomework003.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.springhomework003.model.Venue;
import org.kshrd.springhomework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id = "venuesMapper",value = {
            @Result(property = "venueId",column = "venue_id"),
            @Result(property = "venueName",column = "venue_name")
    })
    @Select("""
            SELECT * FROM venues
            LIMIT #{size}
            OFFSET (#{page} - 1) * #{size}
            """)
    List<Venue> findAllVenues(Integer page,Integer size);
    @ResultMap("venuesMapper")
    @Select("""
            SELECT * FROM venues
            WHERE venue_id = #{venueId}
            """)
    Venue findVenueById(Integer venueId);
    @ResultMap("venuesMapper")
    @Select("""
            INSERT INTO venues(venue_name,location)
            VALUES(#{req.venueName},#{req.location})
            RETURNING *
            """)
    Venue saveVenue(@Param("req") VenueRequest venueRequest);

    @ResultMap("venuesMapper")
    @Select("""
            UPDATE venues
            SET venue_name = #{req.venueName},
            location = #{req.location}
            WHERE venue_id = #{venueId}
            RETURNING *
            """)
    Venue updateVenue(Integer venueId,@Param("req") VenueRequest venueRequest);

    @ResultMap("venuesMapper")
    @Select("""
            DELETE FROM venues
            WHERE venue_id = #{venueId}
            RETURNING *
            """)
    Venue deleteVenue(Integer venueId);
}
