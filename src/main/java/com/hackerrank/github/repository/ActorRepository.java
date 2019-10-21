package com.hackerrank.github.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hackerrank.github.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {

	@Query(value = "select e.actor_id as id, a.login as login, a.avatar as avatar, count(e.actor_id) as eventcount, "
			+ "max(e.created_at) as createdAt from event e join actor a on e.actor_id=a.id group by e.actor_id "
			+ "order by eventcount desc , max(e.created_at) DESC", nativeQuery = true)
	public List<Actor> findAllEventsByActor();

}
