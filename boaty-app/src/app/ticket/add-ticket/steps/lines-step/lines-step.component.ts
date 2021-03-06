import { map } from 'rxjs/operators';
import { ZoneSelectionService } from 'src/app/services/zone-selection.service';
import { AdditionalLinesStepComponent } from './../additional-lines-step/additional-lines-step.component';
import { TransportTypeSelectionService } from 'src/app/services/transport-type-selection.service';
import { LineService } from 'src/app/services/line.service';
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
    this.lineService.getLines().subscribe(allLines => this.allLines = allLines);
  }

  ngOnChanges() {
    if (this.shouldFilterLines) {
      this.lines = this.filterSelectedLines();
    }
  }

  getLines() {
    return this.lineService.getLines().subscribe(allLines => {
      this.allLines = allLines;
      return this.filterSelectedLines();
    });

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
      if (line.transportType.id === selectedTransportType.id) {
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
    let additionaLines = new Array<LineModel>();
    this.lineService.getLines().subscribe(lines => {
      lines.forEach(line => console.log(line.name));
    });
    this.lineService.getLines().subscribe(lines => {
      lines.map(line => additionaLines.push(new LineModel(line.id, line.name, line.stations, false, line.transportType)));
      let ref = this.modalService.open(AdditionalLinesStepComponent);
      ref.componentInstance.lines = additionaLines;
    })
  }
}
