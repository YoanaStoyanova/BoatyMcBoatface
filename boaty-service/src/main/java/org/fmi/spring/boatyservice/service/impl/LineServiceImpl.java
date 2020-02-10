package org.fmi.spring.boatyservice.service.impl;

import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.repository.LineRepository;
import org.fmi.spring.boatyservice.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
