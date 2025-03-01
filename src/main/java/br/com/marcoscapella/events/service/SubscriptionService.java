package br.com.marcoscapella.events.service;

import br.com.marcoscapella.events.dto.SubscriptionResponse;
import br.com.marcoscapella.events.exception.EventNotFoundException;
import br.com.marcoscapella.events.exception.SubscriptionConflictException;
import br.com.marcoscapella.events.exception.UserIndicatorNotFoundException;
import br.com.marcoscapella.events.model.Event;
import br.com.marcoscapella.events.model.Subscription;
import br.com.marcoscapella.events.model.User;
import br.com.marcoscapella.events.repository.EventRepo;
import br.com.marcoscapella.events.repository.SubscriptionRepo;
import br.com.marcoscapella.events.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    @Autowired
    private EventRepo evtRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SubscriptionRepo subRepo;

    public SubscriptionResponse createNewSubscription(String eventName, User user, Integer userId) {
        // retrieve the event by its name
        Event evt = evtRepo.findByPrettyName(eventName);
        if (evt == null){
            throw new EventNotFoundException("Evento " + eventName + " não existe.");
        }

        User userRec = userRepo.findByEmail(user.getEmail());
        // if the user doesn't exist...
        if (userRec == null) {
            // ...save the user in the db
            userRec = userRepo.save(user);
        }

        User indicator = null;
        if (userId != null) {
            indicator = userRepo.findById(userId).orElse(null);
            if (indicator == null) {
                throw new UserIndicatorNotFoundException("Usuário " + userId + " indicador não existe.");
            }
        }

        Subscription subs = new Subscription();
        subs.setEvent(evt);
        subs.setSubscriber(userRec);
        subs.setIndication(indicator);

        Subscription tmpSub = subRepo.findByEventAndSubscriber(evt, userRec);
        if (tmpSub != null) {
            throw new SubscriptionConflictException("Já existe inscrição para o usuário " + userRec.getName() + " no evento " + evt.getTitle());
        }

        Subscription res = subRepo.save(subs);
        return new SubscriptionResponse(res.getSubscriptionNumber(), "http://codecraft.com/subscription/" + res.getEvent().getPrettyName() + "/" + res.getSubscriber().getId());
    }
}
