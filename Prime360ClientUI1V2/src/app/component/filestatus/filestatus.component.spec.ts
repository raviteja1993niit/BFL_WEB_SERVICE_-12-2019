import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilestatusComponent } from './filestatus.component';

describe('FilestatusComponent', () => {
  let component: FilestatusComponent;
  let fixture: ComponentFixture<FilestatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilestatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilestatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
