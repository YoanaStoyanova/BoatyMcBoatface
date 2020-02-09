import { TicketModel } from './../../../model/ticket-model';
import { ZoneSelectionService } from './../../../services/zone-selection.service';
import { AdditionalLinesStepComponent } from './../additional-lines-step/additional-lines-step.component';
import { TransportTypeSelectionService } from './../../../services/transport-type-selection.service';
import { LineService } from './../../../services/line.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { LineModel } from 'src/app/model/line-model';
import { TransportTypeModel } from 'src/app/model/transport-type-model';
import { NgbModal, ModalDismissReasons, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { ZoneModel } from 'src/app/model/zone-model';
import { LineSelectionService } from 'src/app/services/line-selection.service';

@Component({
  selector: 'lines-step',
  templateUrl: './lines-step.component.html',
  styleUrls: ['./lines-step.component.css']
})
export class LinesStepComponent implements OnInit {

  //lines: Array<LineModel>;
  allLines: Array<LineModel>;
  selectedTransportTypes: Array<TransportTypeModel>;
  selectedZones: Set<ZoneModel>;
  closeResult: string;
  modalOptions: NgbModalOptions;

  @Output()
  next = new EventEmitter();

  @Output()
  prev = new EventEmitter();

  @Input()
  ticket: any;

  constructor(private lineService: LineService,
    private transportTypeSelectionService: TransportTypeSelectionService,
    private zoneSelectionService: ZoneSelectionService,
    private lineSelectionService: LineSelectionService,
    private modalService: NgbModal) {
    this.modalOptions = {
      backdrop: 'static',
      backdropClass: 'customBackdrop'
    };
  }

  ngOnInit() {
    this.transportTypeSelectionService.currentTransportTypes
      .subscribe(selectedTransportTypes => this.selectedTransportTypes = selectedTransportTypes);
    this.zoneSelectionService.currentZones
      .subscribe(selectedZones => this.selectedZones = selectedZones);
  //  this.allLines = this.getLines();
    this.allLines = this.lineService.getLines();
  }

  getLines() {
    this.allLines = this.lineService.getLines();
    return this.filterSelectedLines();
  }

  filterSelectedLines() {
    return this.allLines.filter(line => {
      return (this.matchesSelectedTransportType(line)
        && this.matchesSelectedStation(line));
    }).map(line => {
      console.log("Found matching lines");
      line.selected = true; return line;
    });
  }

  matchesSelectedTransportType(line: LineModel) {
    for (let selectedTransportType of this.selectedTransportTypes) {
      console.log("Selected tr type " + selectedTransportType.id);
      if (line.transportType === selectedTransportType.id) {
        console.log("found matching line " + line.transportType);
        return true;
      }
    }
    return false;
  }

  matchesSelectedStation(line: LineModel) {
    let selectedStationIds = new Set<number>();

    this.selectedZones.forEach(selectedZone => {
      selectedZone.stations.map(station => {
        selectedStationIds.add(station.id)
      });
    });

    for (let lineStation of line.stations) {
      if (selectedStationIds.has(lineStation.id)) {
        return true;
      }
    }
    return false;
  }

  submit() {
    let linesToSelect = this.allLines.filter(line => line.selected);
    this.lineSelectionService.setSelectedLines(new Set(linesToSelect));
    this.next.emit();
  }

  open() {
    let ref = this.modalService.open(AdditionalLinesStepComponent);
    let additionaLines = new Array<LineModel>();
    this.lineService.getLines().map(line => additionaLines.push(new LineModel(line.id, line.name, line.stations, false, line.transportType)));
    ref.componentInstance.lines = additionaLines;
    // this.modalService.open(AdditionalLinesStepComponent, this.modalOptions).result.then((result) => {
    //   this.closeResult = `Closed with: ${result}`;
    // }, (reason) => {
    //   this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    // });
  }

}
