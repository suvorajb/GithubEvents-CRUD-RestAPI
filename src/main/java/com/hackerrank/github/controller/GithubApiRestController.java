package com.hackerrank.github.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.dto.ActorDTO;
import com.hackerrank.github.service.GithubApiRestService;


@RestController
public class GithubApiRestController {

	@Autowired
	GithubApiRestService _service;

	// 1
	@DeleteMapping(path = "/erase", produces = "application/json")
	public ResponseEntity eraseAllEvents() {

		return _service.eraseAllEvents();
	}

	// 2
	@PostMapping(path = "/events", consumes = "application/json", produces = "application/json")
	public ResponseEntity addEvent(@RequestBody Event event) {
		System.out.println(event);

		return _service.createEvent(event);
	}

	// 3
	@GetMapping(path = "/events", produces = "application/json")
	public ResponseEntity getAllEvents() {
		return _service.getAllEvents();
	}

	// 4
	@GetMapping(path = "/events/actors/{actorid}", produces = "application/json")
	public ResponseEntity getAllEventsByActor(@PathVariable Long actorid) {

		return _service.getAllEventsByActor(actorid);
	}

	// 5
	@PutMapping(path = "/actors", consumes = "application/json", produces = "application/json")
	public ResponseEntity updtActorAvatarUrl(@RequestBody Actor actor) {

		return _service.updateActorAvatarUrl(actor);
	}

	// 6
	@GetMapping(path = "/actors", produces = "application/json")
	public ResponseEntity getActorsByTotalEvents() {

		return _service.getAllActorsByEventCount();
	}

	// 7
	@GetMapping(value = "/actors/streak", produces = "application/json")
	public ResponseEntity getActorsStreak() {

		return _service.getActorsStreak();

	}

}
