package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.Line;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineRepository extends JpaRepository<Line, Long> {
}
