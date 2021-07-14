import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListJobPermisComponent } from './list-job-permis.component';

describe('ListJobPermisComponent', () => {
  let component: ListJobPermisComponent;
  let fixture: ComponentFixture<ListJobPermisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListJobPermisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListJobPermisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
