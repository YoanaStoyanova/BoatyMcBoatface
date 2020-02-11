package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/lines")
public class LineController {

   @Autowired
   private LineService lineService;

   @GetMapping
   public List<Line> getAllLines() {
      return lineService.getAllLines();
   }
}
