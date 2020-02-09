import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { LineModel } from '../model/line-model';

@Injectable({
  providedIn: 'root'
})
export class LineSelectionService {

  private selectedLines = new BehaviorSubject(new Set<LineModel>());
  currentLines = this.selectedLines.asObservable();

  constructor() { }

  public setSelectedLines(lines: Set<LineModel>) {
    this.selectedLines.next(lines);
  }

  public addSelectedLines(lines: Set<LineModel>) {
    lines.forEach(station => this.selectedLines.getValue().add(station));
    this.selectedLines.next(this.selectedLines.getValue());
  }

  public removeStations(lines: Set<LineModel>) {
    let selectedLines = this.selectedLines.getValue();
    lines.forEach(station => {
      selectedLines.forEach(selectedLine => {
        if (selectedLine.id === station.id) {
          selectedLines.delete(selectedLine);
          return;
        }
      });
    });
    this.selectedLines.next(selectedLines);
  }
}
