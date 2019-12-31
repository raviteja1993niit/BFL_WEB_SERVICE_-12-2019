import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EoduploadComponent } from './eodupload.component';

describe('EoduploadComponent', () => {
  let component: EoduploadComponent;
  let fixture: ComponentFixture<EoduploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EoduploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EoduploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
