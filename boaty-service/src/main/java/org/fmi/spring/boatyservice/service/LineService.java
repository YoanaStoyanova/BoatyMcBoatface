package org.fmi.spring.boatyservice.service;

import org.fmi.spring.boatyservice.model.Line;

import java.util.List;

public interface LineService {

   List<Line> getAllLines();

   Line addLine(Line l);

   Line updateLine(Line l);

   Line deleteLine(Long id);
}
