import { LineModel } from 'src/app/model/line-model';
import { TransportTypeEnum } from './../model/transport-type-model';
import { StationModel } from './../model/station-model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LineService {

  lines = [
    { "id": 1, "name": "Line 1", "stations": [new StationModel(1, "station 1", null, null)], "selected": false, transportType: TransportTypeEnum.BUS },
    { "id": 2, "name": "Line 2", "stations": new Array<StationModel>(), "selected": false, transportType: TransportTypeEnum.BUS },
    { "id": 3, "name": "Line 3", "stations": new Array<StationModel>(), "selected": false, transportType: TransportTypeEnum.BUS },
    { "id": 4, "name": "Line 4", "stations": new Array<StationModel>(), "selected": false, transportType: TransportTypeEnum.BUS },
    { "id": 5, "name": "Line 5", "stations": new Array<StationModel>(), "selected": false, transportType: TransportTypeEnum.BUS },
    { "id": 6, "name": "Line 6", "stations": new Array<StationModel>(), "selected": false, transportType: TransportTypeEnum.BUS }
  ]

  constructor() { }

  public getLines() {
   let res = new Array<LineModel>();
    this.lines.forEach(line => res.push(new LineModel(line.id, line.name, line.stations, line.selected, line.transportType)));
    return res;
  }
}
