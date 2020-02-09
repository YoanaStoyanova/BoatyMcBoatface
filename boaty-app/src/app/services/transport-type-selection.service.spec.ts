import { TestBed } from '@angular/core/testing';

import { TransportTypeSelectionService } from './transport-type-selection.service';

describe('TransportTypeSelectionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TransportTypeSelectionService = TestBed.get(TransportTypeSelectionService);
    expect(service).toBeTruthy();
  });
});
