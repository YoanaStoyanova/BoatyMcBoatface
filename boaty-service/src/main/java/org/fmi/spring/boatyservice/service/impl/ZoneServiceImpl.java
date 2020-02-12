package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.api.bindings.ZoneDetails;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.model.Zone;
import org.fmi.spring.boatyservice.repository.ZoneRepository;
import org.fmi.spring.boatyservice.service.StationService;
import org.fmi.spring.boatyservice.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ZoneServiceImpl implements ZoneService {

   @Autowired
   private ZoneRepository repository;

   @Autowired
   private StationService stationService;

   @Override
   public List<Zone> getAllZone() {
      return repository.findAll();
   }

   @Override
   public Zone findById(Long id) {
      return repository.findById(id).orElseThrow(() -> new ApplicationException(
            String.format("Zone with given id %d not found", id), HttpStatus.BAD_REQUEST));
   }

   @Override
   public Zone addZone(Zone zone) {
      Zone savedZone = repository.save(zone);
      Set<Station> stations = zone.getStations();
      for (Station station : stations) {
         station.setZone(savedZone);
         stationService.updateStation(station);
      }
      return savedZone;
   }

   private Zone loadById(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("Station with id %d does not exist", id), HttpStatus.NOT_FOUND));
   }

   @Override
   public Zone updateZone(ZoneDetails zoneDetails) {
      Zone oldZone = loadById(zoneDetails.getId());
      oldZone.setName(zoneDetails.getName());
      Set<Station> oldStations = oldZone.getStations();
      Set<Station> newStations = zoneDetails.getStations().stream().map(stationDetails ->
            new Station(stationDetails.getId(), stationDetails.getName(), null, oldZone)).collect(Collectors.toSet());
      Set<Station> toBeRemoved = new HashSet<>(oldStations);
      toBeRemoved.removeAll(newStations);
      toBeRemoved.forEach(station -> {
         oldZone.removeStation(station);
         station.setZone(null);
         stationService.updateStation(station);
      });
      Set<Station> toBeAdded = new HashSet<>(newStations);
      toBeAdded.removeAll(oldStations);
      toBeAdded.forEach(station -> {
         oldZone.addStation(station);
         stationService.updateStation(station);
      });
      return repository.save(oldZone);
   }

   @Override
   public Zone deleteZone(Long id) {
      Zone oldZone = loadById(id);
      oldZone.getStations().forEach(station -> {
         station.setZone(null);
         stationService.updateStation(station);
      });
      repository.deleteById(id);
      return oldZone;
   }

}
