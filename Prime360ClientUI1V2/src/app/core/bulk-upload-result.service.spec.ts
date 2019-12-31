import { TestBed, inject } from '@angular/core/testing';

import { BulkUploadResultService } from './bulk-upload-result.service';

describe('BulkUploadResultService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BulkUploadResultService]
    });
  });

  it('should be created', inject([BulkUploadResultService], (service: BulkUploadResultService) => {
    expect(service).toBeTruthy();
  }));
});
