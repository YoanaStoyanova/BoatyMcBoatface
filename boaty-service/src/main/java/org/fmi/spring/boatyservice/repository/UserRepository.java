package org.fmi.spring.boatyservice.repository;

import java.util.Optional;

import org.fmi.spring.boatyservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

}
