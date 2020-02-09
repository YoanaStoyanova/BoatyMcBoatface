package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stations")
public class StationController {

   @Autowired
   private StationService stationService;

   @GetMapping
   public List<Station> getAllStations() {
      return stationService.getAllStations();
   }

   @PostMapping
   public Station addStation(@RequestBody Station s) {
      return stationService.addStation(s);
   }

}
