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
import java.util.Set;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ticket {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private Integer minutesValidFor;

   private Float price;

   private String name;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "ticket_zone_validity",
         joinColumns = @JoinColumn(name = "ticket_id"),
         inverseJoinColumns = @JoinColumn(name = "zone_id"))
   private Set<Zone> zones;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "ticket_line_validity",
         joinColumns = @JoinColumn(name = "ticket_id"),
         inverseJoinColumns = @JoinColumn(name = "line_id"))
   private Set<Line> lines;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "ticket_additional_line_validity",
         joinColumns = @JoinColumn(name = "ticket_id"),
         inverseJoinColumns = @JoinColumn(name = "line_id"))
   private Set<Line> additionalLines;

}
