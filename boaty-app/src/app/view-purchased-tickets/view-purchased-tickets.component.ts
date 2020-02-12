import { Component, OnInit } from '@angular/core';
import {TicketService} from "../services/ticket.service";
import {TicketModel} from "../model/ticket-model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-view-purchased-tickets',
  templateUrl: './view-purchased-tickets.component.html',
  styleUrls: ['./view-purchased-tickets.component.css']
})
export class ViewPurchasedTicketsComponent implements OnInit {

  tickets :TicketModel[];

  constructor(private ticketService :TicketService,
              private userService :UserService) { }

  ngOnInit() {
    this.userService.getCurrent().subscribe(user => {
      this.ticketService.viewPurchasedTickets(user.id).subscribe(tickets => this.tickets = tickets);
    })
  }

}
