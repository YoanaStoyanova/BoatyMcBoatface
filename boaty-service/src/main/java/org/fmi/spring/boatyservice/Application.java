package org.fmi.spring.boatyservice;

import java.util.Arrays;
import java.util.Collections;

import org.fmi.spring.boatyservice.model.TransportType;
import org.fmi.spring.boatyservice.model.User;
import org.fmi.spring.boatyservice.repository.TransportTypeRepository;
import org.fmi.spring.boatyservice.repository.UserRepository;
import org.fmi.spring.boatyservice.security.authentication.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

   @Autowired
   private PasswordEncoder passwordEncoder;

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

   @Bean
   public ApplicationRunner initializer(UserRepository repository, TransportTypeRepository transportTypeRepository) {
      User user = new User();
      user.setUsername("admin@app.local");
      user.setPassword(passwordEncoder.encode("admin"));
      user.setRole(UserRole.ADMIN);

      return args -> {
          transportTypeRepository.saveAll(Arrays.asList(
              new TransportType(1L, "Bus", "fa-bus"),
              new TransportType(2L, "Subway", "fa-subway"),
              new TransportType(3L, "Train", "fa-train"),
              new TransportType(4L, "Train", "fa-tram"),
              new TransportType(5L, "Shuttle van", "fa-shuttle-van")
          ));
         repository.saveAll(Collections.singletonList(user));
      };
   }
}
