import { LineModel } from 'src/app/model/line-model';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdditionalLineSelectionService {

    private selectedAdditionalLines = new BehaviorSubject(new Set<LineModel>());
  currentAdditionalLines = this.selectedAdditionalLines.asObservable();

  constructor() { }

  public setSelectedLines(lines: Set<LineModel>) {
    this.selectedAdditionalLines.next(lines);
  }

  public addSelectedLines(lines: Set<LineModel>) {
    lines.forEach(station => this.selectedAdditionalLines.getValue().add(station));
    this.selectedAdditionalLines.getValue().forEach(station => console.log(station.name));
    this.selectedAdditionalLines.next(this.selectedAdditionalLines.getValue());
  }

  public removeStations(lines: Set<LineModel>) {
    let selectedLines = this.selectedAdditionalLines.getValue();
    lines.forEach(station => {
      selectedLines.forEach(selectedLine => {
        if (selectedLine.id === station.id) {
          selectedLines.delete(selectedLine);
          return;
        }
      });
    });
    this.selectedAdditionalLines.next(selectedLines);
  }
}
