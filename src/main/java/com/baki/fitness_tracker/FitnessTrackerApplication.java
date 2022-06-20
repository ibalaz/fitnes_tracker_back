package com.baki.fitness_tracker;

import com.baki.fitness_tracker.dbModel.userModel.User;
import com.baki.fitness_tracker.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.util.List;

@SpringBootApplication
@Slf4j
public class FitnessTrackerApplication {

  public static void main(String[] args) {
    SpringApplication.run(FitnessTrackerApplication.class, args);
  }

/*
    @Bean
    CommandLineRunner runner (UserRepository repository, MongoTemplate template) {
        return args -> {
            Address address = new Address(
                    "Hrvatske Republike",
                    "25",
                    "Antunovac",
                    "Hrvatska",
                    "31216"
            );
            String email = "ibalaz66@gmail.com";
            User user = new User(
                    "Ivan",
                    "Balaž",
                    email,
                    Gender.MALE,
                    address,
                    List.of("veslanje", "zumba"),
                    BigDecimal.valueOf(100.0),
                    LocalDateTime.now()
            );

            // usingMongoTemplate(repository, template, email, user);

            repository.findUserByEmail(email)
                    .ifPresentOrElse(u -> {
                        log.error(u + " already exits");

                    }, ()-> {
                        log.info("Inserting " + user);
                        repository.insert(user);
                    } );

        };
    }
*/

  private void usingMongoTemplate(UserRepository repository, MongoTemplate template, String email, User user) {
    Query query = new Query();
    query.addCriteria(Criteria.where("email").is(email));

    List<User> users = template.find(query, User.class);

    if (users.size() > 1) {
      throw new IllegalStateException("already exists email " + email);
    }
    if (users.isEmpty()) {
      log.info("Inserting " + user);
      repository.insert(user);
    } else {
      log.error(user + " already exits");
    }
  }
}
