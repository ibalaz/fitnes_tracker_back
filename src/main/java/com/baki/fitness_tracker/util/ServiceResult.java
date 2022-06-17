package com.baki.fitness_tracker.util;

import java.util.List;

/**
 * Util class for easier service response mapping
 *
 * @author Baki
 */
public class ServiceResult {
  public boolean success;
  public List<String> errorMessageList;
  public Object result;
}
