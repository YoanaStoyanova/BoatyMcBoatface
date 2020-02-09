import { AdditionalLineSelectionService } from './../services/additional-line-selection.service';
import { ZoneSelectionService } from './../services/zone-selection.service';
import { TicketService } from './../services/ticket.service';
import { TicketModel } from './../model/ticket-model';
import { Component, OnInit, Input } from '@angular/core';
import Stepper from 'bs-stepper';
import { StationModel } from '../model/station-model';
import { LineModel } from '../model/line-model';
import { LineSelectionService } from '../services/line-selection.service';

@Component({
  selector: 'add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {

  stepper: Stepper;
  lines: Array<LineModel>;

  @Input()
  ticket: TicketModel = {} as TicketModel;
  selectedStations: Map<number, StationModel>;
  selectedLines: Array<LineModel>;

  constructor(private ticketSvc: TicketService,
    private zoneSelectionSvc: ZoneSelectionService,
    private lineSelectionSvc: LineSelectionService,
    private additionaLineSelectionSvc: AdditionalLineSelectionService
    ) {
  }

  ngOnInit() {
    this.stepper = new Stepper(document.querySelector('.bs-stepper'), {
      linear: false,
      animation: true
    });
    this.zoneSelectionSvc.currentZones
    .subscribe(selectedZones => this.ticket.zones = selectedZones);
    this.lineSelectionSvc.currentLines
    .subscribe(selectedLines => this.ticket.lines = selectedLines);
    this.additionaLineSelectionSvc.currentAdditionalLines
    .subscribe(selectedAdditionalLines => this.ticket.additionalLines = selectedAdditionalLines);
  }

  prev() {
    this.stepper.previous();
  }

  next() {
    this.stepper.next();
  }

  onSubmit() {
    console.log("All selected zones");

    console.log(this.ticket.name + " " + this.ticket.minutesValidFor + " " + this.ticket.price);
    this.ticketSvc.addTicket(this.ticket);
    return false;
  }

  onCancel() {
    //redirect to view tickets or main page
  }

  toggleStations(selected: boolean, stations: Array<StationModel>) {
    if (selected) {
      stations.forEach(station => {
        this.selectedStations.set(station.id, station);
      });
    } else {
      stations.forEach(station => {
        this.selectedStations.delete(station.id);
      });
    }
  }

  toggleLine(line: LineModel) {
    line.selected = !line.selected;
  }

  getSelectedLines() {
    return new Array();
  }
}
