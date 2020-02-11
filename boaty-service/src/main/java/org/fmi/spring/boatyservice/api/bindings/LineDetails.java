package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.model.TransportType;
import org.fmi.spring.boatyservice.model.Zone;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LineDetails {

   Long id;

   String name;

   TransportType transportType;

   Set<StationDetails> stations;

   public static LineDetails getLineDetails(Line line) {
      LineDetails lineDetails = new LineDetails();
      lineDetails.setId(line.getId());
      lineDetails.setName(line.getName());
      lineDetails.setTransportType(line.getTransportType());
      Set<Station> stations = line.getStations();
      Set<StationDetails> stationDetailsSet = new HashSet<>();
      stations.forEach(station -> {
         Zone zone = station.getZone();
         StationDetails stationDetails = new StationDetails();
         stationDetails.setId(station.getId());
         stationDetails.setName(station.getName());
         if (zone != null) {
            stationDetails.setZoneId(zone.getId());
            stationDetails.setZoneName(zone.getName());
         }
         stationDetailsSet.add(stationDetails);
      });
      lineDetails.setStations(stationDetailsSet);
      return lineDetails;
   }
}
