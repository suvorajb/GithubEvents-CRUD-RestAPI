package com.hackerrank.github.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hackerrank.github.model.Event;


public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("SELECT e FROM Event e WHERE e.actor.id=?1 ORDER BY e.id")
	public List<Event> findAllEventsByActorid(Long actorid);
}
