import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EodFilestatusComponent } from './Eodfilestatus.component';

describe('EodFilestatusComponent', () => {
  let component: EodFilestatusComponent;
  let fixture: ComponentFixture<EodFilestatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EodFilestatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EodFilestatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
