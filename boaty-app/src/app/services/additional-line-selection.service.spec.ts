import { TestBed } from '@angular/core/testing';

import { AdditionalLineSelectionService } from './additional-line-selection.service';

describe('AdditionalLineSelectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdditionalLineSelectionService = TestBed.get(AdditionalLineSelectionService);
    expect(service).toBeTruthy();
  });
});
