import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulkuploadresultComponent } from './bulkuploadresult.component';

describe('BulkuploadresultComponent', () => {
  let component: BulkuploadresultComponent;
  let fixture: ComponentFixture<BulkuploadresultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulkuploadresultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulkuploadresultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
