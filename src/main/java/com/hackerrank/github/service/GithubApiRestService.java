package com.hackerrank.github.service;

import org.springframework.http.ResponseEntity;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

public interface GithubApiRestService {
	
	public ResponseEntity createEvent(Event event);
	
	public ResponseEntity getAllEvents();
	
	public ResponseEntity eraseAllEvents();
	
	public ResponseEntity getAllEventsByActor(Long actorid);
	
	public ResponseEntity updateActorAvatarUrl(Actor actor);
	
	public ResponseEntity getAllActorsByEventCount();
	
	public ResponseEntity getActorsStreak();
	
}
