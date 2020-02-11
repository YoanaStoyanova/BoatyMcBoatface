package org.fmi.spring.boatyservice.api.bindings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fmi.spring.boatyservice.model.TransportType;

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
}
