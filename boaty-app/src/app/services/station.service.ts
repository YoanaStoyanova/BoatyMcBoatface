import { environment } from './../../environments/environment';
import { StationModel } from './../model/station-model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StationService {

  constructor(private http: HttpClient) { }

  getAllStations() {
    return this.http.get<StationModel[]>(`${environment.baseUrl}/stations`);
  }

  addStation(station: StationModel) {
    let httpHeaders = new HttpHeaders();
    httpHeaders.append('Content-Type', 'application/json');
    return this.http.post(`${environment.baseUrl}/stations`, station, {headers: httpHeaders});
  }

  updateStation(station: StationModel) {
    return this.http.post(`${environment.baseUrl}/stations/${station.id}`, station);
  }

  deleteStation(station: StationModel) {
    return this.http.delete(`${environment.baseUrl}/stations/${station.id}`);
  }
}
