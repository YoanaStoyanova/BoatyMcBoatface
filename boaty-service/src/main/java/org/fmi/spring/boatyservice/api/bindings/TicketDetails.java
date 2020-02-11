package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.model.Ticket;
import org.fmi.spring.boatyservice.model.Zone;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TicketDetails {

   @NonNull
   private Long id;

   @NonNull
   private Integer minutesValidFor;

   @NonNull
   private Float price;

   @NonNull
   private String name;

   private Set<LineDetails> lines;

   private Set<LineDetails> additionalLines;

   private Set<ZoneDetails> zones;

   public static TicketDetails getTicketDetails(Ticket ticket) {
      TicketDetails ticketDetails = new TicketDetails(ticket.getId(), ticket.getMinutesValidFor(), ticket.getPrice(), ticket.getName());
      Set<Zone> zones = ticket.getZones();
      Set<ZoneDetails> zoneDetails = new HashSet<>();
      if (zones != null) {
         zoneDetails = zones.stream().map(ZoneDetails::getZoneDetails).collect(Collectors.toSet());
      }
      Set<Line> lines = ticket.getLines();
      Set<LineDetails> lineDetails = new HashSet<>();
      if (lines != null) {
         lineDetails = lines.stream().map(LineDetails::getLineDetails).collect(Collectors.toSet());
      }
      Set<Line> additionalLines = ticket.getAdditionalLines();
      Set<LineDetails> additionaLineDetails = new HashSet<>();
      if (additionalLines != null) {
         additionaLineDetails = additionalLines.stream().map(LineDetails::getLineDetails).collect(Collectors.toSet());
      }

      ticketDetails.setZones(zoneDetails);
      ticketDetails.setLines(lineDetails);
      ticketDetails.setAdditionalLines(additionaLineDetails);
      return ticketDetails;
   }

}
