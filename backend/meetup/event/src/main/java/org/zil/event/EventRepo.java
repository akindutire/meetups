package org.zil.event;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepo extends JpaRepository<Event, Integer> {
    Optional<Event> findByTitleAndOwnership(String title, Integer ownership);

    @Query(value = "SELECT e.id FROM Event e WHERE e.ownership= ?1 AND e.startAt<= ?2 and e.endAt>= ?2")
    List<Integer> findByOwnershipAndDateInBetweenDates(Integer ownership, Date start);
}
