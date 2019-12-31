import { TestBed, inject } from '@angular/core/testing';

import { NegativeuploadserviceService } from './negativeuploadservice.service';

describe('NegativeuploadserviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NegativeuploadserviceService]
    });
  });

  it('should be created', inject([NegativeuploadserviceService], (service: NegativeuploadserviceService) => {
    expect(service).toBeTruthy();
  }));
});
