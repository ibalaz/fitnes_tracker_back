package com.baki.fitness_tracker.repository;

import com.baki.fitness_tracker.dbModel.userExcerciseModel.UserExercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Mongo repository for UserExercise document
 *
 * @author Baki
 */
public interface UserExerciseRepository extends MongoRepository<UserExercise, String> {

  Optional<List<UserExercise>> findUserExercisesByUsername(String username);

  UserExercise findUserExercisesByExerciseNameAndUsernameAndExerciseDate(String name, String user, Date date);
}
