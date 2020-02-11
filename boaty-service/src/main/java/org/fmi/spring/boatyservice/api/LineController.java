package org.fmi.spring.boatyservice.api;

import org.fmi.spring.boatyservice.api.bindings.LineDetails;
import org.fmi.spring.boatyservice.exception.ApplicationException;
import org.fmi.spring.boatyservice.model.Line;
import org.fmi.spring.boatyservice.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.fmi.spring.boatyservice.api.bindings.LineDetails.getLineDetails;

@RestController
@RequestMapping("/api/lines")
public class LineController {

   @Autowired
   private LineService lineService;

   @GetMapping
   public List<LineDetails> getAllLines() {
      return lineService.getAllLines().stream().map(LineDetails::getLineDetails).collect(Collectors.toList());
   }

   @PostMapping
   public LineDetails addLine(@RequestBody Line line) {
      Line addedLine = lineService.addLine(line);
      return getLineDetails(addedLine);
   }

   @PostMapping("/{line_id}")
   public LineDetails updateLine(@PathVariable(name = "line_id") Long lineId, @RequestBody Line line) {
      if (!lineId.equals(line.getId())) {
         throw new ApplicationException("Not matching ids", HttpStatus.BAD_REQUEST);
      }
      return getLineDetails(lineService.updateLine(line));
   }

   @DeleteMapping("/{line_id}")
   public LineDetails deleteLine(@PathVariable(name = "line_id")Long lineId){
      return getLineDetails(lineService.deleteLine(lineId));
   }

}
