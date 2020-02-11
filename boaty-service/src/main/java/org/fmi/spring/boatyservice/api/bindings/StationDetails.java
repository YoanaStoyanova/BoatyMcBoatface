package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fmi.spring.boatyservice.model.Station;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StationDetails {

   private Long id;

   private String name;

   Long zoneId;

   String zoneName;

   public static StationDetails getStationDetails(Station station) {
      String zoneName = "";
      Long zoneId = null;
      if (station.getZone() != null) {
         zoneName = station.getZone().getName();
         zoneId = station.getZone().getId();
      }
      return new StationDetails(station.getId(), station.getName(), zoneId, zoneName);
   }
}
