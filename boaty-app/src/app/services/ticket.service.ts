import { environment } from './../../environments/environment';
import { TicketModel } from './../model/ticket-model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http: HttpClient) { }

  public getAllTickets() {
    this.http.get(`${environment.baseUrl}/tickets`);
  }

  public addTicket(ticket: TicketModel) {
    console.log("Adding ticket");
    console.log("Ticket zones: ");
    ticket.zones.forEach(zone => {
      console.log("Zone name: ", zone.name);
      console.log("Zone stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });

    console.log("-----------");
    console.log("Ticket lines: ");
    ticket.lines.forEach(zone => {
      console.log("Line name: ", zone.name);
      console.log("Lines stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });

    console.log("-----------");
    console.log("Ticket additional lines: ");
    ticket.additionalLines.forEach(zone => {
      console.log("Additional line name: ", zone.name);
      console.log("Additional line stations");
      zone.stations.forEach(st => console.log("Station name " + st.name))
    });
    return this.http.post<TicketModel>(`${environment.baseUrl}/tickets`, ticket);
  }
}
