import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NegativeuploadComponent } from './negativeupload.component';

describe('NegativeuploadComponent', () => {
  let component: NegativeuploadComponent;
  let fixture: ComponentFixture<NegativeuploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NegativeuploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NegativeuploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
