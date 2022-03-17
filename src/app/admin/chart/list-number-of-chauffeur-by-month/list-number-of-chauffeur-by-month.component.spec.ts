import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNumberOfChauffeurByMonthComponent } from './list-number-of-chauffeur-by-month.component';

describe('ListNumberOfChauffeurByMonthComponent', () => {
  let component: ListNumberOfChauffeurByMonthComponent;
  let fixture: ComponentFixture<ListNumberOfChauffeurByMonthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNumberOfChauffeurByMonthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNumberOfChauffeurByMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
