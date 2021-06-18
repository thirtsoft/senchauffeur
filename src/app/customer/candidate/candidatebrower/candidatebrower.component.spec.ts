import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidatebrowerComponent } from './candidatebrower.component';

describe('CandidatebrowerComponent', () => {
  let component: CandidatebrowerComponent;
  let fixture: ComponentFixture<CandidatebrowerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidatebrowerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidatebrowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
