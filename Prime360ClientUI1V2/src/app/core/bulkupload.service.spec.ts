import { TestBed, inject } from '@angular/core/testing';

import { BulkuploadService } from './bulkupload.service';

describe('BulkuploadService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BulkuploadService]
    });
  });

  it('should be created', inject([BulkuploadService], (service: BulkuploadService) => {
    expect(service).toBeTruthy();
  }));
});
