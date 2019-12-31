import { TestBed, inject } from '@angular/core/testing';

import { EoduploadserviceService } from './eoduploadservice.service';

describe('EoduploadserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EoduploadserviceService]
    });
  });

  it('should be created', inject([EoduploadserviceService], (service: EoduploadserviceService) => {
    expect(service).toBeTruthy();
  }));
});
