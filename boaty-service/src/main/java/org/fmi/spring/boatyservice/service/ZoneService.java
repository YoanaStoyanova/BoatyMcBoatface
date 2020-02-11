package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.api.bindings.ZoneDetails;
import org.fmi.spring.boatyservice.model.Zone;

import java.util.List;

public interface ZoneService {

   List<Zone> getAllZone();

   Zone findById(Long id);

   Zone addZone(Zone zone);

   Zone updateZone(ZoneDetails zone);

   Zone deleteZone(Long id);
}
