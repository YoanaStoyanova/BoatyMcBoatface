import { Router } from '@angular/router';
import { AdditionalLineSelectionService } from 'src/app/services/additional-line-selection.service';
import { ZoneSelectionService } from 'src/app/services/zone-selection.service';
import { TicketService } from 'src/app/services/ticket.service';
import { TicketModel } from 'src/app/model/ticket-model';
import { Component, OnInit, Input } from '@angular/core';
import Stepper from 'bs-stepper';
import { LineSelectionService } from 'src/app/services/line-selection.service';

@Component({
  selector: 'add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {

  @Input()
  ticket: TicketModel = {} as TicketModel;
  stepper: Stepper;
  shouldFilterLines: boolean;

  constructor(private ticketSvc: TicketService,
    private zoneSelectionSvc: ZoneSelectionService,
    private lineSelectionSvc: LineSelectionService,
    private additionaLineSelectionSvc: AdditionalLineSelectionService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.shouldFilterLines = false;
    this.stepper = new Stepper(document.querySelector('.bs-stepper'), {
      linear: false,
      animation: true
    });
    this.zoneSelectionSvc.currentZones
      .subscribe(selectedZones => this.ticket.zones = Array.from(selectedZones));
    this.lineSelectionSvc.currentLines
      .subscribe(selectedLines => this.ticket.lines = Array.from(selectedLines));
    this.additionaLineSelectionSvc.currentAdditionalLines
      .subscribe(selectedAdditionalLines => this.ticket.additionalLines = Array.from(selectedAdditionalLines));
  }

  prev() {
    this.shouldFilterLines = false;
    this.stepper.previous();
  }

  next() {
    this.stepper.next();
  }

  filterLines() {
    this.shouldFilterLines = true;
  }

  onSubmit() {
    this.ticketSvc.addTicket(this.ticket).subscribe(() => this.router.navigateByUrl("/tickets"));
    return false;
  }

  onCancel() {
    //redirect to view tickets or main page
  }
}
