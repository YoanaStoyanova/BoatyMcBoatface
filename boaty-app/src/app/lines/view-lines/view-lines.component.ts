import { StationService } from './../../services/station.service';
import { LineService } from './../../services/line.service';
import { Component, OnInit } from '@angular/core';
import { LineModel } from 'src/app/model/line-model';
import { StationModel } from 'src/app/model/station-model';

@Component({
  selector: 'app-view-lines',
  templateUrl: './view-lines.component.html'
})
export class ViewLinesComponent implements OnInit {

  edit: boolean
  editIndex: number;
  newLineName: string;
  selectAllStations: boolean;
  lines: Array<LineModel>;
  stations: Array<StationModel>;
  toggledStations: Map<number, StationModel>;

  constructor(private lineService: LineService,
    private stationService: StationService) { }

  ngOnInit() {
    this.toggledStations = new Map();
    this.edit = false;
    this.editIndex = -1;
    this.newLineName = '';
    this.selectAllStations = false;
    this.lineService.getLines().subscribe(lines => {
      this.lines = lines;
      this.stationService.getAllStations().subscribe(stations => {
        this.stations = stations;
        this.matchLinesToStations();
      });
    });

  }

  triggerEdit(line: LineModel, i: number) {
    this.toggledStations.forEach((station: StationModel, key: number) => {
      station.selected = !station.selected;
    });
    this.toggledStations = new Map();
    this.selectAllStations = false;
    this.newLineName = line.name;
    this.edit = true;
    this.editIndex = i;
  }

  cancelEdit() {
    this.edit = false;
    this.editIndex = -1;
    this.newLineName = '';
    this.toggledStations.forEach((station: StationModel, key: number) => {
      station.selected = !station.selected;
    });
    this.toggledStations = new Map();
  }

  submitEdit(line: LineModel) {
    line.name = this.newLineName;
    this.newLineName = '';
    this.edit = false;
    this.editIndex = -1;
    this.selectAllStations = false;
    line.stations = line.stations.filter(station => station.selected);
    this.lineService.updateLine(line).subscribe(() => this.ngOnInit());
  }

  delete(line: LineModel) {
    this.lineService.deleteLine(line).subscribe(() => {
      this.ngOnInit();
    })
  }

  shouldShow(line: LineModel, i: number) {
    if (this.edit && this.editIndex === i) {
      return 'show';
    } else {
      return '';
    }
  }

  getStations(line: LineModel) {
    let lineStationIds = new Set(line.stations.map(station => station.id));
    this.stations.map(station => {
      if (lineStationIds.has(station.id)) {
        station.selected = true;
      } else {
        station.selected = false;
      }
    });
    return this.stations;
  }

  matchLinesToStations() {
    for (let line of this.lines) {
      let lineStationsIds = new Set(line.stations.map(station => station.id));
      let lineStations = new Array<StationModel>();
      this.stations.map(station => {
        let selected = lineStationsIds.has(station.id);
        lineStations.push(new StationModel(station.id, station.name, station.zoneId, station.zoneName, selected))
      });
      line.stations = lineStations;
    }
  }

  toggleAllStations(stations: Array<StationModel>) {
    stations.map(station => station.selected = this.selectAllStations);
  }

  toggleStation(station: StationModel) {
    if (this.toggledStations.get(station.id)) {
      this.toggledStations.delete(station.id);
    } else {
      this.toggledStations.set(station.id, station);
    }
  }


}
