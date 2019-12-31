import { TestBed, inject } from '@angular/core/testing';

import { EodFilestatusService } from './Eodfilestatus.service';

describe('EodFilestatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EodFilestatusService]
    });
  });

  it('should be created', inject([EodFilestatusService], (service: EodFilestatusService) => {
    expect(service).toBeTruthy();
  }));
});
