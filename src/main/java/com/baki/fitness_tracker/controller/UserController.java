package com.baki.fitness_tracker.controller;

import com.baki.fitness_tracker.dbModel.userModel.User;
import com.baki.fitness_tracker.service.UserService;
import com.baki.fitness_tracker.util.ServiceResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Rest controller for database table User
 *
 * @author Baki
 */

@RestController
@RequestMapping("users")
@Slf4j
@AllArgsConstructor
public class UserController {

  private final UserService userService;

  /**
   * Endpoint to get all users
   *
   * @return ServiceResult
   */
  @GetMapping(path = "/getAll")
  public List<User> fetchAllUsers() {
    log.info("Calling fetchAllUsers");
    return userService.getAllUsers();
  }

  /**
   * Endpoint to get details for specific user
   *
   * @param user String - for which user are we getting details
   * @return ServiceResult
   */
  @GetMapping(path = "/get")
  public ServiceResult fetchSpecificUser(@RequestParam String user) {
    log.info("Calling fetch specific user " + user);
    return userService.getSpecificUser(user);
  }

  /**
   * Endpoint to create new user
   *
   * @param newUser User - user details that we are saving
   * @return ServiceResult
   */
  @PostMapping(path = "/saveUser")
  public ServiceResult saveNewUser(@RequestBody User newUser) {
    log.info("Calling saveNewUser with params " + newUser);
    newUser.setCreated(new Date());
    newUser.setTotalSpentExercising(BigDecimal.ZERO);
    return userService.saveNewUser(newUser);
  }
}
