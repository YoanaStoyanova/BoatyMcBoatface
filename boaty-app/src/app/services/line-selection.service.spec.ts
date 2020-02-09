import { TestBed } from '@angular/core/testing';

import { LineSelectionService } from './line-selection.service';

describe('LineSelectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LineSelectionService = TestBed.get(LineSelectionService);
    expect(service).toBeTruthy();
  });
});
