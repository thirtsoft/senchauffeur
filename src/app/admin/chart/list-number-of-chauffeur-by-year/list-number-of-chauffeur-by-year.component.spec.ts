import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNumberOfChauffeurByYearComponent } from './list-number-of-chauffeur-by-year.component';

describe('ListNumberOfChauffeurByYearComponent', () => {
  let component: ListNumberOfChauffeurByYearComponent;
  let fixture: ComponentFixture<ListNumberOfChauffeurByYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNumberOfChauffeurByYearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNumberOfChauffeurByYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
