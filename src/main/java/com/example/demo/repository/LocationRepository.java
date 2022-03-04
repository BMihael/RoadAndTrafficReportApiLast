package com.example.demo.repository;

import com.example.demo.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select * from Location l WHERE (l.latitude BETWEEN ?2 AND ?1 ) AND (l.longitude BETWEEN ?3 AND ?4)", nativeQuery = true)
    List<Location> findLocationsByBounds(Double to_latitude, Double from_latitude, Double from_longitude, Double to_longitude);
}
