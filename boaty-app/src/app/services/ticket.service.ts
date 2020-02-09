import { TicketModel } from './../model/ticket-model';
import { Injectable } from '@angular/core';
import { tick } from '@angular/core/src/render3';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor() { }

  public getAllTickets(){

  }

  public addTicket(ticket: TicketModel){
    console.log("Adding ticket");
    console.log("Ticket zones: ");
    ticket.zones.forEach( zone =>{
      console.log("Zone name: ", zone.name);
      console.log("Zone stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });

    console.log("-----------");
    console.log("Ticket lines: ");
    ticket.lines.forEach( zone =>{
      console.log("Line name: ", zone.name);
      console.log("Lines stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });

    console.log("-----------");
    console.log("Ticket additional lines: ");
    ticket.additionalLines.forEach( zone =>{
      console.log("Additional line name: ", zone.name);
      console.log("Additional line stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });
  }
}
