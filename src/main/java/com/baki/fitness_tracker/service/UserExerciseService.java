package com.baki.fitness_tracker.service;

import com.baki.fitness_tracker.dbModel.excerciseModel.Exercise;
import com.baki.fitness_tracker.dbModel.userExcerciseModel.UserExercise;
import com.baki.fitness_tracker.dbModel.userModel.User;
import com.baki.fitness_tracker.repository.ExerciseRepository;
import com.baki.fitness_tracker.repository.UserExerciseRepository;
import com.baki.fitness_tracker.repository.UserRepository;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for working with UserExercise controller
 *
 * @author Baki
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserExerciseService {
  public final UserExerciseRepository userExerciseRepository;
  public final UserRepository userRepository;
  public final ExerciseRepository exerciseRepository;

  MongoOperations mongoOperations;

  /**
   * Service for getting exercises for specific user.
   *
   * @param user String - for which user we are getting exercises
   * @return ServiceResult
   */
  public ServiceResult getAllUserExercises(String user) {
    ServiceResult result = new ServiceResult();
    if (userRepository.findUserByUsername(user).isPresent()) {
      log.info("Getting all {} exercises ", user);
      result.success = true;
      result.result = userExerciseRepository.findUserExercisesByUsername(user);
    } else {
      log.error(user + " does not exist!");
      result.success = false;
      result.errorMessageList = List.of(user + " does not exist!");
    }
    return result;
  }

  /**
   * Service for saving user exercise
   *
   * @param newUserExercise UserExercise which user exercise to save
   * @return ServiceResult
   */
  public ServiceResult saveNewUserExercise(UserExercise newUserExercise) {
    ServiceResult result = new ServiceResult();
    Optional<User> user = userRepository.findUserByUsername(newUserExercise.username);
    // Only one exercise per call can be inserted, so we get first element exercise name and fetch rest of data
    Optional<Exercise> exercise = exerciseRepository.findExerciseByName(newUserExercise.exercise.getName());

    // Optional<UserExercise> userInUserExercise = userExerciseRepository.findUserExercisesByUser(newUserExercise.user.getUsername());

    /*if (userInUserExercise.isPresent() && exercise.isPresent()) {
      // If user entry already exists update document field exercise
      log.info("Updating user {} exercises with {} ...", newUserExercise.user.getUsername(), exercise.get().getName());
      Query query = new Query(Criteria.where("User.username").is(newUserExercise.user.getUsername()));
      UserExercise userExerciseToUpdate = mongoOperations.findOne(query, UserExercise.class);
      assert userExerciseToUpdate != null;
      userExerciseToUpdate.exercise.add(exercise.get());
      userExerciseToUpdate.setExercise(userExerciseToUpdate.exercise);
      mongoOperations.save(userExerciseToUpdate);

      result.success = true;
      result.result = newUserExercise;
      log.info("Done updating userExercise");
    } else {*/
    if (user.isPresent() && exercise.isPresent()) {
      log.info("Inserting new user exercise...");
      try {
        newUserExercise.exercise = exercise.get();
        userExerciseRepository.insert(newUserExercise);

        // Update totalSpentExercising for user
        Query query = new Query(Criteria.where("username").is(newUserExercise.username));
        User userToUpdate = mongoOperations.findOne(query, User.class);
        assert userToUpdate != null;
        userToUpdate.setTotalSpentExercising(userToUpdate.getTotalSpentExercising().add(newUserExercise.duration));
        mongoOperations.save(userToUpdate);

        result.success = true;
        result.result = newUserExercise;
        log.info("Done inserting new user exercise");
      } catch (Exception e) {
        log.error("Error inserting in database " + e);
        result.success = false;
        result.errorMessageList = List.of("Error inserting in database " + e);
      }
    } else {
      log.error(newUserExercise.username + " or " + newUserExercise.exercise.getName() + " does not exist!");
      result.success = false;
      result.errorMessageList = List.of(newUserExercise.username + " or " + newUserExercise.exercise.getName() + " does not exist!", "First create user!");
    }

    return result;
  }

  /**
   * Service to delete existing exercise for user.
   *
   * @param userExercise UserExercise
   * @return ServiceResult
   */
  public ServiceResult deleteUserExercise(UserExercise userExercise) {
    ServiceResult result = new ServiceResult();
    Optional<User> user = userRepository.findUserByUsername(userExercise.username);
    if (user.isPresent()) {
      try {
        userExerciseRepository.delete(userExercise);

        // Update totalSpentExercising for user
        Query query = new Query(Criteria.where("username").is(userExercise.username));
        User userToUpdate = mongoOperations.findOne(query, User.class);
        assert userToUpdate != null;
        userToUpdate.setTotalSpentExercising(userToUpdate.getTotalSpentExercising().subtract(userExercise.duration));
        mongoOperations.save(userToUpdate);

        result.success = true;
        result.result = userExercise;

      } catch (Exception e) {
        log.error("Error deleting from database " + e);
        result.success = false;
        result.errorMessageList = List.of("Error deleting in database " + e);
      }
    } else {
      log.error(userExercise.username + " does not exist!");
      result.success = false;
      result.errorMessageList = List.of(userExercise.username + " does not exist!", "First create user!");
    }
    return result;
  }

}
