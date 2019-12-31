import { TestBed, inject } from '@angular/core/testing';

import { EodstagesService } from './Eodstages.service';

describe('EodstagesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EodstagesService]
    });
  });

  it('should be created', inject([EodstagesService], (service: EodstagesService) => {
    expect(service).toBeTruthy();
  }));
});
