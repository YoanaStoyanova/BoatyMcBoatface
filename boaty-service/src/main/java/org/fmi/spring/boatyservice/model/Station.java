package org.fmi.spring.boatyservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.Set;

@Entity(name = "station")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Station {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NonNull
   private String name;

   @ManyToMany(mappedBy = "stations")
   private Set<Line> lines;

   @ManyToOne(fetch = FetchType.EAGER)
   private Zone zone;

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Station station = (Station) o;
      return Objects.equals(id, station.id) &&
            Objects.equals(name, station.name);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name);
   }



}
