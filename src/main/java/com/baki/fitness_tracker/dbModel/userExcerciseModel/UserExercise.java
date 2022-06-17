package com.baki.fitness_tracker.dbModel.userExcerciseModel;

import com.baki.fitness_tracker.dbModel.excerciseModel.Exercise;
import com.baki.fitness_tracker.dbModel.userModel.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * UserExercise document class for mongoDB
 *
 * @author Baki
 */
@Data
@Document
@AllArgsConstructor
public class UserExercise {
  @Id
  public String id;

  public String username;
  public Exercise exercise;

  public BigDecimal duration;
  public FunFactor funFactor;
  public Date exerciseDate;
}
