package com.baki.fitness_tracker.dbModel.userModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * User document class for mongoDB
 *
 * @author Baki
 */
@Data
@Document
public class User {
  @Id
  private String id;
  private String firstName;
  private String lastName;
  @Indexed(unique = true)
  private String username;
  @Indexed(unique = true)
  private String email;
  private Gender gender;
  private Address address;
  private List<String> favouriteExercise;
  private BigDecimal totalSpentExercising;

  private Date created;

  public User(String firstName,
              String lastName,
              String username,
              String email,
              Gender gender,
              Address address,
              List<String> favouriteExercise,
              BigDecimal totalSpentExercising,
              Date created) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
    this.gender = gender;
    this.address = address;
    this.favouriteExercise = favouriteExercise;
    this.totalSpentExercising = totalSpentExercising;
    this.created = created;
  }
}
