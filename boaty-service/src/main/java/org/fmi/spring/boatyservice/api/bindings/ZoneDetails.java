package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ZoneDetails {

   Long id;

   String name;

   Set<StationDetails> stations;
}
