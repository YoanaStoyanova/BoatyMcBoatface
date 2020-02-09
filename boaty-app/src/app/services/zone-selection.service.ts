import { ZoneModel } from 'src/app/model/zone-model';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ZoneSelectionService {

  private selectedZones = new BehaviorSubject(new Set<ZoneModel>());
  currentZones = this.selectedZones.asObservable();

  constructor() { }

  public setSelectedZones(zones: Set<ZoneModel>) {
    console.log("Setting selected stations");
    zones.forEach(station => console.log(station.name));
    this.selectedZones.next(zones);
  }

  public addSelectedZones(zones: Set<ZoneModel>) {
    console.log("Adding stations");
    zones.forEach(station => this.selectedZones.getValue().add(station));
    this.selectedZones.getValue().forEach(station => console.log(station.name));
    this.selectedZones.next(this.selectedZones.getValue());
  }

  public removeStations(zones: Set<ZoneModel>) {
    let selectedZones = this.selectedZones.getValue();
    zones.forEach(station => {
      selectedZones.forEach(selectedZone => {
        if (selectedZone.id === station.id) {
          selectedZones.delete(selectedZone);
          return;
        }
      });
    });
    this.selectedZones.next(selectedZones);
  }
}
