package com.hackerrank.github.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hackerrank.github.model.Actor;


public class ActorDTO {
	private Long id;
	private String login;
	@JsonProperty(value = "avatar_url")
	private String avatar;

	public static ActorDTO convertFrom(Actor actor) {
		return new ActorDTO(actor.getId(), actor.getLogin(), actor.getAvatar());
	}

	public ActorDTO(Long id, String login, String avatar) {
		super();
		this.id = id;
		this.login = login;
		this.avatar = avatar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	

}
