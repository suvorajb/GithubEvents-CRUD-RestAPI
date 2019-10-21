package com.hackerrank.github.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.model.Repo;
import com.hackerrank.github.model.dto.ActorDTO;
import com.hackerrank.github.repository.ActorRepository;
import com.hackerrank.github.repository.EventRepository;
import com.hackerrank.github.repository.RepoRepository;

@Service
@Transactional
public class GithubApiRestServiceImpl implements GithubApiRestService {

	@Autowired
	EventRepository _evntRepo;

	@Autowired
	ActorRepository _actrRepo;

	@Autowired
	RepoRepository _repoRepo;

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity createEvent(Event event) {

		boolean errFlag = false;

		if (event != null) {
			try {
				// check if event with same id exists or not
				Event evntChk = _evntRepo.findOne(event.getId());

				if (evntChk!=null) {
					errFlag = true;
				} else {
					try {
						// save actor
						_actrRepo.save(event.getActor());

						// save repo
						_repoRepo.save(event.getRepo());
					} catch (Exception ex) {
						ex.printStackTrace();
						errFlag = true;
					}

					// now save event and return
					_evntRepo.save(event);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				errFlag = true;
			}

		} else {
			errFlag = true;
		}

		if (!errFlag) {
			return new ResponseEntity(HttpStatus.CREATED);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity getAllEvents() {
		List<Event> eventList = _evntRepo.findAll().stream().sorted(Comparator.comparingLong(Event::getId))
				.collect(Collectors.toList());
		System.out.println("eventList - " + eventList);
		return new ResponseEntity(eventList, HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity eraseAllEvents() {

		List<Event> eventList = _evntRepo.findAll();

		if (eventList != null && eventList.size() > 0) {
			/*for (Event event : eventList) {
				Actor actor = event.getActor();
				Repo repo = event.getRepo();

				if (actor != null) {
					_actrRepo.delete(actor);
				}

				if (repo != null) {
					_repoRepo.delete(repo);
				}
			}*/
			// now delete event
			_evntRepo.deleteAll();
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseEntity updateActorAvatarUrl(Actor actor) {

		int errFlag = 0;
		// fetch the actor from DB
		Actor dbActor = _actrRepo.findOne(actor.getId());

		if (dbActor!=null) {
			//Actor dbActor = optActor.get();
			// compare if other field has been modified
			if (!StringUtils.equalsIgnoreCase(dbActor.getLogin(), actor.getLogin())) {
				errFlag = 2;
			} else {
				// update the avatar url
				dbActor.setAvatar(actor.getAvatar());
				_actrRepo.save(dbActor);
			}
		} else {
			errFlag = 1;
		}

		if (errFlag == 1) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else if (errFlag == 2) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public ResponseEntity getAllEventsByActor(Long actorid) {

		List<Event> eventList = _evntRepo.findAllEventsByActorid(actorid);
		if (eventList == null) {
			return new ResponseEntity(eventList, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(eventList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity getAllActorsByEventCount() {
		List<Actor> actorList = _actrRepo.findAllEventsByActor();

		System.out.println("actorList-" + actorList);
		if (actorList == null) {
			return new ResponseEntity(actorList, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(actorList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity getActorsStreak() {

		List<Event> events = _evntRepo.findAll();
		List<Actor> actors = _actrRepo.findAll();

		List<ActorContainer> actorTupleStreaks = new ArrayList<>();

		for (Actor actor : actors) {
			List<Event> collect = events.stream()
					.filter(event -> event.getActor().equals(actor) && event.getType().equals("PushEvent"))
					.sorted(Comparator.comparing(Event::getCreatedAt).reversed()).collect(Collectors.toList());

			if (!collect.isEmpty()) {
				if (collect.size() == 1) {
					actorTupleStreaks.add(new ActorContainer(actor, 0, collect.get(0).getCreatedAt()));
				} else {
					Integer mayorStreak = GithubApiRestUtil.getStreak(collect);
					actorTupleStreaks.add(new ActorContainer(actor, mayorStreak, collect.get(0).getCreatedAt()));
				}
			}
		}
		List<ActorDTO> actorList = GithubApiRestUtil.getCollectionWithCriteria(actorTupleStreaks);
		
		return new ResponseEntity(actorList, HttpStatus.OK);

	}

}
