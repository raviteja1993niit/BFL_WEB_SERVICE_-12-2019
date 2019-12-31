import { TestBed, inject } from '@angular/core/testing';

import { CustomersearchService } from './customersearch.service';

describe('CustomersearchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CustomersearchService]
    });
  });

  it('should be created', inject([CustomersearchService], (service: CustomersearchService) => {
    expect(service).toBeTruthy();
  }));
});
