package org.fmi.spring.boatyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "transportType")
@NoArgsConstructor
@Getter
@Setter
public class TransportType {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String icon;

   public TransportType(Long id, String name, String icon) {
      this.id = id;
      this.name = name;
      this.icon = icon;
   }

}
