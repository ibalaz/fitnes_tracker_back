package com.baki.fitness_tracker.repository;

import com.baki.fitness_tracker.dbModel.excerciseModel.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Mongo repository for Exercise document
 *
 * @author Baki
 */
public interface ExerciseRepository extends MongoRepository<Exercise, String> {
  Optional<Exercise> findExerciseByName(String name);
}
