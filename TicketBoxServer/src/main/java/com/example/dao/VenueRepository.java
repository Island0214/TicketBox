package com.example.dao;

import com.example.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by island on 2018/3/16.
 */
@Repository
public interface VenueRepository extends JpaSpecificationExecutor<Venue>, JpaRepository<Venue, Long> {
    Venue findByCodeAndPassword(int code, String password);

    Venue findByName(String name);
}
