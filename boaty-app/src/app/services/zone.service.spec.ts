import { TestBed } from '@angular/core/testing';

import { ZoneService } from './zone.service';

describe('ZonesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ZoneService = TestBed.get(ZoneService);
    expect(service).toBeTruthy();
  });
});
