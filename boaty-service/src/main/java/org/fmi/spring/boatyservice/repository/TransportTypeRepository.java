package org.fmi.spring.boatyservice.repository;

import org.fmi.spring.boatyservice.model.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportTypeRepository extends JpaRepository<TransportType, Long> {

}
