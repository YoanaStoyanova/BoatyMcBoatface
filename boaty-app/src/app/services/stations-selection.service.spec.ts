import { TestBed } from '@angular/core/testing';

import { StationsSelectionService } from './stations-selection.service';

describe('StationsSelectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: StationsSelectionService = TestBed.get(StationsSelectionService);
    expect(service).toBeTruthy();
  });
});
