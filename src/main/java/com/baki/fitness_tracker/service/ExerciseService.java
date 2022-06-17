package com.baki.fitness_tracker.service;

import com.baki.fitness_tracker.dbModel.excerciseModel.Exercise;
import com.baki.fitness_tracker.repository.ExerciseRepository;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for working with Exercise controller
 *
 * @author Baki
 */
@Service
@Slf4j
@AllArgsConstructor
public class ExerciseService {

  private final ExerciseRepository exerciseRepository;

  public List<Exercise> getAllExercise() {
    return exerciseRepository.findAll();
  }

  public ServiceResult saveNewExercise(Exercise newExercise) {
    ServiceResult result = new ServiceResult();
    exerciseRepository.findExerciseByName(newExercise.getName())
        .ifPresentOrElse(e -> {
          log.error("Exercise with name {} already exists", newExercise.getName());
          result.success = false;
          result.errorMessageList = List.of(newExercise.getName() + " already exists");
        }, () -> {
          log.info("Inserting " + newExercise);
          try {
            exerciseRepository.insert(newExercise);
            result.success = true;
            result.result = newExercise;
            log.info("Inserting done...");
          } catch (Exception e) {
            log.error("Exception inserting to database " + e);
            result.success = false;
            result.errorMessageList = List.of("Exception in inserting to database " + e);
          }
        });
    return result;
  }
}
