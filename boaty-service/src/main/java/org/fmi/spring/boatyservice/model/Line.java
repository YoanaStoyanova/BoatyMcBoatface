package org.fmi.spring.boatyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity(name = "line")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Line {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @ManyToOne
   @JoinColumn(name = "transportTypeId")
   private TransportType transportType;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "line_stations",
         joinColumns = @JoinColumn(name = "line_id"),
         inverseJoinColumns = @JoinColumn(name = "station_id"))
   private Set<Station> stations;

   @ManyToMany(mappedBy = "lines", fetch = FetchType.EAGER)
   private Set<Ticket> tickets;

   @ManyToMany(mappedBy = "additionalLines", fetch = FetchType.EAGER)
   private Set<Ticket> additionalTickets;
}
