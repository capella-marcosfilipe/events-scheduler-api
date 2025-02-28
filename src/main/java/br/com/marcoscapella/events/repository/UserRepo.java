package br.com.marcoscapella.events.repository;

import br.com.marcoscapella.events.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {
    public User findByEmail(String email);
}
