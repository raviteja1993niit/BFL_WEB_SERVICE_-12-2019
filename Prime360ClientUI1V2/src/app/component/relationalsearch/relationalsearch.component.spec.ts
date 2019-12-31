import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RelationalsearchComponent } from './relationalsearch.component';

describe('RelationalsearchComponent', () => {
  let component: RelationalsearchComponent;
  let fixture: ComponentFixture<RelationalsearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RelationalsearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RelationalsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
