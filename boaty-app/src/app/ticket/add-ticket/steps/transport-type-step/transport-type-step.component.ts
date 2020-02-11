import { TransportTypeSelectionService } from 'src/app/services/transport-type-selection.service';
import { TransportTypeService } from 'src/app/services/transport-type.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { TransportTypeModel } from 'src/app/model/transport-type-model';

@Component({
  selector: 'transport-type-step',
  templateUrl: './transport-type-step.component.html',
  styleUrls: ['./transport-type-step.component.css']
})
export class TransportTypeStepComponent implements OnInit {

  transportTypes: Array<TransportTypeModel>;
  selectAllTransportTypes: boolean;

  @Output()
  next = new EventEmitter<string>();

  @Output()
  prev = new EventEmitter<string>();

  @Output()
  filterLines = new EventEmitter<string>();

  constructor(private transportTypeService: TransportTypeService,
    private transportTypeSelectionService: TransportTypeSelectionService) { }

  ngOnInit() {
    this.transportTypes = this.transportTypeService.getTransportTypes();
  }

  toggleAllTransportTypes() {
    this.transportTypes.map(transportType => transportType.selected = this.selectAllTransportTypes);
  }

  submit() {
    let selectedTransportTypes = this.transportTypes.filter(transportType => transportType.selected);
    this.transportTypeSelectionService.setSelectedTransportTypes(selectedTransportTypes);
    this.filterLines.emit();
    this.next.emit();
  }
}
