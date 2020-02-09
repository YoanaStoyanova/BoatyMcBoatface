package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.repository.StationRepository;
import org.fmi.spring.boatyservice.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

   @Autowired
   private StationRepository repository;

   @Override
   public List<Station> getAllStations() {
      return repository.findAll();
   }

   @Override
   public Station addStation(Station s) {
      return repository.save(s);
   }
}
