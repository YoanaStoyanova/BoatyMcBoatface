package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.repository.StationRepository;
import org.fmi.spring.boatyservice.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

   private Station loadById(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("Station with id %d does not exist", id), HttpStatus.NOT_FOUND));
   }

   @Override
   public Station updateStation(Station s) {
      Station station = loadById(s.getId());
      station.setName(s.getName());
      return repository.save(station);
   }

   @Override
   public Station deleteStation(Long id) {
      Station old = loadById(id);
      repository.deleteById(id);
      return old;
   }
}
