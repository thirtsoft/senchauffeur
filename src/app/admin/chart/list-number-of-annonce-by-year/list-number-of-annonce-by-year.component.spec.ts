import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNumberOfAnnonceByYearComponent } from './list-number-of-annonce-by-year.component';

describe('ListNumberOfAnnonceByYearComponent', () => {
  let component: ListNumberOfAnnonceByYearComponent;
  let fixture: ComponentFixture<ListNumberOfAnnonceByYearComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNumberOfAnnonceByYearComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNumberOfAnnonceByYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
