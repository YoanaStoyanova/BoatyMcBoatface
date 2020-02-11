import { TransportTypeService } from './../../services/transport-type.service';
import { Router } from '@angular/router';
import { StationService } from './../../services/station.service';
import { LineService } from './../../services/line.service';
import { Component, OnInit } from '@angular/core';
import { StationModel } from 'src/app/model/station-model';
import { LineModel } from 'src/app/model/line-model';
import Stepper from 'bs-stepper';
import { TransportTypeModel } from 'src/app/model/transport-type-model';

@Component({
  selector: 'app-add-line',
  templateUrl: './add-line.component.html'
})
export class AddLineComponent implements OnInit {

  line = {} as LineModel;
  selectAllStations: boolean
  stations: Array<StationModel>;
  stepper: Stepper;
  transportTypes: Array<TransportTypeModel>;
  selectedTransportType: TransportTypeModel;

  constructor(private lineService: LineService,
    private stationService: StationService,
    private router: Router,
    private transportTypeService: TransportTypeService) { }

  ngOnInit() {
    this.stepper = new Stepper(document.querySelector('.bs-stepper'), {
      linear: false,
      animation: true
    });
    this.transportTypes = this.transportTypeService.getTransportTypes();
    this.selectedTransportType = this.transportTypes[0];
    this.stationService.getAllStations().subscribe(stations => this.stations = stations);
  }

  submit() {
    this.line.stations = this.stations.filter(station => station.selected);
    this.line.transportType = this.selectedTransportType;
    console.log("submitting zone");
    this.lineService.addLine(this.line).subscribe(result => {
      this.router.navigateByUrl("/admin/lines");
    });
  }

  cancel() {
    this.router.navigateByUrl("/admin/lines");
  }

  toggleAllStations() {
    this.stations.forEach(station => station.selected = this.selectAllStations);
  }

  next() {
    this.stepper.next();
  }

  prev() {
    this.stepper.previous();
  }

}
