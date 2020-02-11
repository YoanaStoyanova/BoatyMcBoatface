import { TicketService } from './../../services/ticket.service';
import { Component, OnInit } from '@angular/core';
import { TicketModel } from 'src/app/model/ticket-model';

@Component({
  selector: 'app-view-ticket',
  templateUrl: './view-ticket.component.html'
})
export class ViewTicketComponent implements OnInit {

  tickets: Array<TicketModel>;

  constructor(private ticketService: TicketService) { }

  ngOnInit() {
    this.ticketService.getAllTickets().subscribe(tickets =>{
      this.tickets = tickets;
      this.meth();
    });
  }

  meth(){
    this.tickets.forEach(ticket => console.log(ticket.name + " " + ticket.price));
  }

  delete(ticket: TicketModel) {
    this.ticketService.deleteTicket(ticket).subscribe(() => this.ngOnInit());
  }

}
