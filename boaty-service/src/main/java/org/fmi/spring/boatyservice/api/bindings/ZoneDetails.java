package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.model.Zone;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ZoneDetails {

   Long id;

   String name;

   Set<StationDetails> stations;

   public static ZoneDetails getZoneDetails(Zone zone) {
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
