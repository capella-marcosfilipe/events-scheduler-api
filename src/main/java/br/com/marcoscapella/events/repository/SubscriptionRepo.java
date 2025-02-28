package br.com.marcoscapella.events.repository;

import br.com.marcoscapella.events.model.Subscription;
import br.com.marcoscapella.events.model.User;
import br.com.marcoscapella.events.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
    public Subscription findByEventAndSubscriber(Event evt, User user);
}
