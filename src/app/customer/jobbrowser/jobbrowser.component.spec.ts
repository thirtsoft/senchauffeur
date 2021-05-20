import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobbrowserComponent } from './jobbrowser.component';

describe('JobbrowserComponent', () => {
  let component: JobbrowserComponent;
  let fixture: ComponentFixture<JobbrowserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobbrowserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobbrowserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
