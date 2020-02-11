import { TicketModel } from 'src/app/model/ticket-model';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private http: HttpClient) { }

  public getAllTickets() {
    return this.http.get<TicketModel[]>(`${environment.baseUrl}/tickets`);
  }

  public addTicket(ticket: TicketModel) {
    return this.http.post<TicketModel>(`${environment.baseUrl}/tickets`, ticket);
  }

  public deleteTicket(ticket: TicketModel){
    return this.http.delete<TicketModel>(`${environment.baseUrl}/tickets/${ticket.id}`);
  }
}
