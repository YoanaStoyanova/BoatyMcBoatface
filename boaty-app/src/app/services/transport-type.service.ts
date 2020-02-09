import { Injectable } from '@angular/core';
import { TransportTypeEnum } from '../model/transport-type-model';

@Injectable({
  providedIn: 'root'
})
export class TransportTypeService {

  transportTypes = [
    { id: TransportTypeEnum.BUS, name: "Bus", icon: "fa-bus", selected: false },
    { id: TransportTypeEnum.SUBWAY, name: "Subway", icon: "fa-subway", selected: false },
    { id: TransportTypeEnum.TRAIN, name: "Train", icon: "fa-train", selected: false },
    { id: TransportTypeEnum.TRAM, name: "Tram", icon: "fa-tram", selected: false },
    { id: TransportTypeEnum.SHUTTLE_VAN, name: "Shuttle van", icon: "fa-shuttle-van", selected: false }
  ]

  constructor() { }

  public getTransportTypes() {
    return this.transportTypes;
  }


}
