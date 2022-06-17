package com.baki.fitness_tracker.repository;

import com.baki.fitness_tracker.dbModel.userModel.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Mongo repository for User document
 *
 * @author Baki
 */
public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findUserByEmail(String email);

  Optional<User> findUserByUsername(String user);

}
