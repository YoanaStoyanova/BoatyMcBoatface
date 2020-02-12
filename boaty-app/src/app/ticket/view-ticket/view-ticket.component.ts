import { TicketService } from './../../services/ticket.service';
import { Component, OnInit } from '@angular/core';
import { TicketModel } from 'src/app/model/ticket-model';
import {UserService} from "../../services/user.service";
import {Observable} from "rxjs";
import {UserModel} from "../../model/user-model";

@Component({
  selector: 'app-view-ticket',
  templateUrl: './view-ticket.component.html'
})
export class ViewTicketComponent implements OnInit {

  currentUser :Observable<UserModel>;
  tickets: Array<TicketModel>;

  constructor(private ticketService: TicketService,
              private userService :UserService) { }

  ngOnInit() {
    this.ticketService.getAllTickets().subscribe(tickets =>{
      this.tickets = tickets;
      this.meth();
    });
    this.currentUser = this.userService.getCurrent();
  }

  meth(){
    this.tickets.forEach(ticket => console.log(ticket.name + " " + ticket.price));
  }

  delete(ticket: TicketModel) {
    this.ticketService.deleteTicket(ticket).subscribe(() => this.ngOnInit());
  }

  purchaseTicket(ticket :TicketModel) {
    this.ticketService.buyTicket(ticket).subscribe();
  }

}
