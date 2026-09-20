package com.rakesh.rakeshmart.dao;

import com.rakesh.rakeshmart.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    User insert(User user);
    Optional<User> findById(long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    boolean existsByEmail(String email);
}
