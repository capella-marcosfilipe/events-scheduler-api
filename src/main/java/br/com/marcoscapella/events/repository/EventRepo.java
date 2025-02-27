package br.com.marcoscapella.events.repository;

import br.com.marcoscapella.events.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepo extends CrudRepository<Event, Integer> {
    public Event findByPrettyName(String prettyName);
}
