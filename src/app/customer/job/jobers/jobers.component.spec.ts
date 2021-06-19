import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobersComponent } from './jobers.component';

describe('JobersComponent', () => {
  let component: JobersComponent;
  let fixture: ComponentFixture<JobersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
