package com.example.application.repository;

import com.example.application.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User findUserByUsername(String username);

    Optional<User> findUserById (Long id);

}
