package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
