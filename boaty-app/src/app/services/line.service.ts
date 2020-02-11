import { environment } from './../../environments/environment.prod';
import { HttpClient } from '@angular/common/http';
import { LineModel } from 'src/app/model/line-model';
import { TransportTypeEnum, TransportTypeModel } from './../model/transport-type-model';
import { StationModel } from './../model/station-model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LineService {

  constructor(private http: HttpClient) { }

  public getLines() {
    return this.http.get<LineModel[]>(`${environment.baseUrl}/lines`);
  }

  public addLine(line: LineModel) {
    return this.http.post<LineModel>(`${environment.baseUrl}/lines`, line);
  }

  public updateLine(line: LineModel) {
    return this.http.post<LineModel>(`${environment.baseUrl}/lines/${line.id}`, line);
  }

  public deleteLine(line: LineModel) {
    return this.http.delete<LineModel>(`${environment.baseUrl}/lines/${line.id}`);
  }
}
