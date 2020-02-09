package org.fmi.spring.boatyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity(name = "zone")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Zone {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   @OneToMany(
         mappedBy = "zone",
         cascade = CascadeType.ALL,
         orphanRemoval = true
   )
   private Set<Station> stations;

   @ManyToMany(mappedBy = "zones")
   private Set<Ticket> tickets;
}
