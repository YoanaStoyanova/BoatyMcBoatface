package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.StationDetails;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

   @Autowired
   private StationService stationService;

   @GetMapping
   public List<StationDetails> getAllStations() {
      List<Station> stations = stationService.getAllStations();
      List<StationDetails> result = new ArrayList<>();
      for (Station station : stations) {
         StationDetails stationDetails = getStationDetails(station);
         result.add(stationDetails);
      }
      return result;
   }

   @PostMapping
   public Station addStation(@RequestBody StationDetails s) {
      return stationService.addStation(s);
   }

   @PostMapping("/{station_id}")
   public StationDetails updateStation(@PathVariable(name = "station_id") Long stationId, @RequestBody StationDetails s) {
      return getStationDetails(stationService.updateStation(s));
   }

   @DeleteMapping("/{station_id}")
   public StationDetails deleteStation(@PathVariable(name = "station_id")Long stationId){
      return getStationDetails(stationService.deleteStation(stationId));
   }

   StationDetails getStationDetails(Station station) {
      String zoneName = "";
      Long zoneId = null;
      if (station.getZone() != null) {
         zoneName = station.getZone().getName();
         zoneId = station.getZone().getId();
      }
      return new StationDetails(station.getId(), station.getName(), zoneId, zoneName);
   }

}
