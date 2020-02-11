import { ZoneSelectionService } from './../../../services/zone-selection.service';
import { AdditionalLinesStepComponent } from './../additional-lines-step/additional-lines-step.component';
import { TransportTypeSelectionService } from './../../../services/transport-type-selection.service';
import { LineService } from './../../../services/line.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { LineModel } from 'src/app/model/line-model';
import { TransportTypeModel } from 'src/app/model/transport-type-model';
import { NgbModal, NgbModalOptions } from '@ng-bootstrap/ng-bootstrap';
import { ZoneModel } from 'src/app/model/zone-model';
import { LineSelectionService } from 'src/app/services/line-selection.service';

@Component({
  selector: 'lines-step',
  templateUrl: './lines-step.component.html',
  styleUrls: ['./lines-step.component.css']
})
export class LinesStepComponent implements OnInit {

  allLines: Array<LineModel>;
  lines: Array<LineModel>;
  selectedTransportTypes: Array<TransportTypeModel>;
  selectedZones: Set<ZoneModel>;
  modalOptions: NgbModalOptions;

  @Output()
  next = new EventEmitter();

  @Output()
  prev = new EventEmitter();

  @Input()
  ticket: any;

  @Input()
  shouldFilterLines: boolean;

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
    this.lines = new Array<LineModel>();
    this.transportTypeSelectionService.currentTransportTypes
      .subscribe(selectedTransportTypes => this.selectedTransportTypes = selectedTransportTypes);
    this.zoneSelectionService.currentZones
      .subscribe(selectedZones => this.selectedZones = selectedZones);
    this.allLines = this.lineService.getLines();
  }

  ngOnChanges(){
    if(this.shouldFilterLines){
      this.lines = this.filterSelectedLines();
    }
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
      line.selected = true; return line;
    });
  }

  matchesSelectedTransportType(line: LineModel) {
    for (let selectedTransportType of this.selectedTransportTypes) {
      if (line.transportType === selectedTransportType.id) {
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
    let linesToSelect = this.lines.filter(line => line.selected);
    this.lineSelectionService.setSelectedLines(new Set(linesToSelect));
    this.next.emit();
  }

  open() {
    let ref = this.modalService.open(AdditionalLinesStepComponent);
    let additionaLines = new Array<LineModel>();
    this.lineService.getLines().map(line => additionaLines.push(new LineModel(line.id, line.name, line.stations, false, line.transportType)));
    ref.componentInstance.lines = additionaLines;
  }
}
