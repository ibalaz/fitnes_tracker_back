package com.baki.fitness_tracker.service;

import com.baki.fitness_tracker.dbModel.userModel.User;
import com.baki.fitness_tracker.repository.UserRepository;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for working with User controller
 *
 * @author Baki
 */
@Service
@AllArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;

  /**
   * Service to fetch all users
   *
   * @return List<User> returns list of all users
   */
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  /**
   * Service to get details for specific user.
   *
   * @param user String - which user are we getting
   * @return ServiceResult
   */
  public ServiceResult getSpecificUser(String user) {
    ServiceResult result = new ServiceResult();
    userRepository.findUserByUsername(user).ifPresentOrElse(u -> {
      log.info("Fetching user {} from database", user);
      result.result = u;
      result.success = true;
    }, () -> {
      log.error(user + " can not be found in database!");
      result.success = false;
      result.errorMessageList = List.of(user + " can not be found in database!");
    });
    return result;
  }

  /**
   * Service to create new user
   *
   * @param newUser User - user details that we are generating
   * @return ServiceResult
   */
  public ServiceResult saveNewUser(User newUser) {
    ServiceResult result = new ServiceResult();
    userRepository.findUserByEmail(newUser.getEmail())
        .ifPresentOrElse(u -> {
          log.error(u + " already exits");
          result.success = false;
          result.errorMessageList = List.of(u + " already exists");
          result.result = null;
        }, () -> {
          log.info("Inserting " + newUser);
          try {
            userRepository.insert(newUser);
            result.success = true;
            result.result = newUser;
            log.info("Inserting done...");
          } catch (Exception e) {
            log.error("Exception in inserting to database " + e);
            result.success = false;
            result.errorMessageList = List.of("Exception in inserting to database " + e);
          }
        });
    return result;
  }
}
