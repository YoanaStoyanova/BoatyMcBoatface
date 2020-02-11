package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
}
