package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.repository.LineRepository;
import org.fmi.spring.boatyservice.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {

   @Autowired
   private LineRepository repository;

   @Override
   public List<Line> getAllLines() {
      return repository.findAll();
   }

   public Line addLine(Line line) {
      return repository.save(line);
   }

   private Line loadById(Long id) {
      return repository.findById(id).orElseThrow(
            () -> new ApplicationException(String.format("Line with id %d does not exist", id), HttpStatus.NOT_FOUND));
   }

   @Override
   public Line updateLine(Line l) {
      Line oldLine = loadById(l.getId());
      oldLine.setName(l.getName());
      oldLine.setStations(l.getStations());
      oldLine.setTransportType(l.getTransportType());
      return repository.save(oldLine);
   }


   @Override
   public Line deleteLine(Long id) {
      Line oldLine = loadById(id);
      repository.deleteById(id);
      return oldLine;
   }


}
