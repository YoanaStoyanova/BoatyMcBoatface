package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.api.bindings.StationDetails;
import org.fmi.spring.boatyservice.model.Station;

import java.util.List;

public interface StationService {

   List<Station> getAllStations();

   Station addStation(StationDetails s);

   Station updateStation(StationDetails s);

   Station updateStation(Station s);

   Station deleteStation(Long id);
}
