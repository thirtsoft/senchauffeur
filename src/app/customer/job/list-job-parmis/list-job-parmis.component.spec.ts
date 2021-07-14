import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListJobParmisComponent } from './list-job-parmis.component';

describe('ListJobParmisComponent', () => {
  let component: ListJobParmisComponent;
  let fixture: ComponentFixture<ListJobParmisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListJobParmisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListJobParmisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
