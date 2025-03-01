package br.com.marcoscapella.events.repository;

import br.com.marcoscapella.events.dto.SubscriptionRankingItem;
import br.com.marcoscapella.events.model.Subscription;
import br.com.marcoscapella.events.model.User;
import br.com.marcoscapella.events.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {
    public Subscription findByEventAndSubscriber(Event evt, User user);

    @Query(value = "select count(subscription_number) as quantidade, indication_user_id, tbl_user.user_name " +
            "from tbl_subscription inner join tbl_user " +
            "on tbl_subscription.indication_user_id = tbl_user.user_id " +
            "where indication_user_id is not null " +
            "and event_id = :eventId " +
            "group by indication_user_id " +
            "order by quantidade desc", nativeQuery = true)
    public List<SubscriptionRankingItem> generateRanking(@Param("eventId") Integer eventId);
}
