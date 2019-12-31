import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EodstagesComponent } from './Eodstages.component';

describe('EodStagesComponent', () => {
  let component: EodstagesComponent;
  let fixture: ComponentFixture<EodstagesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EodstagesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EodstagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
