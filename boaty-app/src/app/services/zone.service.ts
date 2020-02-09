import { StationModel } from './../model/station-model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ZoneService {

  zones = [
    {"id": 1, "name" : "Zone 1", "stations": [new StationModel(1, "station 1", null)], "selected": false},
    {"id": 2, "name" : "Zone 2", "stations": new Array<StationModel>(), "selected": false},
    {"id": 3, "name" : "Zone 3", "stations": new Array<StationModel>(), "selected": false},
    {"id": 4, "name" : "Zone 4", "stations": new Array<StationModel>(), "selected": false},
    {"id": 5, "name" : "Zone 5", "stations": new Array<StationModel>(), "selected": false},
    {"id": 6, "name" : "Zone 6", "stations": new Array<StationModel>(), "selected": false},
  ]

  constructor() { }

  public getZones(){
    return this.zones;
  }
}
