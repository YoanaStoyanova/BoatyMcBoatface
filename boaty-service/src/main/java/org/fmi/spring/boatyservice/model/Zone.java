package org.fmi.spring.boatyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Objects;
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
         fetch = FetchType.EAGER,
         cascade = CascadeType.MERGE,
         orphanRemoval = true
   )
   private Set<Station> stations;

   @ManyToMany(mappedBy = "zones")
   private Set<Ticket> tickets;

   public void addStation(Station s) {
      stations.add(s);
   }

   public void removeStation(Station s) {
      stations.remove(s);
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Zone zone = (Zone) o;
      return Objects.equals(id, zone.id) &&
            Objects.equals(name, zone.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name);
   }

}
