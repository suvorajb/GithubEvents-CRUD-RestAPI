package com.hackerrank.github.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackerrank.github.model.Event;

public class EventDTO {
    private Long id;
    private String type;
    private ActorDTO actor;
    private RepoDTO repo;
    @JsonProperty(value = "created_at")
    private String createdAt;

    public static EventDTO convertFrom(Event event) {
        return new EventDTO(
                event.getId(),
                event.getType(),
                ActorDTO.convertFrom(event.getActor()),
                RepoDTO.convertFrom(event.getRepo()),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format(
                                new Date(
                                        event.getCreatedAt().getTime()
                                )
                        )
        );
    }

	public EventDTO(Long id, String type, ActorDTO actor, RepoDTO repo, String createdAt) {
		super();
		this.id = id;
		this.type = type;
		this.actor = actor;
		this.repo = repo;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ActorDTO getActor() {
		return actor;
	}

	public void setActor(ActorDTO actor) {
		this.actor = actor;
	}

	public RepoDTO getRepo() {
		return repo;
	}

	public void setRepo(RepoDTO repo) {
		this.repo = repo;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
    
    
}
