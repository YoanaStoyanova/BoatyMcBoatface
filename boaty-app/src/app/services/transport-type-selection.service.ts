import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { TransportTypeModel } from '../model/transport-type-model';

@Injectable({
  providedIn: 'root'
})
export class TransportTypeSelectionService {

  private selectedTransportTypes = new BehaviorSubject(new Array<TransportTypeModel>());
  currentTransportTypes = this.selectedTransportTypes.asObservable();

  constructor() { }

  public setSelectedTransportTypes(transportTypes: Array<TransportTypeModel>) {
    console.log("Setting selected transport types");
    transportTypes.map(tType => console.log(tType.name));
    this.selectedTransportTypes.next(transportTypes);
  }

  public addSelectedTransportTypes(transportTypes: Array<TransportTypeModel>) {
    console.log("Adding selected transport types");
    transportTypes.map(tType => console.log(tType.name));
    this.selectedTransportTypes.next(this.selectedTransportTypes.getValue().concat(transportTypes));
  }
}
