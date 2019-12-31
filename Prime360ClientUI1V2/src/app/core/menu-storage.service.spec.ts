import { TestBed, inject } from '@angular/core/testing';

import { MenuStorageService } from './menu-storage.service';

describe('MenuStorageService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MenuStorageService]
    });
  });

  it('should be created', inject([MenuStorageService], (service: MenuStorageService) => {
    expect(service).toBeTruthy();
  }));
});
