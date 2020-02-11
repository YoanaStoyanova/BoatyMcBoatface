import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { StationService } from './../../services/station.service';
import { Observable } from 'rxjs';
import { ZoneModel } from './../../model/zone-model';
import { ZoneService } from './../../services/zone.service';
import { Component, OnInit } from '@angular/core';
import { StationModel } from 'src/app/model/station-model';

@Component({
  selector: 'app-view-zones',
  templateUrl: './view-zones.component.html',
  styleUrls: ['./view-zones.component.css']
})
export class ViewZonesComponent implements OnInit {

  edit: boolean;
  editIndex: number
  newZoneName: string
  toggledStations: Map<number, StationModel>;
  zones: Array<ZoneModel>;
  stations: Array<StationModel>;
  selectAllStations: boolean;

  constructor(private zoneService: ZoneService,
    private stationService: StationService,
    private router: Router) { }

  ngOnInit() {
    this.toggledStations = new Map();
    this.edit = false;
    this.editIndex = -1;
    this.newZoneName = '';
    this.stationService.getAllStations().subscribe(stations => {
      this.stations = stations;
      this.zoneService.getZones().pipe(map(zones => zones.map(zone => {
        zone.stations = this.filterStations(zone).map(station => {
          if (station.zoneId === zone.id) {
            station.selected = true;
          }
          return station;
        });
        return zone;
      }))).subscribe(zones => this.zones = zones);
    });
  }

  triggerEdit(zone: ZoneModel, i: number) {
    this.selectAllStations = false;
    this.newZoneName = zone.name;
    this.edit = true;
    this.editIndex = i;
    this.toggledStations.forEach((station: StationModel, key: number) => {
      station.selected = !station.selected;
    });
    this.toggledStations = new Map();
  }

  submitEdit(zone: ZoneModel) {
    zone.name = this.newZoneName;
    this.newZoneName = '';
    this.edit = false;
    this.editIndex = -1;
    this.selectAllStations = false;
    zone.stations = zone.stations.filter(station => station.selected);
    this.zoneService.updateZone(zone).subscribe(res => {
      this.ngOnInit();
    });
  }

  cancelEdit() {
    this.newZoneName = '';
    this.edit = false;
    this.editIndex = -1;
    this.selectAllStations = false;
    this.toggledStations.forEach((station: StationModel, key: number) => {
      station.selected = !station.selected;
    });
    this.toggledStations = new Map();
  }

  delete(zone: ZoneModel) {
    this.zoneService.deleteZone(zone).subscribe(() => this.ngOnInit());
  }

  shouldShow(zone: ZoneModel, i: number) {
    if (this.edit && this.editIndex === i) {
      return 'show';
    } else {
      return '';
    }
  }

  filterStations(zone: ZoneModel) {
    let filtered = this.stations.filter(station => station.zoneId === null || station.zoneId === zone.id);
    return filtered;
  }

  toggleAllStations(stations: Array<StationModel>) {
    stations.map(station => {
      station.selected = this.selectAllStations;
    })
  }

  getStations(zone: ZoneModel) {
    return zone.stations;
  }

  toggleStation(station: StationModel) {
    if (this.toggledStations.get(station.id)) {
      this.toggledStations.delete(station.id);
    } else {
      this.toggledStations.set(station.id, station);
    }
  }

}
