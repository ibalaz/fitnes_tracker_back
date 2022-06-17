package com.baki.fitness_tracker.controller;

import com.baki.fitness_tracker.dbModel.excerciseModel.Exercise;
import com.baki.fitness_tracker.service.ExerciseService;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for database table Exercise
 *
 * @author Baki
 */
@RestController
@RequestMapping("exercise")
@Slf4j
@AllArgsConstructor
public class ExerciseController {

  private final ExerciseService exerciseService;

  /**
   * Method for getting all exercises.
   *
   * @return {List<Exercise>} List of all exercises
   */
  @GetMapping(path = "/getAll")
  public List<Exercise> fetchAllExercise() {
    log.info("Calling fetch all exercises");
    return exerciseService.getAllExercise();
  }

  /**
   * @param newExercise {Exercise} object coresponding to mongoDB document Exercise
   * @return {ServiceResult} success - boolean, result - what was inserted, errorMessageList - List of error messages
   */
  @PostMapping(path = "/saveExercise")
  public ServiceResult saveNewExercise(@RequestBody Exercise newExercise) {
    log.info("Calling save new exercise");
    return exerciseService.saveNewExercise(newExercise);
  }
}
