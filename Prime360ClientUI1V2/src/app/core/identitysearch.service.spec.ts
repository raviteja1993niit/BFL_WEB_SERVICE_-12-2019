import { TestBed, inject } from '@angular/core/testing';

import { IdentitysearchService } from './identitysearch.service';

describe('IdentitysearchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IdentitysearchService]
    });
  });

  it('should be created', inject([IdentitysearchService], (service: IdentitysearchService) => {
    expect(service).toBeTruthy();
  }));
});
