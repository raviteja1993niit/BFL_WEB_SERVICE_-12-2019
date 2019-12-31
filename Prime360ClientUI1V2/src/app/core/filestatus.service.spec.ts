import { TestBed, inject } from '@angular/core/testing';

import { FilestatusService } from './filestatus.service';

describe('FilestatusService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FilestatusService]
    });
  });

  it('should be created', inject([FilestatusService], (service: FilestatusService) => {
    expect(service).toBeTruthy();
  }));
});
