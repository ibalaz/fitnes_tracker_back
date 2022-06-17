package com.baki.fitness_tracker.dbModel.excerciseModel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * Exercise document class for mongoDB
 *
 * @author Baki
 */
@Data
@Document
public class Exercise {
  @Id
  private String id;

  @Indexed(unique = true)
  private String name;

  private IntensityFactor intensityLevel;
  private BigDecimal caloriesPerMinute;
  private List<BodySection> bodySection;
  private BigDecimal preferredDuration;
}
