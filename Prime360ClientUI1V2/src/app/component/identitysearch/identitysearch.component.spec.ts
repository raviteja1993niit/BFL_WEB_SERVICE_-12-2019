import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IdentitysearchComponent } from './identitysearch.component';

describe('IdentitysearchComponent', () => {
  let component: IdentitysearchComponent;
  let fixture: ComponentFixture<IdentitysearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IdentitysearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IdentitysearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
