import { ZoneSelectionService } from './../../../services/zone-selection.service';
import { ZoneModel } from './../../../model/zone-model';
import { ZoneService } from './../../../services/zone.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { StationModel } from 'src/app/model/station-model';

@Component({
  selector: 'zones-step',
  templateUrl: './zones-step.component.html',
  styleUrls: ['./zones-step.component.css']
})
export class ZonesStepComponent implements OnInit {

  @Input()
  zones: Array<ZoneModel>;
  selectAllZones: boolean;

  @Output()
  next = new EventEmitter<string>();

  @Output()
  prev = new EventEmitter<string>();

  constructor(private zoneService: ZoneService,
    private zoneSelectionService: ZoneSelectionService) { }

  ngOnInit() {
    this.zoneService.getZones().subscribe(zones => this.zones = zones);
  }

  toggleAllZones() {
    this.zones.map(zone => {
      zone.selected = this.selectAllZones;
    })
  }

  submit() {
    let selectedZones = this.zones.filter(zone => zone.selected);
    let selectedStations = new Array<StationModel>();
    selectedZones.map(zone => {
      selectedStations = selectedStations.concat(zone.stations);
    });
    this.zoneSelectionService.setSelectedZones(new Set(selectedZones));
    this.next.emit();
  }

}
