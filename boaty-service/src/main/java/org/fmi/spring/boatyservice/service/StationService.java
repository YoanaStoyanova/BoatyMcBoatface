package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.model.Station;

import java.util.List;

public interface StationService {

   List<Station> getAllStations();

   Station addStation(Station s);
}
