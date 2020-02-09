import { StationModel } from './../model/station-model';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StationsSelectionService {

  private selectedStations = new BehaviorSubject(new Set<StationModel>());
  currentStations = this.selectedStations.asObservable();

  constructor() { }

  public setSelectedStations(stations: Set<StationModel>) {
    console.log("Setting selected stations");
    stations.forEach(station => console.log(station.name));
    this.selectedStations.next(stations);
  }

  public addSelectedStations(stations: Set<StationModel>) {
    console.log("Adding stations");
    stations.forEach(station => this.selectedStations.getValue().add(station));
    this.selectedStations.getValue().forEach(station => console.log(station.name));
    this.selectedStations.next(this.selectedStations.getValue());
  }

  public removeStations(stations: Set<StationModel>) {
    let selectedStations = this.selectedStations.getValue();
    stations.forEach(station => {
      selectedStations.forEach(selectedStation => {
        if (selectedStation.id === station.id) {
          selectedStations.delete(selectedStation);
          return;
        }
      });
    });
    this.selectedStations.next(selectedStations);
  }
}
