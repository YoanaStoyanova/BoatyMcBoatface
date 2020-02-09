import { TestBed } from '@angular/core/testing';

import { ZoneSelectionService } from './zone-selection.service';

describe('ZoneSelectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ZoneSelectionService = TestBed.get(ZoneSelectionService);
    expect(service).toBeTruthy();
  });
});
