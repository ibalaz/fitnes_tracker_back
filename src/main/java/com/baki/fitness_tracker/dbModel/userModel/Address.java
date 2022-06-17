package com.baki.fitness_tracker.dbModel.userModel;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Address class used in User document
 *
 * @author Baki
 */
@Data
@AllArgsConstructor
public class Address {
  private String streetName;
  private String houseNo;
  private String city;
  private String country;
  private String postCode;

}
