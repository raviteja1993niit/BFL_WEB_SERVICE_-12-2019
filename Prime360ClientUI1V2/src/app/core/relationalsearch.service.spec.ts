import { TestBed, inject } from '@angular/core/testing';

import { RelationalsearchService } from './relationalsearch.service';

describe('RelationalsearchService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RelationalsearchService]
    });
  });

  it('should be created', inject([RelationalsearchService], (service: RelationalsearchService) => {
    expect(service).toBeTruthy();
  }));
});
