package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.StationDetails;
import org.fmi.spring.boatyservice.api.bindings.ZoneDetails;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.model.Zone;
import org.fmi.spring.boatyservice.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

   @Autowired
   private ZoneService zoneService;

   @GetMapping
   public List<ZoneDetails> getAllZones() {
      List<Zone> zones = zoneService.getAllZone();
      List<ZoneDetails> zoneDetails = new ArrayList<>();
      for (Zone zone : zones) {
         zoneDetails.add(getZoneDetails(zone));
      }
      return zoneDetails;
   }

   @PostMapping
   public ZoneDetails addZone(@RequestBody Zone zone) {
      Set<Station> stations = zone.getStations();
      for (Station station : stations) {
         station.setZone(zone);
      }
      return getZoneDetails(zoneService.addZone(zone));
   }

   @PostMapping("/{zone_id}")
   public ZoneDetails updateZone(@PathVariable(name = "zone_id") Long id, @RequestBody ZoneDetails zoneDetails) {
      return getZoneDetails(zoneService.updateZone(zoneDetails));
   }

   @DeleteMapping("/{zone_id}")
   public ZoneDetails deleteZone(@PathVariable(name = "zone_id") Long id) {
      return getZoneDetails(zoneService.deleteZone(id));
   }

   ZoneDetails getZoneDetails(Zone zone) {
      Set<Station> stations = zone.getStations();
      ZoneDetails zoneDetails = new ZoneDetails();
      zoneDetails.setId(zone.getId());
      zoneDetails.setName(zone.getName());
      if (stations != null) {
         Set<StationDetails> stationDetails = new HashSet<>();
         for (Station station : stations) {
            stationDetails.add(new StationDetails(station.getId(), station.getName(), zone.getId(), zone.getName()));
         }
         zoneDetails.setStations(stationDetails);
      }
      return zoneDetails;
   }
}
