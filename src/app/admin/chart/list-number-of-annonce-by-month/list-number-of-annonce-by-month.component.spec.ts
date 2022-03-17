import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListNumberOfAnnonceByMonthComponent } from './list-number-of-annonce-by-month.component';

describe('ListNumberOfAnnonceByMonthComponent', () => {
  let component: ListNumberOfAnnonceByMonthComponent;
  let fixture: ComponentFixture<ListNumberOfAnnonceByMonthComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListNumberOfAnnonceByMonthComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListNumberOfAnnonceByMonthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
