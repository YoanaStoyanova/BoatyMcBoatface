import { Router } from '@angular/router';
import { StationModel } from './../../model/station-model';
import { StationService } from './../../services/station.service';
import { ZoneService } from './../../services/zone.service';
import { ZoneModel } from './../../model/zone-model';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-add-zone',
  templateUrl: './add-zone.component.html',
  styleUrls: ['./add-zone.component.css']
})
export class AddZoneComponent implements OnInit {

  zone = {} as ZoneModel;
  stations: StationModel[];
  selectAllStations: boolean;

  constructor(private zoneService: ZoneService,
    private stationService: StationService,
    private router: Router) { }

  ngOnInit() {
    this.selectAllStations = false;
    this.stationService.getAllStations()
      .pipe(map(stations => stations.filter(station => station.zoneId === null))).subscribe(stations =>
        this.stations = stations);
  }

  submit() {
    this.zone.stations = this.stations.filter(station => station.selected);
    console.log("submitting zone");
    this.zoneService.addZone(this.zone).subscribe(result => {
      this.router.navigateByUrl("/admin/zones");
    });
  }

  cancel() {
    this.router.navigateByUrl("/admin/zones");
  }

  toggleAllStations() {
    this.stations.forEach(station => station.selected = this.selectAllStations);
  }
}
