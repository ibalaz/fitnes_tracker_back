package com.baki.fitness_tracker.controller;

import com.baki.fitness_tracker.dbModel.userExcerciseModel.UserExercise;
import com.baki.fitness_tracker.service.UserExerciseService;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * Rest controller for database table UserExercise
 *
 * @author Baki
 */
@RestController
@RequestMapping("userExercise")
@AllArgsConstructor
@Slf4j
public class UserExerciseController {
  private final UserExerciseService userExerciseService;

  /**
   * Endpoint for getting all exercises for specific user
   *
   * @param user String - for which user to get exercises
   * @return ServiceResult - with all exercises for user
   */
  @GetMapping("/getAll")
  public ServiceResult fetchAllUserExercises(@RequestParam String user) {
    log.info("Calling fetch all user exercise for user" + user);
    return userExerciseService.getAllUserExercises(user);
  }

  /**
   * Endpoint to save new exercise for user.
   *
   * @param newUserExercise UserExercise - what new exercise to add
   * @return ServiceResult - success, result with what user exercise was saved
   */
  @PostMapping(path = "/save")
  public ServiceResult saveUserExercise(@RequestBody UserExercise newUserExercise) {
    log.info("Calling save user exercise " + newUserExercise);
    return userExerciseService.saveNewUserExercise(newUserExercise);
  }

  /**
   * Endpoint to delete existing exercise for user
   *
   * @param userExercise UserExercise - what exercise to delete
   * @return ServiceResult - success, result with which userExercise was deleted
   */
  @PostMapping(path = "/delete")
  public ServiceResult deleteUserExercise(@RequestBody UserExercise userExercise) {
    log.info("Calling delete user exercise " + userExercise);
    return userExerciseService.deleteUserExercise(userExercise);
  }

}
