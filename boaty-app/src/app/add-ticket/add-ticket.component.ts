import { AdditionalLineSelectionService } from './../services/additional-line-selection.service';
import { ZoneSelectionService } from './../services/zone-selection.service';
import { TicketService } from './../services/ticket.service';
import { TicketModel } from './../model/ticket-model';
import { Component, OnInit, Input } from '@angular/core';
import Stepper from 'bs-stepper';
import { LineSelectionService } from '../services/line-selection.service';

@Component({
  selector: 'add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {

  @Input()
  ticket: TicketModel = {} as TicketModel;
  stepper: Stepper;

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
    this.ticketSvc.addTicket(this.ticket);
    return false;
  }

  onCancel() {
    //redirect to view tickets or main page
  }
}
