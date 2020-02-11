package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.LineDetails;
import org.fmi.spring.boatyservice.api.bindings.StationDetails;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.model.Station;
import org.fmi.spring.boatyservice.model.Zone;
import org.fmi.spring.boatyservice.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lines")
public class LineController {

   @Autowired
   private LineService lineService;

   @GetMapping
   public List<LineDetails> getAllLines() {
      return lineService.getAllLines().stream().map(this::getLineDetails).collect(Collectors.toList());
   }

   @PostMapping
   public LineDetails addLine(@RequestBody Line line) {
      Line addedLine = lineService.addLine(line);
      return getLineDetails(addedLine);
   }

   @PostMapping("/{line_id}")
   public LineDetails updateLine(@PathVariable(name = "line_id") Long lineId, @RequestBody Line line) {
      if (!lineId.equals(line.getId())) {
         throw new ApplicationException("Not matching ids", HttpStatus.BAD_REQUEST);
      }
      return getLineDetails(lineService.updateLine(line));
   }

   @DeleteMapping("/{line_id}")
   public LineDetails deleteLine(@PathVariable(name = "line_id")Long lineId){
      return getLineDetails(lineService.deleteLine(lineId));
   }

   private LineDetails getLineDetails(Line line) {
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
