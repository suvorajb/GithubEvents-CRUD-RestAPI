package com.hackerrank.github.service;


import java.sql.Timestamp;

import com.hackerrank.github.model.Actor;

public class ActorContainer {
	
	private Actor actor;
	private Integer cEvents;
	private Timestamp last;
	
	public ActorContainer(Actor actor, int cEvents, Timestamp last) {
		super();
		this.actor = actor;
		this.cEvents = cEvents;
		this.last = last;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public int getcEvents() {
		return cEvents;
	}

	public void setcEvents(int cEvents) {
		this.cEvents = cEvents;
	}

	public Timestamp getLast() {
		return last;
	}

	public void setLast(Timestamp last) {
		this.last = last;
	}

	@Override
	public String toString() {
		return "ActorContainer [actor=" + actor + ", cEvents=" + cEvents + ", last=" + last + "]";
	}
	
	

}
