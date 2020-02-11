import { Observable } from 'rxjs';
import { StationService } from './../../services/station.service';
import { Component, OnInit } from '@angular/core';
import { StationModel } from 'src/app/model/station-model';

@Component({
  selector: 'app-view-stations',
  templateUrl: './view-stations.component.html',
  styleUrls: ['./view-stations.component.css']
})
export class ViewStationsComponent implements OnInit {

  stations: Observable<StationModel[]>;
  edit: boolean
  editIndex: number;
  newStationName: string;

  constructor(private stationService: StationService) { }

  ngOnInit() {
    this.editIndex = -1;
    this.edit = false;
    this.stations = this.stationService.getAllStations();
  }

  triggerEdit(station: StationModel, i: number) {
    this.newStationName = station.name;
    this.edit = true;
    this.editIndex = i;
  }

  submitEdit(station: StationModel) {
    station.name = this.newStationName;
    this.newStationName = '';
    this.edit = false;
    this.editIndex = -1;
    this.stationService.updateStation(station).subscribe(result =>{
      this.ngOnInit();
    });
  }

  cancelEdit() {
    this.newStationName = '';
    this.edit = false;
    this.editIndex = -1;
  }

  delete(station: StationModel) {
    this.stationService.deleteStation(station).subscribe(result => {
      this.ngOnInit();
    });
  }
}
