import { AdditionalLineSelectionService } from 'src/app/services/additional-line-selection.service';
import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { LineModel } from 'src/app/model/line-model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'additional-lines-step',
  templateUrl: './additional-lines-step.component.html',
  styleUrls: ['./additional-lines-step.component.css']
})
export class AdditionalLinesStepComponent implements OnInit {

  @Input()
  public lines: Array<LineModel>;
  
  selectAllLines: boolean;

  @Output()
  next = new EventEmitter();
  prev = new EventEmitter();

  constructor(private additionalLineSelectionService: AdditionalLineSelectionService,
    public activeModal: NgbActiveModal) { }

  ngOnInit() {
    this.selectAllLines = false;
  }

  toggleAllLines() {
    this.lines.map(line => line.selected = this.selectAllLines);
  }

  submit() {
    let selectedAdditionalLines = this.lines.filter(line => line.selected);
    this.additionalLineSelectionService.setSelectedLines(new Set(selectedAdditionalLines));
    this.activeModal.close('submitted lines');
  }

  cancel() {
    this.activeModal.close('cancelled');
  }
}
